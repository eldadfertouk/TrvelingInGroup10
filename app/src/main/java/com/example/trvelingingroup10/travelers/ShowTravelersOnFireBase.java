package com.example.trvelingingroup10.travelers;



import android.os.Bundle;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvelingingroup10.R;

import java.util.ArrayList;

public class ShowTravelersOnFireBase extends AppCompatActivity {


    private ArrayList<String> arraySpinner;
    private ArrayList<Traveler> allTravelersNames;
    private RecyclerView showusers;
    private RecyclerAdapterTravelerFragment recyclerAdapterTravelerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_travelers_on_fire_base);




    }


    private void setTheData(Traveler travelerClass) {
        TextView name, number, area;
        ImageView imageView;
        imageView = findViewById(R.id.image_of_user_in_show_the_users);
        name = findViewById(R.id.user_name_in_show_users);
        area = findViewById(R.id.leivingAddress);
        number = findViewById(R.id.phone_in_show_users);
    /*    name.setText(travelerClass.getClass().getField("displayName").hashCode());
        area.setText(travelerClass.getClass().getField(""));
        number.setText(travelerClass.getTravelerPhoneNumber());
        travelerClass.getImageFromServer(imageView);*/
    }





}
