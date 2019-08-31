package com.example.trvelingingroup10.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trvelingingroup10.Groups.GroupFragment;
import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.content.GroupContent;
import com.example.trvelingingroup10.travelers.Traveler;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

public class TravelerRegActivity extends AppCompatActivity implements GroupFragment.OnListFragmentInteractionListener,
        View.OnClickListener{
    private Traveler traveler;
    View containerOfGroups;
    private TextView mTextMessage,travelerName,travelerGroup;
    private Button sendTravelerDataBtn,startAppAsTraveler;
    private CheckBox deffCB,blindCB,kosherCB, realignsCB,minerCB,needHelpCB;
    private Boolean isDef,isBlind,isNeedHelp,isMiner,isKosherEat,isRealigns;

    //fire base settings
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myTravelerRef = database.getReference("travelers");

    //sharedpref
    //todo:make it work with shared class
    private SharedPreferences SPToLocalStorage;
    SharedPreferences.Editor SPEditor;
    //page create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_traveler_reg );
        Intent travelerReg = getIntent();
        BottomNavigationView navView = findViewById( R.id.nav_view );

        String userId =  travelerReg.getStringExtra("user id");
        String displayName = travelerReg.getStringExtra("display name");
        String userEmail = travelerReg.getStringExtra("user email");
        String phoneNumber = travelerReg.getStringExtra("phone number");

        traveler = new Traveler(userId,displayName,userEmail,phoneNumber);



        //ButterKnife.bind( this );
        mTextMessage = findViewById( R.id.mTextMessage );
        travelerGroup = findViewById(R.id.traveler_group);
        travelerName = findViewById(R.id.traveler_name);
        sendTravelerDataBtn  = findViewById(R.id.sendTravelRegDataBtn);
        startAppAsTraveler  = findViewById(R.id.startTravelBtn);
        sendTravelerDataBtn.setOnClickListener(this::onClick);
        startAppAsTraveler.setOnClickListener(this::onClick);
        deffCB = findViewById(R.id.is_Deff_CB);
        deffCB.setChecked(false);

        blindCB = findViewById(R.id.is_Blaind_CB);

        kosherCB = findViewById(R.id.is_Kosher_CB);
        realignsCB = findViewById(R.id.is_Realigns_CB);
        minerCB = findViewById(R.id.is_Miner_CB);
        needHelpCB = findViewById(R.id.is_Need_Help);
        //deffCB.setOnCheckedChangeListener();


        //containerOfGroups =  findViewById(R.layout.fragment_group);
        //todo:check how to inflait the frag

     //   saveTheDataToLocalSharedPref(traveler);
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
*/
    //buttons of page
    public void onClick(View view) {
        Intent travelerRegData = getIntent();
        Bundle travelerData = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.join_to_this_group_Btn:
                makeText( this, "join  this group ", Toast.LENGTH_LONG ).show();
                addCurrentTravelrToChosenGroup();
                break;
            case R.id.sendTravelRegDataBtn:
                makeText( this, "send traveler to firebase  ", Toast.LENGTH_LONG ).show();
                updateTravelerDataOnFireBase(travelerRegData);
                //sendTravelerDataToFireBase( userData );
                break;
            case R.id.startTravelBtn:
                makeText( this, "go to start  ", Toast.LENGTH_LONG ).show();
                goToMainAppActivity( travelerData );
                break;
        }

    }

    private void updateTravelerDataOnFireBase(Intent travelerRegData) {
        String travelerUID =travelerRegData.getStringExtra("user id");
        myTravelerRef.child("guides").child(travelerUID).setValue(traveler);
        //myTravelerRef.setValue(userData);

    }

    public void onCheckedChangeListener(View v){
        boolean checked = ((CheckBox) v).isChecked();

        // Check which checkbox was clicked
        switch(v.getId()) {
            case R.id.is_Blaind_CB :
                if (checked){
                    traveler.setHeavySited(true);
                    isBlind = true;}
                else{
                    traveler.setHeavySited(false);
                    isBlind = false;}
                break;
            case R.id.is_Deff_CB:
                if (checked){
                    traveler.setHeavyListener(true);
                    isDef=true;}
                else{
                    traveler.setHeavyListener(false);
                    isDef=false;}
                break;
            case R.id.is_Kosher_CB:
                if (checked){
                    traveler.setEatKosherFood(true);
                    isKosherEat=true;}
                else{
                    traveler.setEatKosherFood(false);
                    isKosherEat=false;}
                break;
            case R.id.is_Realigns_CB:
                if (checked){
                    traveler.setPracticeRealign(true);
                    isRealigns=true;}
                else{
                    traveler.setPracticeRealign(false);
                    isRealigns=false;}
                break;
            case R.id.is_Miner_CB:
                if (checked){
                    traveler.setMiner(true);
                    isMiner=true;}
                else{
                    traveler.setMiner(false);
                    isMiner=false;}
                break;
            case R.id.is_Need_Help:
                if (checked){
                    traveler.setNeedHelp(true);
                    isNeedHelp=true;}
                else{
                    traveler.setNeedHelp(false);
                    isNeedHelp=false;}
                break;
            // TODO: allcheckbox eroors
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.is_Blaind_CB :
                if (checked){
                    traveler.setHeavySited(true);
                    isBlind = true;}
            else{
                traveler.setHeavySited(false);
                isBlind = false;}
                break;
            case R.id.is_Deff_CB:
                if (checked){
                    traveler.setHeavyListener(true);
                    isDef=true;}
            else{
                traveler.setHeavyListener(false);
                isDef=false;}
                break;
            case R.id.is_Kosher_CB:
                if (checked){
                    traveler.setEatKosherFood(true);
                    isKosherEat=true;}
                else{
                    traveler.setEatKosherFood(false);
                    isKosherEat=false;}
                break;
            case R.id.is_Realigns_CB:
                if (checked){
                    traveler.setPracticeRealign(true);
                    isRealigns=true;}
                else{
                    traveler.setPracticeRealign(false);
                    isRealigns=false;}
                break;
            case R.id.is_Miner_CB:
                if (checked){
                    traveler.setMiner(true);
                    isMiner=true;}
                else{
                    traveler.setMiner(false);
                    isMiner=false;}
                break;
            case R.id.is_Need_Help:
                if (checked){
                    traveler.setNeedHelp(true);
                    isNeedHelp=true;}
                else{
                    traveler.setNeedHelp(false);
                    isNeedHelp=false;}
                break;
            // TODO: allcheckbox eroors
        }
    }
    //save traveler date to firebase
    private void sendTravelerDataToFireBase(Traveler traveler) {

    }
    //start application as traveler
    private void goToMainAppActivity(Bundle userData) {
        Intent mainAppIntet = new Intent( this, MainGuideActivity.class );
        mainAppIntet.putExtras( userData );
        startActivity( mainAppIntet );
        finish();
    }


    private void addCurrentTravelrToChosenGroup() {
        //todo: create method that adds the traveler to the group he picked
    }

    //frag implementation group list
    @Override
    public void onListFragmentInteraction(GroupContent.GroupItem groupItem) {
        //  String currentGroupId=groupItem.groupId.toString();
        //  String currentGroupName=groupItem.groupName.toString();
        //  String currentGroupContent=groupItem.groupContent.toString();
        //  String sb = (currentGroupId+" "+currentGroupName+" "+currentGroupContent);
        //  currentShowedGroup.setText(sb.toString());
        //todo: implement as show more details
    }
    private void saveTheDataToLocalSharedPref(Traveler traveler) {
        SPToLocalStorage=getSharedPreferences("TravelerReg",MODE_PRIVATE);
//todo:make it work with class instead of method
    /*    SharedPref.write(SharedPref.UID,userdata.get("user id").toString());
        SharedPref.write(SharedPref.PHONENUMBER,userdata.get("phone number").toString());
        SharedPref.write(SharedPref.DISPLAYNAME,userdata.get("display name").toString());
        SharedPref.write(SharedPref.EMAIL,userdata.get("user email").toString());
*/
        SPEditor=SPToLocalStorage.edit();
        SPEditor.putString("userUID", traveler.getUserUid());
        //todo: build all data to sherd pref
        /*
        SPEditor.putString("userType", traveler.getFullName());
        SPEditor.putString("limits", userdata.get("limits").toString());
        SPEditor.putString("location", userdata.get("user location").toString());
*/
        SPEditor.apply();


    }
}
