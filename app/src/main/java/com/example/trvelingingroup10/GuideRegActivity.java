package com.example.trvelingingroup10;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.trvelingingroup10.content.TravelerContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.makeText;

public class GuideRegActivity extends AppCompatActivity implements TravelerFragment.OnListFragmentInteractionListener,
        View.OnClickListener {

    private TextView mTextMessage;
    //private TextView currentTraveler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Intent guideReg = getIntent();
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
        //String currentTravelerId = travelerItem ;
        //String currentTravelerGroup = travelerItem. travelerGroup.toString();
        //String currentTravelerContent = travelerItem. content.toString();
        //StringBuilder sb = new StringBuilder(currentTravelerId+" "+currentTravelerGroup+" "+currentTravelerContent);
        //currentTraveler.setText(sb.toString());
    }



    private void goToMainAppAsGuide(Bundle guideData) {
        Intent moveToMainAppAsGuide = new Intent( this,MainActivity.class );
        moveToMainAppAsGuide.putExtras( guideData );
        startActivity( moveToMainAppAsGuide );
    }

    private void addNewGuideDataToFireBase(Bundle guideData) {
        //todo: add new guide to fire base
    }

    private void addNewGroupToFireBase(Bundle guideData) {
        //todo:add group to firebase
    }

    @Override
    public void onClick(View view) {
        Bundle guideData = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.addNewGroupBtn:
                addNewGroupToFireBase(guideData);
                break;
            case R.id.addNewGuideBtn:
                addNewGuideDataToFireBase(guideData);
                break;
            case R.id.startTourGuideBtn:
                goToMainAppAsGuide(guideData);
                break;
        }


    }

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
