package com.example.trvelingingroup10;

import android.media.Image;

import com.example.trvelingingroup10.content.TravelerContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NonNull

@RequiredArgsConstructor
@ToString


public class GroupTour {
    private int numberToAdd;
    private int GroupId;
    private String GroupName,GroupCode,dateOfTourStart,groupContactInfo,groupId,groupLeader,groupImageRefPath;
    private Image groupPicture;
    private Map <String, TravelerContent.TravelerItem> TRAVELERS_LIST = new HashMap<String, TravelerContent.TravelerItem>();


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
