package com.tratao.payout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tratao.payout.models.*;
import com.tratao.xcore.BaseClient;
import com.tratao.xcore.Config;
import com.tratao.xcore.request.RequestMethod;
import com.tratao.xcore.request.RequestResult;
import com.tratao.xcore.sign.RSASign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client {
    private Config config;
    private BaseClient baseClient;
    private HashMap<String, String> headers;
    private String host = "https://api.xcurerncy.com/payout";
    private boolean sandbox;
    private HashMap<String, Integer> retries;

    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
        if (sandbox) {
            host = "https://api-sandbox.xcurrency.com/payout";
        } else {
            host = "https://api.xcurrency.com/payout";
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Client(Config config) {
        this.config = config;
        baseClient = BaseClient.getInstance();
        headers = Maps.newHashMap(ImmutableMap.of("appKey", config.getAppKey()));
        retries = new HashMap<>();
    }

    /**
     * To get token and auto set headers of token
     * @return response
     */
    public String getToken() {
        String uri = host + "/oauth/token";
        GetTokenRequest request = new GetTokenRequest();
        request.setSecretKey(config.getSecretKey());
        request.setAppKey(config.getAppKey());

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, null, body);
            if (result.getStatusCode() == 200) {
                RequestResponse<String> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<String>>(){});

                // set header token
                headers.put("token", response.getData());

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GetRateResponseData getRate(GetRateRequest request) {
        String uri = host + "/payment/query/price";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<GetRateResponseData> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<GetRateResponseData>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<GetOccupationListResponseData> getOccupationData() {
        String uri = host + "/common/occupation/list";
        getToken();

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.GET, null, headers);

            if (result.getStatusCode() == 200) {
                RequestResponse<List<GetOccupationListResponseData>> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<List<GetOccupationListResponseData>>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<GetPBCAreaListResponseData> getPBCAreaListData() {
        String uri = host + "/common/area/list";
        getToken();

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.GET, null, headers);

            if (result.getStatusCode() == 200) {
                RequestResponse<List<GetPBCAreaListResponseData>> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<List<GetPBCAreaListResponseData>>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public PaymentStatus createTransfer(CreateTransferRequest request) {
        String uri = host + "/payment/create";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<PaymentStatus> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<PaymentStatus>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean confirmTransfer(TradeIDRequest request) {
        String uri = host + "/payment/transfer";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<String> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<String>>(){});

                return response.getData().equals("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean asyncConfirmTransfer(TradeIDRequest request) {
        String uri = host + "/payment/transfer";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse>(){});

                return response.getStatus().equals("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean cancelTransfer(TradeIDRequest request) {
        String uri = host + "/payment/cancel";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse>(){});

                return response.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public PaymentStatus getPaymentStatus(TradeIDRequest request) {
        String uri = host + "/payment/status";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<PaymentStatus> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<PaymentStatus>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updatePaymentInfo(UpdateTransferRequest request) {
        String uri = host + "/payment/update";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<PaymentStatus> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<PaymentStatus>>(){});

                return response.isSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public BalanceResponseData getCurrencyBalance(BalanceRequest request) {
        String uri = host + "/wallet/get";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<BalanceResponseData> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<BalanceResponseData>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BalanceResponseData> getAllBalance() {
        String uri = host + "/wallet/get";
        getToken();

        String body = JSON.toJSONString(new HashMap<>());
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<List<BalanceResponseData>> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<List<BalanceResponseData>>>(){});

                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
