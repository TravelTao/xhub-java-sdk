package com.tratao.payout.models;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Address implements Serializable {
    @NotEmpty(message = "address1 can not be empty")
    @JSONField(name = "address")
    private String address1;
    private String city;
    private String country;
    private String district;
    private String postCode;
    private String state;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
