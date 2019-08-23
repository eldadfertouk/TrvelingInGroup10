package com.example.trvelingingroup10;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


public class RegClass extends AppCompatActivity implements View.OnClickListener {
    private static String TAG="RegClass";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("RegClassSendsLocation");
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 31.675568, wayLongitude = 34.569321;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private StringBuilder stringBuilder;
    private boolean isContinue = false;
    private boolean isGPS = false;

    public Boolean isGuide = false;
    TextView useridtextfiled, displaynametextfiled, useremailtextfield, phonenumbertextfiled, locationtextfield;
    Button guideRegistrationBtn, travelerRegistrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_class);
        Intent reg = getIntent();
        guideRegistrationBtn = findViewById(R.id.guideRegBtn);
        travelerRegistrationBtn = findViewById(R.id.travelerRegBtn);
        useridtextfiled = findViewById(R.id.userUidFld);
        displaynametextfiled = findViewById(R.id.displayNameFld);
        useremailtextfield = findViewById(R.id.userEmailFld);
        phonenumbertextfiled = findViewById(R.id.phoneNumberFld);
        locationtextfield = findViewById(R.id.locationFld);
        guideRegistrationBtn.setOnClickListener(this);
        travelerRegistrationBtn.setOnClickListener(this);
        try {
            String uid = reg.getStringExtra("user id");
            String displayname = reg.getStringExtra("display name");
            String useremail = reg.getStringExtra("user email");
            String phounenumber = reg.getStringExtra("phone number");
            useridtextfiled.setText(uid);
            displaynametextfiled.setText(displayname);
            useremailtextfield.setText(useremail);
            phonenumbertextfiled.setText(phounenumber);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RegClass", e + "  ");
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds

        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });

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
        getLocation();
        myRef.setValue(locationtextfield.toString());
        Toast.makeText(this,"send data to fire base",Toast.LENGTH_LONG).show();
      //  btnLocation.setOnClickListener(this);
      //  btnContinueLocation.setOnClickListener(this);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                phonenumbertextfiled.setText(value);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Toast.makeText(this,"read from fire base",Toast.LENGTH_LONG).show();
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

    public void onClick(View view) {
        Bundle regDataFromLogin = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.travelerRegBtn:
                goToTravelerReGActivity( regDataFromLogin );
                break;
            case R.id.guideRegBtn:
                isGuide = true;
                String userType = "guide";
                goToGuideRegActivity( regDataFromLogin );
                break;
        }
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

}
