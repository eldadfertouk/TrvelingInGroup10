package com.example.trvelingingroup10.tours;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.trvelingingroup10.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class AddShowToursActivity extends AppCompatActivity  {

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
        BottomNavigationView bottomNavigationView=findViewById(R.id.answer_question_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.add_new_tour:
                        transaction.replace(R.id.view_changer_list_or_add_tour,addTourFragment).commit();

                        break;
                    case R.id.show_list_of_tours:
                        transaction.replace(R.id.view_changer_list_or_add_tour,showTourListFragment).commit();

                        break;
                }

                return true;
            }
        });
    }

    private void loadDataBase() {
        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);
    }

}
