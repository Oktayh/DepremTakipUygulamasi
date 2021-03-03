package com.example.newsapp;

public class Earthquake {

    String location;
    double magnitude;
    String mDate;
    double longitude;
    double latitude;
    double depth;

    public Earthquake() {
    }

    public Earthquake(String location, double magnitude,String mDate) {
        this.location = location;
        this.magnitude = magnitude;
        this.mDate = mDate;
    }

    public Earthquake(String location, double magnitude, double longitude, double latitude,double depth) {
        this.location = location;
        this.magnitude = magnitude;
        this.longitude = longitude;
        this.latitude = latitude;
        this.depth = depth;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }
    public void setMagnitude(double magnitude) { this.magnitude = magnitude; }

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getmDate() { return mDate; }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getDepth() {
        return depth;
    }
}
