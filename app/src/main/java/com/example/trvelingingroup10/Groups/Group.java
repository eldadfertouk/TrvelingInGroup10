package com.example.trvelingingroup10.Groups;

import android.media.Image;

import com.example.trvelingingroup10.content.TravelerContent;
import com.example.trvelingingroup10.travelers.Traveler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NonNull

@ToString


public class Group {
    private int numberToAdd;
    private int monitorLevel;
    private String name,code,dateOfTourStart,contactInfo,groupId,groupLeader,groupImageRefPath;
    private Date startDate,endDate;
    private Time startTime,endTime;
    private Image groupPicture;

    private List<Traveler> travelersInTheGroup ;
    private Map <String, TravelerContent.TravelerItem> TRAVELERS_LIST = new HashMap<String, TravelerContent.TravelerItem>();

    public Group() {
    }
    public Group(int tets){
        this.numberToAdd = 50;

        this.name = "fisrt group";
        this.code = "g1";
        this.monitorLevel=1;
        this.dateOfTourStart = "31/12/2019";
        this.contactInfo = "group contact info sms phone ";
        this.groupId = "g-id1";
        this.groupLeader = "team leader";
        this.groupImageRefPath = "image path";
        this.travelersInTheGroup = new ArrayList<Traveler>();

    }

    public Group(int numberToAdd, int groupId, String groupName, String groupCode, String dateOfTourStart, String groupContactInfo, String groupId1, String groupLeader, String groupImageRefPath, Image groupPicture, Map<String, TravelerContent.TravelerItem> TRAVELERS_LIST) {
        this.numberToAdd = numberToAdd;

        this.name = groupName;
        this.code = groupCode;
        this.dateOfTourStart = dateOfTourStart;
        this.contactInfo = groupContactInfo;
        this.groupId = groupId1;
        this.groupLeader = groupLeader;
        this.groupImageRefPath = groupImageRefPath;
        this.groupPicture = groupPicture;
        this.TRAVELERS_LIST = TRAVELERS_LIST;
    }

    public void addTravelerToTheGroupTour(TravelerContent.TravelerItem travelerItem){
        this.TRAVELERS_LIST.put("uid",travelerItem);
        //todo:add travelers to a group
    }

    public int getNumberToAdd() {
        return numberToAdd;
    }

    public void setNumberToAdd(int numberToAdd) {
        this.numberToAdd = numberToAdd;
    }



    public int getMonitorLevel() {
        return monitorLevel;
    }

    public void setMonitorLevel(int monitorLevel) {
        this.monitorLevel = monitorLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateOfTourStart() {
        return dateOfTourStart;
    }

    public void setDateOfTourStart(String dateOfTourStart) {
        this.dateOfTourStart = dateOfTourStart;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGroupImageRefPath() {
        return groupImageRefPath;
    }

    public void setGroupImageRefPath(String groupImageRefPath) {
        this.groupImageRefPath = groupImageRefPath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Image getGroupPicture() {
        return groupPicture;
    }

    public void setGroupPicture(Image groupPicture) {
        this.groupPicture = groupPicture;
    }

    public List<Traveler> getTravelersInTheGroup() {
        return travelersInTheGroup;
    }

    public void setTravelersInTheGroup(List<Traveler> travelersInTheGroup) {
        this.travelersInTheGroup = travelersInTheGroup;
    }

    public Map<String, TravelerContent.TravelerItem> getTRAVELERS_LIST() {
        return TRAVELERS_LIST;
    }

    public void setTRAVELERS_LIST(Map<String, TravelerContent.TravelerItem> TRAVELERS_LIST) {
        this.TRAVELERS_LIST = TRAVELERS_LIST;
    }
}
