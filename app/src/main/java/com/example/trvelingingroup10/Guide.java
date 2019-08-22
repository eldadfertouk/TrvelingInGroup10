package com.example.trvelingingroup10;

import java.util.List;

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


public class Guide {
    public int numberToAdd;
    public String fullName,displayName,dateOfBirth,guideEmail,guideUid;
    public boolean isTourLeader;
    public List oldGroupsTourLeadByGuide;

}
