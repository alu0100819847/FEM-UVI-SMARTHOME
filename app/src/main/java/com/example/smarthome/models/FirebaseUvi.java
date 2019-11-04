package com.example.smarthome.models;

public class FirebaseUvi {


    private String location;

    private String date_iso;

    private float value;

    public FirebaseUvi(){}

    public FirebaseUvi(String location, String date_iso, float value){
        this.location = location;
        this.date_iso = date_iso;
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_iso() {
        return date_iso;
    }

    public void setDate_iso(String date_iso) {
        this.date_iso = date_iso;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FirebaseUvi{" +
                "location='" + location + '\'' +
                ", date_iso='" + date_iso + '\'' +
                ", value=" + value +
                '}';
    }
}
