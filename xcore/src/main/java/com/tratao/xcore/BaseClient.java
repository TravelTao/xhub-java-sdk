package com.tratao.xcore;

import com.tratao.xcore.request.RequestMethod;
import com.tratao.xcore.request.RequestResult;
import com.tratao.xcore.utils.TLog;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Base Request with pool
 */
public class BaseClient {
    private int timeOut = 10 * 1000;
    private boolean debug;

    private volatile CloseableHttpClient client;
    private PoolingHttpClientConnectionManager manager;

    private ScheduledExecutorService executorService;

    private BaseClient () {
        // init pool
        manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(20);
        manager.setMaxTotal(200);

        // init http client
        client = getHttpClient();
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {

            manager.closeExpiredConnections();
            manager.closeIdleConnections(timeOut, TimeUnit.MILLISECONDS);

            TLog.getInstance().log("Close expired and idle over 15s connection");

        }, 300, 300, TimeUnit.SECONDS);

    }

    private static class SingletonClient {
        private static final  BaseClient instance = new BaseClient();
    }

    public static BaseClient getInstance() {
        return SingletonClient.instance;
    }

    public CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut)
                .build();

        /**
         *  retry 3 times.
         *  if return false,not retry
         */
        HttpRequestRetryHandler retry = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 3) {
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();

                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };


        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retry)
                .setConnectionManager(manager)
                .build();
    }

    /**
     * release request
     * @param response response data
     */
    private void release(CloseableHttpResponse response) {
        if (!Objects.isNull(response)) {
            try {
                EntityUtils.consume(response.getEntity());
                response.close();
            } catch (IOException e) {
                TLog.getInstance().log("release http request error");
            }
        }
    }

    /**
     * get result
     * @param response response data
     * @return requestResult
     */
    private RequestResult getResult(CloseableHttpResponse response) throws IOException {
        if (response != null && response.getStatusLine() != null) {
            String content = "";
            if (response.getEntity() != null) {
                content = EntityUtils.toString(response.getEntity(), Charsets.UTF_8.name());
            }

            return new RequestResult(response.getStatusLine().getStatusCode(), content);
        }

        return new RequestResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, "");
    }

    /**
     * http base config setting
     * @param httpRequestBase
     */
    private void setRequestConfig(HttpRequestBase httpRequestBase) {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).build();

        httpRequestBase.setConfig(config);
    }

    private RequestResult doGet(String url, HashMap<String, String> params, HashMap<String, String> headers) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        if (MapUtils.isNotEmpty(params)) {
            params.forEach((key, value) -> builder.setParameter(key, value));
        }

        HttpGet httpGet = new HttpGet(builder.build());
        setRequestConfig(httpGet);

        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(httpGet::setHeader);
        }

        CloseableHttpResponse response = client.execute(httpGet);

        try {
            return getResult(response);
        } finally {
            release(response);
        }
    }

    private RequestResult doPost(String url, HashMap<String, String> params, HashMap<String, String> headers, String body) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        if (MapUtils.isNotEmpty(params)) {
            params.forEach((key, value) -> builder.setParameter(key, value));
        }

        HttpPost httpPost = new HttpPost(builder.build());
        setRequestConfig(httpPost);

        if (MapUtils.isNotEmpty(headers)) {
            params.forEach(httpPost::setHeader);
        }

        if (StringUtils.isNotBlank(body)) {
            httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        }

        CloseableHttpResponse response = client.execute(httpPost, HttpClientContext.create());

        try {
            return getResult(response);
        } finally {
            release(response);
        }
    }


    public RequestResult makeRequest(String url, RequestMethod method) throws Exception {
        return makeRequest(url, method, null);
    }

    public RequestResult makeRequest(String url, RequestMethod method, HashMap<String, String> params) throws Exception {
        return makeRequest(url, method, params, null);
    }

    public RequestResult makeRequest(String url, RequestMethod method, HashMap<String, String> params, HashMap<String, String> headers) throws Exception {
        return makeRequest(url, method, params, headers, null);
    }

    public RequestResult makeRequest(String url, RequestMethod method, HashMap<String, String> params, HashMap<String, String> headers, String body) throws Exception {
        switch (method) {
            case GET:
                return doGet(url, params, headers);
            case POST:
                return doPost(url, params, headers, body);
            default:
                TLog.getInstance().log("Not Support Method");
        }
        return new RequestResult(HttpStatus.SC_NOT_FOUND, "");
    }

}
