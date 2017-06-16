package com.example.orangemodule.bean;

/**
 * Created by 杨健 on 2017/6/16.
 * function: 电池电量的Json数据类
 */

public class Battery {

    private String resp;
    private int frame_battery;

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public int getFrame_battery() {
        return frame_battery;
    }

    public void setFrame_battery(int frame_battery) {
        this.frame_battery = frame_battery;
    }
}
