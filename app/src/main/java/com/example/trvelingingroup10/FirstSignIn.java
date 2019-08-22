package com.example.trvelingingroup10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.makeText;

public class FirstSignIn extends AppCompatActivity {

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build() );


    @BindView(R.id.imageViewLogo)
    ImageView logo;
    @BindView( R.id.timerturtle )
    ImageView turtuletimer;



    private SharedPreferences SPSaveUserDataToLocalStorage;
    private SharedPreferences.Editor SPEditor;
    private String displayName,uId,userEmail,userPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first_sign_in );
        setTheSharedPreferences();
        ButterKnife.bind( this );
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders( providers )
                            .build(),
                    0 );
        } else {
            goToNextRegActivity(user);
        }


    }

    private void setTheSharedPreferences() {
        SPSaveUserDataToLocalStorage=getSharedPreferences("type",MODE_PRIVATE);
    }


    public void goToNextRegActivity(FirebaseUser user) {
        makeText( this, "user id:" + user.getUid(), Toast.LENGTH_LONG ).show();

        displayName = user.getDisplayName();
        userEmail = user.getEmail();
        userPhoneNumber = user.getPhoneNumber();
        uId = user.getUid();
        Bundle regBundle = new Bundle(  );
        regBundle.putString( "user id", uId );
        regBundle.putString( "display name",displayName );
        regBundle.putString( "user email",userEmail );
        regBundle.putString( "phone number",userPhoneNumber );
        saveTheData(regBundle);
        Intent regActivity = new Intent( this, RegClass.class ) ;
        regActivity.putExtras( regBundle );
        startActivity( regActivity );
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == 0) {


            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                makeText( this, "made a successful login "+user.getDisplayName().toString(), Toast.LENGTH_LONG ).show();
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
    private void saveTheData(Bundle userdata) {

        SPEditor=SPSaveUserDataToLocalStorage.edit();
        SPEditor.putString("userUID", userdata.get("user id").toString());
        SPEditor.putString("displayName", userdata.get("display name").toString());
        SPEditor.putString("userEmail", userdata.get("user email").toString());
        SPEditor.putString("userPhone", userdata.get("phone number").toString());

        SPEditor.apply();


    }
}
