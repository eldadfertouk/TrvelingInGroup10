package com.example.trvelingingroup10.tours;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;

import com.example.trvelingingroup10.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class AddShowToursActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore database;
    private AddTourFragment addTourFragment;
    private ShowTourListFragment showTourListFragment;
    private Button addTour,showList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show_tours);


        addTourFragment =new AddTourFragment();
        showTourListFragment =new ShowTourListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.view_changer_list_or_add_tour, addTourFragment).commit();
        loadDataBase();

    }

    private void setTheFragmentSwitch() {


    }

    private void loadDataBase() {
        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int id = v.getId();
        switch (id){
            case R.id.add_new_tour_btn:
                transaction.replace(R.id.view_changer_list_or_add_tour, addTourFragment).commit();
                break;
            case R.id.show_list_btn:
                transaction.replace(R.id.view_changer_list_or_add_tour, showTourListFragment).commit();
                break;
        }
    }
}
