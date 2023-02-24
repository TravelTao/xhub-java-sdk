package com.tratao.payout.models;

import java.io.Serializable;
import java.util.List;

/**
 * China PBCArea Data
 *
 * The areaLevel value of 1 is state(province), 2 is city, 3 is district, for beneficiary's address data can be filled by pinyin fields.
 * The state(province) has city's list, the city has district's list and the district's list is null or empty.
 */
public class PBCAreaResponseData implements Serializable {
    private String areaLevel;
    private String areaKey;
    private String code;
    private List<PBCAreaResponseData> list;
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

    public List<PBCAreaResponseData> getList() {
        return list;
    }

    public void setList(List<PBCAreaResponseData> list) {
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
