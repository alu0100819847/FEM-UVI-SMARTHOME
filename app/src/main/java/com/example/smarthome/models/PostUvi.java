package com.example.smarthome.models;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class PostUvi {


    public String uid;
    public String location;
    public String date_iso;
    public float value;

    public Map<String, Boolean> stars = new HashMap<>();

    public PostUvi() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public PostUvi(String location, String date_iso, Float value) {
        this.location = location;
        this.date_iso = date_iso;
        this.value = value;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
                "key='" + uid + '\'' +
                "location='" + location + '\'' +
                ", date_iso='" + date_iso + '\'' +
                ", value=" + value +
                '}';
    }
}
