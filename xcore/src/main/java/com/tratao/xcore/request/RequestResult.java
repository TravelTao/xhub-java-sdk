package com.tratao.xcore.request;

import java.io.Serializable;

public class RequestResult implements Serializable {
    private int statusCode;
    private String content;

    public RequestResult(int statusCode, String content) {
        this.content = content;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
