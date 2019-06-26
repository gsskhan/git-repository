package org.demo.android.utils;

public enum AppConstants {

    MAIN_LOGGER_TAG ("App Log"),
    DEVELOPER_LOGGER_TAG ("Developer Log");

    private final String strValue;

    AppConstants(String strValue) {
        this.strValue = strValue;
    }

    public String getStrValue(){
        return this.strValue;
    }
}
