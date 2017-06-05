package com.example.orangemodule.bean;

/**
 * Created by 杨健 on 2017/6/5.
 * function: module的实体类
 */

public class ModuleBean {

    private String moduleName;
    private String moduleBackground;

    public ModuleBean(String moduleName, String moduleBackground) {
        this.moduleName = moduleName;
        this.moduleBackground = moduleBackground;
    }

    public String getModuleBackground() {
        return moduleBackground;
    }

    public void setModuleBackground(String moduleBackground) {
        this.moduleBackground = moduleBackground;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
