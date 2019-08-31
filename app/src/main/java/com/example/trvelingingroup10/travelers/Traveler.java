package com.example.trvelingingroup10.travelers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trvelingingroup10.content.BasicAppUser;
import com.example.trvelingingroup10.utils.FireBaseInit;
import com.example.trvelingingroup10.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lombok.*;


@ToString

public class Traveler extends BasicAppUser {

    private BasicAppUser basicUser;

    private int numberToAdd;
    private String fullName,displayName,dateOfBirth,userEmail,userUid,travelerGroup,travelerImageRefPath,travelerPhoneNumber;
    private Boolean isPracticeRealign,isEatKosherFood,isHeavySited,isHeavyListener,isMiner,isNeedHelp;
    private ImageView userProfilePicture;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef= database.getReference("travelers");

    public Traveler(){

    }
    public Traveler(int a){

        this.fullName="traveler 1";
        this.displayName="show name";
        this.dateOfBirth="25/12/2000";
        this.userEmail="a@a.com";
        this.userEmail="v8nwFFiV94SwHgS6CiPWuFTw0Uz2";
        this.travelerGroup="group1";
        this.travelerPhoneNumber="555-6667777";
    }
    public Traveler(String toString, String toString1, String toString2, String s) {

    }


    private void getImageFromServer(final ImageView imageView){
        FireBaseInit.getFireBaseInit().getStorageRef().child(travelerImageRefPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {

            // third party liberery that adds images as image view
            // uses temparery pic until picture is upload
            Glide.with(imageView).load(uri)
                    .thumbnail(Glide.with(imageView).load(R.drawable.mypicspacer))
                    .into(imageView);

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@androidx.annotation.NonNull @NonNull Exception exception) {
            // Handle any errors
        }
    });

    }
    public void putImageInServerWithInputStream(final Activity context, Uri imagePath) {

        // TODO: 10/01/2019 טעינת תמונה אל השרת באמצעות נתיב התמונה שבחרנו
        StorageReference imagesRef = FireBaseInit.getFireBaseInit().getStorageRef().child(travelerImageRefPath);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        UploadTask uploadTask = imagesRef.putBytes(data);
        setTheUploadTaskLiseners(context,uploadTask);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context,"פעולה לא הצליחה",Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(context,"פעולה  הצליחה",Toast.LENGTH_SHORT).show();
                moveToNextActivity(context);
            }
        });

    }

    private void setTheUploadTaskLiseners(final Activity context, UploadTask uploadTask) {
        // TODO: 10/01/2019 מאזין לשינויים של העלאת הקובץ ומעדכן את המד ההתקדמות שלנו בהתאם
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                setTheProgressBar(context,taskSnapshot.getTotalByteCount(),taskSnapshot.getBytesTransferred());

            }
        });
    }

    private void setTheProgressBar(Activity context, long total, long progress) {
        ProgressBar progressBar=context.findViewById(R.id.image_progressbar);
        progressBar.setMax((int) total);
        progressBar.setProgress((int) progress);


    }

    private void moveToNextActivity(Activity context) {
        context.finish();
        context.startActivity(new Intent(context,ShowTravelersOnFireBase.class));
    }


    public void setImagesRefPath(String imagesRefPath) {
        this.travelerImageRefPath = imagesRefPath;
    }
    private void AddTravelerToDataBase(Traveler traveler){
        this.myRef.setValue(Traveler.class, traveler);

    }

    public BasicAppUser getUser() {
        return basicUser;
    }

    public void setUser(BasicAppUser user) {
        this.basicUser = user;
    }

    public int getNumberToAdd() {
        return numberToAdd;
    }

    public void setNumberToAdd(int numberToAdd) {
        this.numberToAdd = numberToAdd;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getTravelerGroup() {
        return travelerGroup;
    }

    public void setTravelerGroup(String travelerGroup) {
        this.travelerGroup = travelerGroup;
    }

    public String getTravelerImageRefPath() {
        return travelerImageRefPath;
    }

    public void setTravelerImageRefPath(String travelerImageRefPath) {
        this.travelerImageRefPath = travelerImageRefPath;
    }

    public String getTravelerPhoneNumber() {
        return travelerPhoneNumber;
    }

    public void setTravelerPhoneNumber(String travelerPhoneNumber) {
        this.travelerPhoneNumber = travelerPhoneNumber;
    }

    public Boolean getPracticeRealign() {
        return isPracticeRealign;
    }

    public void setPracticeRealign(Boolean practiceRealign) {
        isPracticeRealign = practiceRealign;
    }

    public Boolean getEatKosherFood() {
        return isEatKosherFood;
    }

    public void setEatKosherFood(Boolean eatKosherFood) {
        isEatKosherFood = eatKosherFood;
    }

    public Boolean getHeavySited() {
        return isHeavySited;
    }

    public void setHeavySited(Boolean heavySited) {
        isHeavySited = heavySited;
    }

    public Boolean getHeavyListener() {
        return isHeavyListener;
    }

    public void setHeavyListener(Boolean heavyListener) {
        isHeavyListener = heavyListener;
    }

    public Boolean getMiner() {
        return isMiner;
    }

    public void setMiner(Boolean miner) {
        isMiner = miner;
    }

    public Boolean getNeedHelp() {
        return isNeedHelp;
    }

    public void setNeedHelp(Boolean needHelp) {
        isNeedHelp = needHelp;
    }

    public ImageView getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(ImageView userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
}
