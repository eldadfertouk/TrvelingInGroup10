package com.example.trvelingingroup10.Groups;

import android.media.Image;

import com.example.trvelingingroup10.content.TravelerContent;
import com.example.trvelingingroup10.travelers.Traveler;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@NonNull

@ToString


public class Group {
    private int numberToAdd;
    private static int GroupId,monitorLevel;
    private String GroupName,GroupCode,dateOfTourStart,groupContactInfo,groupId,groupLeader,groupImageRefPath;
    private Date startDate,endDate;
    private Time startTime,endTime;

    private Image groupPicture;
    private List<Traveler> listOfTravelerInGroup ;
    private Map <String, TravelerContent.TravelerItem> TRAVELERS_LIST = new HashMap<String, TravelerContent.TravelerItem>();

    public Group() {
    }
    public Group(int tets){
        this.numberToAdd = 50;
        GroupId = 99999;
        GroupName = "fisrt group";
        GroupCode = "g1";
        this.monitorLevel=1;
        this.dateOfTourStart = "31/12/2019";
        this.groupContactInfo = "group contact info sms phone ";
        this.groupId = "g-id1";
        this.groupLeader = "team leader";
        this.groupImageRefPath = "image path";

    }

    public Group(int numberToAdd, int groupId, String groupName, String groupCode, String dateOfTourStart, String groupContactInfo, String groupId1, String groupLeader, String groupImageRefPath, Image groupPicture, Map<String, TravelerContent.TravelerItem> TRAVELERS_LIST) {
        this.numberToAdd = numberToAdd;
        GroupId = groupId;
        GroupName = groupName;
        GroupCode = groupCode;
        this.dateOfTourStart = dateOfTourStart;
        this.groupContactInfo = groupContactInfo;
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

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getGroupCode() {
        return GroupCode;
    }

    public void setGroupCode(String groupCode) {
        GroupCode = groupCode;
    }

    public String getDateOfTourStart() {
        return dateOfTourStart;
    }

    public void setDateOfTourStart(String dateOfTourStart) {
        this.dateOfTourStart = dateOfTourStart;
    }

    public String getGroupContactInfo() {
        return groupContactInfo;
    }

    public void setGroupContactInfo(String groupContactInfo) {
        this.groupContactInfo = groupContactInfo;
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

    public Image getGroupPicture() {
        return groupPicture;
    }

    public void setGroupPicture(Image groupPicture) {
        this.groupPicture = groupPicture;
    }

}