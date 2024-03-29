package com.tratao.payout.models;

import java.io.Serializable;

public class NotifyResponseData implements Serializable {
    private String code;
    private String message;

    public NotifyResponseData() {}

    public NotifyResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
