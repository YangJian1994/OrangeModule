package com.example.orangemodule.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by 杨健 on 2017/6/5.
 * function: 底部导航界面的类
 */

public class CommonTabBean implements CustomTabEntity {

    private int selectedImage;
    private int unselectedImage;
    private String title;

    public CommonTabBean(int selectedImage, int unselectedImage, String title) {
        this.selectedImage = selectedImage;
        this.unselectedImage = unselectedImage;
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedImage;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedImage;
    }

}
