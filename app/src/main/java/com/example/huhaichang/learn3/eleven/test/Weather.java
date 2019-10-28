package com.example.huhaichang.learn3.eleven.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huhaichang on 2019/8/17.
 */

public class Weather {
    public String city;
    public String weather;
    public String pm25;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }
}
