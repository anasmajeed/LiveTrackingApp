package com.example.anas_ch.livetracking;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Anas-CH on 28-Apr-17.
 */

public class Users {
    String id;
    String name ;
    String password;
    String phoneNumber;
    String request;
    String longitude;
    String latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Users(){
    }

    public Users(String name, String password, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Users(String name, String password, String phoneNumber, String request) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Name : " + name +
                "\nPhone Number : "+ phoneNumber;
    }
}
