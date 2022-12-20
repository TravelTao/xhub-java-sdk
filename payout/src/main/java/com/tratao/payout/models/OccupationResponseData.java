package com.tratao.payout.models;

import java.util.Locale;

public class OccupationResponseData {
    private String nameCn;
    private String nameEn;
    private String nameTw;

    /**
     * get occupation name by locale
     * - Locale.SIMPLIFIED_CHINESE
     * - Locale.TRADITIONAL_CHINESE
     * other Locale will return english of occupation name.
     *
     * @param locale Locale of SIMPLIFIED_CHINESE
     * @return occupation name
     */
    public String getName(Locale locale) {
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            return this.nameCn;
        } else if (locale.equals(Locale.TRADITIONAL_CHINESE)) {
            return this.nameTw;
        } else {
            return this.nameEn;
        }
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameTw() {
        return nameTw;
    }

    public void setNameTw(String nameTw) {
        this.nameTw = nameTw;
    }
}
