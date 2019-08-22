package com.example.trvelingingroup10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;

import com.example.trvelingingroup10.content.GroupTourContent;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TravelerRegActivity extends AppCompatActivity implements GroupFragment.OnListFragmentInteractionListener,
        View.OnClickListener
{

    TextView currentShowedGroup;
    FragmentContainer containerOfGroups;
    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_traveler_reg );
    //    setContentView( R.layout.fragment_group );
        ButterKnife.bind( this );
        currentShowedGroup = findViewById(R.id.current_group_txt_fld);
        mTextMessage = findViewById( R.id.mTextMessage );


    }


    private void sendTravelerDataToFireBase(Bundle userData) {
        //todo:send travelers data to fire base
    }

    private void goToMainAppActivity(Bundle userData) {
        Intent mainAppIntet = new Intent( this, MainActivity.class );
        mainAppIntet.putExtras( userData );
        startActivity( mainAppIntet );
        finish();
    }

    @Override
    public void onListFragmentInteraction(GroupTourContent.GroupItem groupItem) {
        String currentGroupId=groupItem.groupId.toString();
        String currentGroupName=groupItem.groupName.toString();
        String currentGroupContent=groupItem.groupContent.toString();
        String sb = (currentGroupId+" "+currentGroupName+" "+currentGroupContent);
        currentShowedGroup.setText(sb.toString());
        Toast.makeText(this,"drek gadol ",Toast.LENGTH_LONG).show();
    }

    public void onClick(View view) {
        Bundle userData = getIntent().getExtras();
        switch (view.getId()) {
            case R.id.sendTravelRegDataBtn:
                sendTravelerDataToFireBase( userData );
                break;
            case R.id.startTravelBtn:
                goToMainAppActivity( userData );
                break;
        }

    }
    static
    class ViewHolder{
        @BindView(R.id.traveler_name)
        TextView travelerName;
        @BindView(R.id.traveler_group)
        TextView travelerGroup;

    }

}
