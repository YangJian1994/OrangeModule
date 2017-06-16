package com.example.orangemodule.bean;

/**
 * Created by 杨健 on 2017/6/16.
 * function: 空气详细json数据类
 */

public class AirDetail {

    private String resp;
    private int[] AQI;
    private int[] PM25;

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public int[] getAQI() {
        return AQI;
    }

    public void setAQI(int[] AQI) {
        this.AQI = AQI;
    }

    public int[] getPM25() {
        return PM25;
    }

    public void setPM25(int[] PM25) {
        this.PM25 = PM25;
    }
}
