package com.example.trvelingingroup10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainer;

import com.example.trvelingingroup10.content.TravelerContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.makeText;

public class GuideRegActivity extends AppCompatActivity implements TravelerFragment.OnListFragmentInteractionListener,
        View.OnClickListener {
    private static final String TAG="GuideRegActivity";
    private TextView mTextMessage,guideName,spokentang;
    private Button addNewGroupBtn,addNewGuideBtn,startTourAsGuide;
    private BottomNavigationView navView;
    private ConstraintLayout regGuideConstraintLayoutContainer;
    private Boolean isLocalGuide;
    FragmentContainer listOfTravelers;
    private Guide guide;
    //private TextView currentTraveler;

    static
    class ViewHolder {
        @BindView(R.id.guideNameFld)
        TextView guideNameFld;
        @BindView(R.id.spokentangfld)
        TextView spokentangfld;
        @BindView(R.id.localGuideCb)
        CheckBox localGuideCb;
        @BindView(R.id.addNewGroupBtn)
        Button addNewGroupBtn;
        @BindView(R.id.addNewGuideBtn)
        Button addNewGuideBtn;
        @BindView(R.id.startTourGuideBtn)
        Button startTourGuideBtn;
        @BindView(R.id.nav_view)
        BottomNavigationView navView;

        @BindView(R.id.travelersListFrag)
        android.widget.Adapter travelersListFrag;
        @BindView(R.id.regGuideContainer)
        ConstraintLayout regGuideContainer;
        ViewHolder(View view) {
            ButterKnife.bind( this, view );
        }
    }







    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("guides");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Intent guideReg = getIntent();
        guide = new Guide();
        guide.setGuideUid(guideReg.getStringExtra("user id"));
        guide.setDisplayName(guideReg.getStringExtra("display name"));
        guide.setFullName(guideReg.getStringExtra("full name"));
        guide.setGuideEmail(guideReg.getStringExtra("user email"));
        setContentView( R.layout.activity_guide_reg );
//        setContentView( R.layout.fragment_traveler );
        ButterKnife.bind( this );

        BottomNavigationView navView = findViewById( R.id.nav_view );
        mTextMessage = findViewById( R.id.mTextMessage );

        try {
            navView.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        }
        catch (Exception e){
            makeText( this, "error:  "+ e.getMessage().toString() , Toast.LENGTH_LONG ).show();

        }

    }

    private void sendGuideRegDataToFireBase(){

        //todo:build regestrasion data package for guide (+group=guide must open or join agroup as tem leader in order to start)
    }
    @Override
    public void onListFragmentInteraction(TravelerContent.TravelerItem travelerItem) {

    }



    private void goToMainAppAsGuide(Guide guide) {
        //todo: find out what data should be pass trow to next screen
        //Bundle guideBumdle = new Bundle();
        //guideBumdle.putBundle("guideObj",guide);
        Intent moveToMainAppAsGuide = new Intent( this, MainGuideActivity.class );

        startActivity( moveToMainAppAsGuide );
        finish();
    }

    private void addNewGuideDataToFireBase(Guide guide) {

        try {
            myRef.setValue(guide);
            Toast.makeText(this,"send guide to database",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Log.e(TAG, "addNewGuideDataToFireBase: ");
        }

    }

    private void addNewGroupToFireBase(Bundle guideData) {
        //todo: build add new group screen
        //todo:add group to firebase
    }


    public void onClick(View view) {
        Bundle guideData = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.addNewGroupBtn:
                Toast.makeText(this,"add new group to fire base",Toast.LENGTH_LONG).show();
                addNewGroupToFireBase(guideData);
                break;
            case R.id.addNewGuideBtn:
                Toast.makeText(this,"add new guide to fire base",Toast.LENGTH_LONG).show();
                addNewGuideDataToFireBase(guide);
                break;
            case R.id.startTourGuideBtn:
                Toast.makeText(this,"Start app to fire base",Toast.LENGTH_LONG).show();
                goToMainAppAsGuide(guide);
                break;
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText( R.string.title_home );
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText( R.string.title_dashboard );
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText( R.string.title_notifications );
                    return true;
            }
            return false;
        }
    };
}
