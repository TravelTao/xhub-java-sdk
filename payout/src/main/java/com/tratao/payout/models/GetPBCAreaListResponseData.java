package com.tratao.payout.models;

import java.util.List;

public class GetPBCAreaListResponseData {
    private String areaLevel;
    private String areaKey;
    private String code;
    private List<GetOccupationListResponseData> list;
    private String name;
    private String pinyin;

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaKey() {
        return areaKey;
    }

    public void setAreaKey(String areaKey) {
        this.areaKey = areaKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<GetOccupationListResponseData> getList() {
        return list;
    }

    public void setList(List<GetOccupationListResponseData> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
