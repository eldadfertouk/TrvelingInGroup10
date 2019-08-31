package com.example.trvelingingroup10.content;
import android.location.Location;

import lombok.*;
import lombok.extern.java.Log;

@Getter
@Setter
@NonNull

public class BasicAppUser {

    private String uId,fullName,emailAddress,phoneNumber;
    private Double lon,lat;


    public BasicAppUser() {

    }

    public BasicAppUser(String uId, String fullName, String emailAddress, String phoneNumber, Double lon, Double lat) {
        this.uId = uId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.lon = lon;
        this.lat = lat;
    }

    public String getuId() { return uId; }


    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
