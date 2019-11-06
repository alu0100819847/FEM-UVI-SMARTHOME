package com.example.smarthome.models;

public class PostUvi {


    public String location;
    public String date_iso;
    public float value;


    public PostUvi() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public PostUvi(String location, String date_iso, Float value) {
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
    public String toString(){
        return "PostUvi{" +
                "location='" + location + '\'' +
                ", date_iso='" + date_iso + '\'' +
                ", value=" + value +
                '}';
    }
}
