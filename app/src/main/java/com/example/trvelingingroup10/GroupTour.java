package com.example.trvelingingroup10;

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
    public int numberToAdd;
    public String GroupName,GroupCode,dateOfTourStart,groupContactInfo,groupId,groupLeader;
    public static final Map <String, TravelerContent.TravelerItem> TRAVELERS_LIST = new HashMap<String, TravelerContent.TravelerItem>();




    public void addTravelerToTheGroupTour(TravelerContent.TravelerItem travelerItem){
        TRAVELERS_LIST.put("uid",travelerItem);
        //todo:add travelers to a group
    }


}
