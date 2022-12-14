package com.tratao.payout.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.tratao.payout.emuns.AccountType;
import com.tratao.payout.emuns.Gender;
import com.tratao.payout.emuns.IDCategory;

import java.util.Date;

public class Payer {
    private AccountType accountType;
    private String cardNo;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationality;
    private String idCountry;
    @JSONField(name = "idType")
    private IDCategory idCategory;
    private String idNumber;
    @JSONField(name = "dob", format = "yyyy-MM-dd")
    private Date birthday;
    @JSONField(format = "yyyy-MM-dd")
    private Date idExpiryDate;
    private String occupation;
    private String iddCode;
    private String phone;
    @JSONField(name = "sex")
    private Gender gender;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Date getIdExpiryDate() {
        return idExpiryDate;
    }

    public void setIdExpiryDate(Date idExpiryDate) {
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
