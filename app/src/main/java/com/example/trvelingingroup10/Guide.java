package com.example.trvelingingroup10;

import android.media.Image;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@NonNull

@ToString


public class Guide {
    private int numberToAdd;
    private int guideId;
    private String fullName,displayName,dateOfBirth,guideEmail,guideUid,guideImageRefPath;
    private boolean isTourLeader;
    private Image guideProfilePicture;
    private List oldGroupsTourLeadByGuide;

    public Guide() {
    }

    public Guide(int numberToAdd, int guideId, String fullName, String displayName, String dateOfBirth, String guideEmail, String guideUid, String guideImageRefPath, boolean isTourLeader, Image guideProfilePicture, List oldGroupsTourLeadByGuide) {
        this.numberToAdd = numberToAdd;
        this.guideId = guideId;
        this.fullName = fullName;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.guideEmail = guideEmail;
        this.guideUid = guideUid;
        this.guideImageRefPath = guideImageRefPath;
        this.isTourLeader = isTourLeader;
        this.guideProfilePicture = guideProfilePicture;
        this.oldGroupsTourLeadByGuide = oldGroupsTourLeadByGuide;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public int getNumberToAdd() {
        return numberToAdd;
    }

    public void setNumberToAdd(int numberToAdd) {
        this.numberToAdd = numberToAdd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGuideEmail() {
        return guideEmail;
    }

    public void setGuideEmail(String guideEmail) {
        this.guideEmail = guideEmail;
    }

    public String getGuideUid() {
        return guideUid;
    }

    public void setGuideUid(String guideUid) {
        this.guideUid = guideUid;
    }

    public String getGuideImageRefPath() {
        return guideImageRefPath;
    }

    public void setGuideImageRefPath(String guideImageRefPath) {
        this.guideImageRefPath = guideImageRefPath;
    }

    public boolean isTourLeader() {
        return isTourLeader;
    }

    public void setTourLeader(boolean tourLeader) {
        isTourLeader = tourLeader;
    }

    public Image getGuideProfilePicture() {
        return guideProfilePicture;
    }

    public void setGuideProfilePicture(Image guideProfilePicture) {
        this.guideProfilePicture = guideProfilePicture;
    }

    public List getOldGroupsTourLeadByGuide() {
        return oldGroupsTourLeadByGuide;
    }

    public void setOldGroupsTourLeadByGuide(List oldGroupsTourLeadByGuide) {
        this.oldGroupsTourLeadByGuide = oldGroupsTourLeadByGuide;
    }
}
