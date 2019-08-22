package com.example.trvelingingroup10;

import android.media.Image;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import lombok.*;
import pl.droidsonroids.gif.GifImageView;

@Getter
@Setter
@AllArgsConstructor
@NonNull

@RequiredArgsConstructor
@ToString


public class Traveler {
    public int numberToAdd;
    public String fullName,displayName,dateOfBirth,userEmail,userUid,travelerGroup;
    public Boolean isPracticeRealign,isEatKosherFood,isHeavySited,isHeavyListener,isMiner,isNeedHelp;
    public GifImageView userProfilePicture;

}
