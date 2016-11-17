package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * Created by Rodin on 2016/11/14.
 */
public class UserBean implements Serializable {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getID_Card() {
        return ID_Card;
    }

    public void setID_Card(String ID_Card) {
        this.ID_Card = ID_Card;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHead_Pic() {
        return Head_Pic;
    }

    public void setHead_Pic(String head_Pic) {
        Head_Pic = head_Pic;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public int getJoin_Time() {
        return Join_Time;
    }

    public void setJoin_Time(int join_Time) {
        Join_Time = join_Time;
    }

    public int getUpdate_Time() {
        return Update_Time;
    }

    public void setUpdate_Time(int update_Time) {
        Update_Time = update_Time;
    }

    public int getEnable_Flag() {
        return Enable_Flag;
    }

    public void setEnable_Flag(int enable_Flag) {
        Enable_Flag = enable_Flag;
    }

    private String ID;

    public String getUAddress() {
        return UAddress;
    }

    public void setUAddress(String UAddress) {
        this.UAddress = UAddress;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public int getUAge() {
        return UAge;
    }

    public void setUAge(int UAge) {
        this.UAge = UAge;
    }

    private String UAddress;
    private String UName;
    private int UAge;
    private String PhoneNumber;
    private String Password;
    private String ID_Card;
    private String Name;
    private String Head_Pic;
    private int Sex;
    private int Join_Time;
    private int Update_Time;
    private int Enable_Flag;

    @Override
    public String toString() {
        return "UserBean{" +
                "ID='" + ID + '\'' +
                ", UAddress='" + UAddress + '\'' +
                ", UName='" + UName + '\'' +
                ", UAge=" + UAge +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", ID_Card='" + ID_Card + '\'' +
                ", Name='" + Name + '\'' +
                ", Head_Pic='" + Head_Pic + '\'' +
                ", Sex=" + Sex +
                ", Join_Time=" + Join_Time +
                ", Update_Time=" + Update_Time +
                ", Enable_Flag=" + Enable_Flag +
                '}';
    }
}
