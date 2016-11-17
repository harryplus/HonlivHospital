package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * Created by Rodin on 2016/11/2.
 */
public class HxtBean implements Serializable {
    private String cardNo;
    private String cardType;
    private String name;
    private String idNo;
    private String sex;
    private String phone;
    private String HxtCardNo;
    private String UserID;
    private String cardUserName;
    private String bindDateTime;
    private String bindStatus;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }

    public String getBindDateTime() {
        return bindDateTime;
    }

    public void setBindDateTime(String bindDateTime) {
        this.bindDateTime = bindDateTime;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getCardNo() {
        return cardNo;
    }

    private String idType;

    public void setName(String name) {
        this.name = name;
    }

    public String getHxtCardNo() {
        return HxtCardNo;
    }

    public void setHxtCardNo(String hxtCardNo) {
        HxtCardNo = hxtCardNo;
    }

    public String getName() {
        return name;
    }

    public void setCardNo(String cardNo) {
        cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "HxtBean{" +
                "cardNo='" + cardNo + '\'' +
                ", cardType='" + cardType + '\'' +
                ", name='" + name + '\'' +
                ", idNo='" + idNo + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", HxtCardNo='" + HxtCardNo + '\'' +
                ", UserID='" + UserID + '\'' +
                ", cardUserName='" + cardUserName + '\'' +
                ", bindDateTime='" + bindDateTime + '\'' +
                ", bindStatus='" + bindStatus + '\'' +
                ", idType='" + idType + '\'' +
                '}';
    }
}
