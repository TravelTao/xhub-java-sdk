package com.tratao.payout.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.tratao.payout.emuns.AccountType;
import com.tratao.payout.emuns.Gender;
import com.tratao.payout.emuns.IDCategory;

import java.util.Date;

public class Beneficiary {
    private AccountType accountType;
    @JSONField(name = "accountNumber")
    private String cardNo;
    private String email;
    @JSONField(name = "accountName")
    private String cardName;
    private String nationality;

    private String country;
    @JSONField(name = "idType")
    private IDCategory idCategory;
    private String idNumber;
    @JSONField(name = "dob")
    private String birthday;
    @JSONField(format = "yyyy-MM-dd")
    private Date idExpiryDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date idIssueDate;
    private String occupation;
    private String iddCode;
    private String phone;
    @JSONField(name = "sex")
    private Gender gender;

    private Address address;

    public Beneficiary() {
        this.accountType = AccountType.BANK;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIdExpiryDate(Date idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public Date getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(Date idIssueDate) {
        this.idIssueDate = idIssueDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getIdExpiryDate() {
        return idExpiryDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIddCode() {
        return iddCode;
    }

    public void setIddCode(String iddCode) {
        this.iddCode = iddCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public IDCategory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(IDCategory idCategory) {
        this.idCategory = idCategory;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
