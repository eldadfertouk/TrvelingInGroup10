package com.example.trvelingingroup10;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowTravelersOnFireBase extends AppCompatActivity {


    private ArrayList<String> arraySpinner;
    private ArrayList<Traveler> allTravelersNames;
    private Spinner spinner;

    // layout updater
    private SwipeRefreshLayout swiperRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_travelers_on_fire_base);
        setTheRefreshMethod();
        defineTheSpinner();


    }

    private void setTheRefreshMethod() {

        swiperRefreshLayout = findViewById(R.id.sweper);
        swiperRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             //refresh page listener reacts on puling finger on list
                setTheSpinnerItemsFromServer();

            }
        });


    }

    private void defineTheSpinner() {
        setTheSpinnerItemsFromServer();
        setTheSpinnerLiseners();

    }

    private void setTheSpinnerItemsFromServer() {
        // load user name from dat bast to container
        FireBaseInit.getFireBaseInit().getDatabase().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                arraySpinner = new ArrayList<>();
                allTravelersNames = new ArrayList<>();
                for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                    Traveler travelerClass = task.getResult().getDocuments().get(i).toObject(Traveler.class);
                    arraySpinner.add(travelerClass.getFullName());
                    allTravelersNames.add(travelerClass);
                }

                // load all data components to the view adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowTravelersOnFireBase.this,
                        android.R.layout.simple_spinner_item, arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                //makes load icon unvisible after load is finish
                swiperRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setTheSpinnerLiseners() {
        // find the item that was pushed and show it
        spinner = findViewById(R.id.swiperefresh );
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                setTheData(allTravelersNames.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setTheData(Traveler travelerClass) {
        TextView name, number, area;
        ImageView imageView;
        imageView = findViewById(R.id.image_of_user_in_show_the_users);
        name = findViewById(R.id.user_name_in_show_users);
        area = findViewById(R.id.leivingAddress);
        number = findViewById(R.id.phone_in_show_users);
        name.setText(travelerClass.getFullName());
        area.setText(travelerClass.getDisplayName());
        number.setText(travelerClass.getTravelerPhoneNumber());
        travelerClass.getImageFromServer(imageView);
    }





}
