package com.example.trvelingingroup10.tours;

import com.example.trvelingingroup10.travelers.Traveler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.*;

@NonNull

@ToString


public class Tour {
    private String contactInfo,tourName,tourGuide,startDate,startTime,endDate,endTime;

    private Boolean isOngoingTour;
    private ArrayList<Traveler>listOfTravelrs;


    public Tour() {
    }

    public Tour(String contactInfo, String tourName, String tourGuide, String startDate, String startTime, String endDate, String endTime, Boolean isOngoingTour, ArrayList<Traveler> listOfTravelrs) {
        this.contactInfo = contactInfo;
        this.tourName = tourName;
        this.tourGuide = tourGuide;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.isOngoingTour = isOngoingTour;
        this.listOfTravelrs = listOfTravelrs;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(String tourGuide) {
        this.tourGuide = tourGuide;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getOngoingTour() {
        return isOngoingTour;
    }

    public void setOngoingTour(Boolean ongoingTour) {
        isOngoingTour = ongoingTour;
    }

    public ArrayList<Traveler> getListOfTravelrs() {
        return listOfTravelrs;
    }

    public void setListOfTravelrs(ArrayList<Traveler> listOfTravelrs) {
        this.listOfTravelrs = listOfTravelrs;
    }


}
