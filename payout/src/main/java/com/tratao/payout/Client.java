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
import com.tratao.xcore.utils.TLog;

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

    /**
     * init with config
     * @param config which provide by xCurrency Hubs
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get payment reference rate
     * Such as USD/CNY, AUD/CNY
     * if not get rate will return null
     * @param request { targetCurrency: CNY, sourceCurrency: USD }
     * @return { queryNo: query id, rate: 6.7522, sourceCurrency: USD, targetCurrency: CNY }
     */
    public RateResponseData getRate(GetRateRequest request) {
        String uri = host + "/payment/query/price";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<RateResponseData> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<RateResponseData>>(){});

                return response.getData();
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get Occupation data
     * `OccupationResponseData.getName` will return data by what Locale you set.
     *
     * - nameEn: occupation english
     * - nameCn: occupation simplified chinese
     * - nameTw: occupation traditional chinese
     *
     * @return [ { nameEn: , nameCn: , nameTw: } ]
     */
    public List<OccupationResponseData> getOccupationData() {
        String uri = host + "/common/occupation/list";
        getToken();

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.GET, null, headers);

            if (result.getStatusCode() == 200) {
                RequestResponse<List<OccupationResponseData>> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<List<OccupationResponseData>>>(){});

                return response.getData();
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * get china pbc area data
     * this can use for checking beneficiary's address data is right or not.
     *
     * - areaLevel: the value 1 is `state`, value 2 is `city`, value 3 is `district`
     * - areaKey: the key of area
     * - code: postcode
     * - list: [ { areaLevel, areaKey, code, list: [], name: , pinyin: } ]
     * - name: Simplified Chinese of area name
     * - pinyin: Chinese PINYIN of area name
     *
     * @return [ { areaLevel, areaKey, code, list: [], name: , pinyin: } ]
     */
    public List<PBCAreaResponseData> getPBCAreaListData() {
        String uri = host + "/common/area/list";
        getToken();

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.GET, null, headers);

            if (result.getStatusCode() == 200) {
                RequestResponse<List<PBCAreaResponseData>> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<List<PBCAreaResponseData>>>(){});

                return response.getData();
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * create a payment to xCurrency Hubs.
     * when get PaymentStatus not null mean that create payment success on xCurrency Hubs side,
     * the tradeId of PaymentStatus should store on your side, this is use for many apis call.
     *
     * @param request request data
     * @return { tradeId: , status:  }
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * send money to beneficiary
     * the request tradeId which is stored by create payment response
     * true mean that success send money to beneficiary
     * false mean that transfer error, can check the log
     *
     * @param request { tradeId }
     * @return true or false
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * async method to send money to beneficiary.
     * use this method should get webhook notify to receive the paymentStatus
     * true mean that success post data to xCurrency Hubs side.
     * false should check the logs.
     *
     * @param request { tradeId }
     * @return true or false
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * cancel the payment
     *
     * @param request { tradeId }
     * @return true or false
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * get payment status
     *
     * @param request { tradeId }
     * @return { status: , message: }
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * update the payment info
     * use this to update some data of payment
     * true mean that update success
     * false mean that update error, please check the logs.
     *
     * @param request UpdateTransferRequest
     * @return true or false
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * get simple wallet data
     *
     * @param request { currency }
     * @return { amount: , currency: , walletId:  }
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get all wallet data
     *
     * @return [ { amount: , currency: , walletId:  } ]
     */
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
            } else {
                TLog.getInstance().log(result.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
