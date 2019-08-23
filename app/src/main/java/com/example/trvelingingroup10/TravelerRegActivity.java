package com.example.trvelingingroup10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;

import com.example.trvelingingroup10.content.GroupTourContent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelerRegActivity extends AppCompatActivity implements GroupFragment.OnListFragmentInteractionListener,
        View.OnClickListener
{
    TextView currentShowedGroup;
    FragmentContainer containerOfGroups;
    private TextView mTextMessage;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("travelers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        Intent travelerReg = getIntent();

        setContentView( R.layout.activity_traveler_reg );
    //    setContentView( R.layout.fragment_group );
        ButterKnife.bind( this );
        mTextMessage = findViewById( R.id.mTextMessage );

    }


    private void sendTravelerDataToFireBase(Bundle userData) {
        myRef.setValue(userData);

    }

    private void goToMainAppActivity(Bundle userData) {
        Intent mainAppIntet = new Intent( this, MainGuideActivity.class );
        mainAppIntet.putExtras( userData );
        startActivity( mainAppIntet );
        finish();
    }

    @Override
    public void onListFragmentInteraction(GroupTourContent.GroupItem groupItem) {
      //  String currentGroupId=groupItem.groupId.toString();
      //  String currentGroupName=groupItem.groupName.toString();
      //  String currentGroupContent=groupItem.groupContent.toString();
      //  String sb = (currentGroupId+" "+currentGroupName+" "+currentGroupContent);
      //  currentShowedGroup.setText(sb.toString());
        //todo: implement as show morw details
    }

    public void onClick(View view) {
        Bundle userData = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.join_to_this_group:
                addCurrentTravelrToChosenGroup();
                case R.id.sendTravelRegDataBtn:
                sendTravelerDataToFireBase( userData );
                break;
            case R.id.startTravelBtn:
                goToMainAppActivity( userData );
                break;
        }

    }

    private void addCurrentTravelrToChosenGroup() {
        //todo: create method that adds the traveler to the group he picked
    }

    static
    class ViewHolder{
        @BindView(R.id.traveler_name)
        TextView travelerName;
        @BindView(R.id.traveler_group)
        TextView travelerGroup;

    }

}
