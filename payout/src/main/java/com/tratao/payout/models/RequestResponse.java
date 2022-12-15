package com.tratao.payout.models;

import com.alibaba.fastjson.JSON;

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

    @Override
    public String toString() {
        return "{\"status\": \""+status+"\", \"message\": \""+message+"\", \"data\": " + JSON.toJSONString(data) +"}";
    }
}
