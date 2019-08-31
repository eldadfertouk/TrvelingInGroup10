package com.example.trvelingingroup10.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.trvelingingroup10.content.BasicAppUser;
import com.example.trvelingingroup10.map_and_gps.GpsUtils;
import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.guides.Guide;
import com.example.trvelingingroup10.travelers.Traveler;
import com.example.trvelingingroup10.utils.AppConstants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;


public class RegClass extends AppCompatActivity implements View.OnClickListener {

    private static String TAG="RegClass";
    private static String UID;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRegClassRef = database.getReference("RegClass");
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private StringBuilder stringBuilder;
    private boolean isContinue = false;
    private boolean isGPS = false;
    private Traveler traveler;
    private Guide guide;
    private BasicAppUser basicUser;
    public Boolean isGuide = false;
    TextView useridtextfiled, displaynametextfiled, useremailtextfield, phonenumbertextfiled, locationtextfield,firstNametextfield,lastNametextfield;
    Button guideRegistrationBtn, travelerRegistrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_class);
        Intent regClass = getIntent();
        buildBasicUser(regClass);
        createLocationCallBack();
        createScreenWithBasickUserData(basicUser);
        defineAndSaveLocation();
        readFromFireBase();


        getLocation();
    }

    private void createScreenWithBasickUserData(BasicAppUser basicUser) {
        //buttons
        guideRegistrationBtn = findViewById(R.id.guideRegBtn);
        travelerRegistrationBtn = findViewById(R.id.travelerRegBtn);
        guideRegistrationBtn.setOnClickListener(this);
        travelerRegistrationBtn.setOnClickListener(this);

        //basic user fields filled
        useridtextfiled = findViewById(R.id.userUidFld);
        displaynametextfiled = findViewById(R.id.displayNameFld);
        useremailtextfield = findViewById(R.id.userEmailFld);
        phonenumbertextfiled = findViewById(R.id.phoneNumberFld);
        locationtextfield = findViewById(R.id.locationFld);
        lastNametextfield = findViewById(R.id.lastNameFld);
        firstNametextfield = findViewById(R.id.firstNameFld);
        String uid =  basicUser.getuId();
        String displayname = basicUser.getFullName();
        String useremail = basicUser.getEmailAddress();
        String phounenumber = basicUser.getPhoneNumber();
        String lat = Double.toString(wayLatitude);
        String lon = Double.toString(wayLongitude);
        String loc=lat+":"+lon;
        useridtextfiled.setText(uid);
        displaynametextfiled.setText(displayname);
        useremailtextfield.setText(useremail);
        phonenumbertextfiled.setText(phounenumber);
        locationtextfield.setText(loc);
        //update basic user with his current location
        basicUser.setLat(wayLatitude);
        basicUser.setLon(wayLongitude);
    }

    private void buildBasicUser(Intent regClass) {
        UID = regClass.getStringExtra("user id");
        String fullName = regClass.getStringExtra("full name");
        String emailAddr = regClass.getStringExtra("user email");
        String phoneNumber = regClass.getStringExtra("phone number");
        basicUser = new BasicAppUser(UID,fullName,emailAddr,phoneNumber,0.0,0.0);
    }

    private void readFromFireBase() {

        myRegClassRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {

                    String value = dataSnapshot.getValue(String.class);
                    phonenumbertextfiled.setText(value);
                    Log.d(TAG, "Value is: " + value);
                }
                catch (Exception e){
                    Log.d(TAG, "try to read from fire base and get back hashmap: " );
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Toast.makeText(this,"read from fire base",Toast.LENGTH_LONG).show();
    }

    private void createLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        if (!isContinue) {
                            locationtextfield.setText(String.format(Locale.ENGLISH, "%s - %s", wayLatitude, wayLongitude));
                        } else {
                            stringBuilder.append(wayLatitude);
                            stringBuilder.append("-");
                            stringBuilder.append(wayLongitude);
                            stringBuilder.append("\n\n");
                            locationtextfield.setText(stringBuilder.toString());
                        }
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };

    }

    private void defineAndSaveLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds
        // check and alert user to turn GPS on
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });
        getLocation();
    }



    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(RegClass.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(RegClass.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegClass.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.LOCATION_REQUEST);

        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(RegClass.this, location -> {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        locationtextfield.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                });
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (isContinue) {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    } else {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(RegClass.this, location -> {
                            if (location != null) {
                                wayLatitude = location.getLatitude();
                                wayLongitude = location.getLongitude();
                                locationtextfield.setText(String.format( Locale.US, "%s - %s", wayLatitude, wayLongitude));
                            } else {
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }
    @Override
    public void onClick(View view) {
        Intent regClass = getIntent();
        Bundle regDataFromLogin = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.travelerRegBtn:
                createTravelerObject(regClass);
                goToTravelerReGActivity( regDataFromLogin );
                break;
            case R.id.guideRegBtn:
                createGuideObject(regClass);
                isGuide = true;
           //     myRegClassRef.setValue(guide);
                 goToGuideRegActivity( regDataFromLogin );
                break;
        }
    }
// new guide object create and saved to firebase
    private void createGuideObject(Intent reg) {
        Guide guide = new Guide();
        guide.setUser(basicUser);
        guide.setFullName(firstNametextfield.getText().toString()+" "+lastNametextfield.getText().toString());
        guide.setDisplayName(displaynametextfiled.getText().toString());
        guide.setGuideUid(useridtextfiled.getText().toString());
        myRegClassRef.child("guides").child(UID).setValue(guide);
    }
//create new guide and save to fire base
    private void createTravelerObject(Intent reg) {

        Traveler traveler = new Traveler();
        traveler.setUser(basicUser);
        traveler.setFullName(reg.getStringExtra("display name"));
        traveler.setUserUid(reg.getStringExtra("user id"));
        traveler.setUserEmail(reg.getStringExtra("user email"));
        traveler.setPhoneNumber(reg.getStringExtra("phone number"));
        traveler.setDateOfBirth("25/10/2019");
        traveler.setMiner(true);
        traveler.setTravelerGroup("groupTEN");
        //todo:check why dont writh to fire base
        try{
            myRegClassRef.child("travelers").child(UID).setValue(traveler);
        }catch (Exception e){
            Log.e(TAG, "createTravelerObject: ",e );}
       //

    }




    private void goToGuideRegActivity(Bundle regData) {
        Intent guideRegistrationIntet = new Intent( this, GuideRegActivity.class );
        guideRegistrationIntet.putExtras( regData );
        startActivity( guideRegistrationIntet );
        finish();
    }

    private void goToTravelerReGActivity(Bundle regData) {
        Intent travelerRegistrationIntent = new Intent( this, TravelerRegActivity.class );
        travelerRegistrationIntent.putExtras( regData );
        startActivity( travelerRegistrationIntent );
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
