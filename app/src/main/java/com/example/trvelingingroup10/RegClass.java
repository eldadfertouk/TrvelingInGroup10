package com.example.trvelingingroup10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


public class RegClass extends AppCompatActivity implements View.OnClickListener {

    public Boolean isGuide = false;
    TextView useridtextfiled,displaynametextfiled,useremailtextfield,phonenumbertextfiled;
    Button guideRegistrationBtn,travelerRegistrationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Intent reg = getIntent();
        setContentView( R.layout.activity_reg_class );
        guideRegistrationBtn = findViewById(R.id.guideRegBtn);
        travelerRegistrationBtn = findViewById(R.id.travelerRegBtn);
        useridtextfiled = findViewById(R.id.userUidFld);
        displaynametextfiled = findViewById(R.id.displayNameFld);
        useremailtextfield = findViewById(R.id.userEmailFld);
        phonenumbertextfiled = findViewById(R.id.phoneNumberFld);
        guideRegistrationBtn.setOnClickListener(this);
        travelerRegistrationBtn.setOnClickListener(this);


        try {

            String uid = reg.getStringExtra( "user id" );
            String displayname = reg.getStringExtra( "display name" );
            String useremail = reg.getStringExtra( "user email" );
            String phounenumber = reg.getStringExtra( "phone number" );

            useridtextfiled.setText( uid );

            displaynametextfiled.setText( displayname );

            useremailtextfield.setText(useremail);

            phonenumbertextfiled.setText(phounenumber);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e( "RegClass", e + "  " );
        }

    }


    private void goToGuideRegActivity(Bundle regData) {
        Intent guideRegistraionIntet = new Intent( this, GuideRegActivity.class );
        guideRegistraionIntet.putExtras( regData );
        startActivity( guideRegistraionIntet );
        finish();
    }

    private void goToTravelerReGActivity(Bundle regData) {
        Intent travelerRegistrationIntent = new Intent( this, TravelerRegActivity.class );
        travelerRegistrationIntent.putExtras( regData );
        startActivity( travelerRegistrationIntent );
        finish();
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



}
