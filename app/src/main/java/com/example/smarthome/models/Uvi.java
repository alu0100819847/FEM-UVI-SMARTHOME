package com.example.smarthome.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uvi {

    @SerializedName("name")
    @Expose
    private String lat;

    @SerializedName("lon")
    @Expose
    private String lon;

    @SerializedName("date_iso")
    @Expose
    private String date_iso;

    @SerializedName("date")
    @Expose
    private long date;

    @SerializedName("value")
    @Expose
    private float value;


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDate_iso() {
        return date_iso;
    }

    public void setDate_iso(String date_iso) {
        this.date_iso = date_iso;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Uvi{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", date_iso='" + date_iso + '\'' +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
