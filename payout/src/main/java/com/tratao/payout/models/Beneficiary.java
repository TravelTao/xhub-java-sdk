package com.tratao.payout.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.tratao.payout.emuns.AccountType;
import com.tratao.payout.emuns.Gender;
import com.tratao.payout.emuns.IDCategory;

import java.util.Date;

public class Beneficiary {
    private AccountType accountType = AccountType.BANK;
    @JSONField(name = "accountNumber")
    private String cardNo;
    private String email;
    @JSONField(name = "accountName")
    private String cardName;
    private String nationality;

    @JSONField(name = "idType")
    private IDCategory idCategory;
    private String idNumber;
    @JSONField(name = "dob")
    private String birthday;
    private String idExpiryDate;
    private String idIssueDate;
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

    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public String getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(String idIssueDate) {
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

    public String getIdExpiryDate() {
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
