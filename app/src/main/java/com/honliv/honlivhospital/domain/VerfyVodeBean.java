package com.honliv.honlivhospital.domain;

import java.io.Serializable;

/**
 * Created by Rodin on 2016/11/14.
 */
public class VerfyVodeBean implements Serializable {
    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public VData getData() {
        return Data;
    }

    public void setData(VData data) {
        Data = data;
    }

    private int StatusCode;
    private String Info;

    private VData Data;


    private class VData {
        private String VerfyVode;
        private String Time;

        public String getVerfyVode() {
            return VerfyVode;
        }

        public void setVerfyVode(String verfyVode) {
            VerfyVode = verfyVode;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }
    }
}
