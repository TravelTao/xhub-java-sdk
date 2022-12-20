package com.tratao.payout.models;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

public class RequestResponse<T> {
    private String status;
    private String message;
    private T data;

    public RequestResponse() {
        this.status = "-1";
    }

    public RequestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (Strings.isNullOrEmpty(this.status)) {
            return false;
        }
        return this.status.equals("1");
    }

    @Override
    public String toString() {
        return "{\"status\": \""+status+"\", \"message\": \""+message+"\", \"data\": " + JSON.toJSONString(data) +"}";
    }
}
