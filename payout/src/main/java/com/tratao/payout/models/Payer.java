package com.tratao.payout.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.tratao.payout.emuns.AccountType;
import com.tratao.payout.emuns.Gender;
import com.tratao.payout.emuns.IDCategory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Payer implements Serializable {
    private AccountType accountType;
    // 本地银行卡号
    private String accountNumber;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationality;
    private String idCountry;
    @JSONField(name = "idType")
    private IDCategory idCategory;
    private String idNumber;
    @JSONField(name = "dob")
    private String birthday;
    private String idExpiryDate;
    private String occupation;
    private String iddCode;
    private String phone;
    @JSONField(name = "sex")
    private Gender gender;

    @Valid
    @NotNull(message = "can not be null")
    private Address address;

    public Payer() {
        this.accountType = AccountType.BANK;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
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

    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
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
