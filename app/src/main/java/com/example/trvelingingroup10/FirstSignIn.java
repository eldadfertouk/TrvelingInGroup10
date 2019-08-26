package com.example.trvelingingroup10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import static android.widget.Toast.makeText;

public class FirstSignIn extends AppCompatActivity {
    private final static String TAG="FirstSignIn";
    private BasicAppUser appUser;

    @BindView(R.id.imageViewLogo)
    ImageView logo;
    @BindView( R.id.timerturtle )
    ImageView turtuletimer;

    String displayName,uId,userEmail,userPhoneNumber;

    //fireBase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myFirstSignInRef = database.getReference("signedin");


//local sher pref

    private SharedPreferences SPSaveUserDataToLocalStorage;
    SharedPreferences.Editor SPEditor;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build() );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first_sign_in );
        ButterKnife.bind( this );
        //check if user is authenticated user on fireBase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //on user not exist run REG
        if (user == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders( providers )
                            .build(),
                    0 );
        } else {

            crateAppUser(user);
            goToNextRegActivity(user);
        }
    }

    private void crateAppUser(FirebaseUser user) {
        appUser = new BasicAppUser();
        appUser.setuId(user.getUid());
        appUser.setFullName(user.getDisplayName());
        appUser.setEmailAddress(user.getEmail());
        appUser.setPhoneNumber(user.getPhoneNumber());
    }


    private void setTheSharedPreferences() {
//todo: make it work with the calls Shared pref
     /*   String uid = SharedPref.read(SharedPref.UID,null);
        if (uid == null){
            SharedPref.init(getApplicationContext());
            Log.d(TAG, "setTheSharedPreferences: " );
        }*/
        SPSaveUserDataToLocalStorage=getSharedPreferences("userAuth",MODE_PRIVATE);
    }

    public void goToNextRegActivity(FirebaseUser user) {
        //shows user id on screen
        makeText( this, "user id:" + user.getUid(), Toast.LENGTH_SHORT ).show();
        setTheSharedPreferences();
        // update local variables with user data
        displayName = user.getDisplayName();
        userEmail = user.getEmail();
        userPhoneNumber = user.getPhoneNumber();
        uId = user.getUid();
        //build user data bundle for registration
        Bundle regBundle = new Bundle(  );
        regBundle.putString( "user id", uId );
        regBundle.putString( "display name",displayName );
        regBundle.putString( "user email",userEmail );
        regBundle.putString( "phone number",userPhoneNumber );
         //saves  user registration data to local file and to firebase
        saveTheDataToLocalSherdPref(regBundle);
        saveNewUserRegDataToFireBase(appUser);
        //build and run move to next page intent
        Intent regActivity = new Intent( this, RegClass.class ) ;
        regActivity.putExtras( regBundle );
        startActivity( regActivity );
        finish();
    }
    //save data to fire base
    //todo: make it work with ReadWrit Class
    //todo: save the data as java object
    private void saveNewUserRegDataToFireBase(BasicAppUser appUser) {
        myFirstSignInRef.child("appusers").child(uId.toString()).setValue(appUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
              //  makeText( this, "made a successful login "+user.getDisplayName().toString(), Toast.LENGTH_LONG ).show();
                goToNextRegActivity(user);
                // ...
            } else {
                makeText( this, "Error while login in ", Toast.LENGTH_LONG ).show();
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

            }
        }
    }
    private void saveTheDataToLocalSherdPref(Bundle userdata) {
//todo:make it work with class instead of method
    /*    SharedPref.write(SharedPref.UID,userdata.get("user id").toString());
        SharedPref.write(SharedPref.PHONENUMBER,userdata.get("phone number").toString());
        SharedPref.write(SharedPref.DISPLAYNAME,userdata.get("display name").toString());
        SharedPref.write(SharedPref.EMAIL,userdata.get("user email").toString());
*/
        SPEditor=SPSaveUserDataToLocalStorage.edit();
        SPEditor.putString("userUID", userdata.get("user id").toString());
        SPEditor.putString("displayName", userdata.get("display name").toString());
        SPEditor.putString("userEmail", userdata.get("user email").toString());
        SPEditor.putString("userPhone", userdata.get("phone number").toString());

        SPEditor.apply();


    }
}
