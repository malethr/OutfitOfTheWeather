package com.epicodus.ootw.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Weather {
    private long mTime;
    private double mHumidity;
    private double mTemperature;
    private double mPercipChance;
    private String mSummary;
    private String mTimezone;

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }


    public long getTime() {
        return mTime;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public double getHumidity(){
        return mHumidity;
    }

    public void setHumidity(double humidity){
        mHumidity = humidity;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public int getPercipChance() {
        double precipPercentage = mPercipChance * 100;
        return (int)Math.round(precipPercentage);
    }

    public void setPercipChance(double percipChance) {
        mPercipChance = percipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
