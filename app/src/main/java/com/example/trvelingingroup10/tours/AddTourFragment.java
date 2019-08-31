package com.example.trvelingingroup10.tours;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trvelingingroup10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTourFragment extends Fragment implements Serializable {



    private FirebaseFirestore dateBase;
    private ArrayList<Tour>tourClasses;
    private Context context;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_tour, container, false);

        loadDataBase();
        loadToursFromDataBase();


        return view;
    }
    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        RecyclerView recyclerView  = view.findViewById(R.id.list_of_tour_recycler_view );
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewTours adapter = new RecyclerViewTours(tourClasses);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    private void loadDataBase() {
        dateBase = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true).build();
        dateBase.setFirestoreSettings(settings);
    }

    private void loadToursFromDataBase() {
    new Thread(new Runnable() {
        @Override
        public void run() {
        tourClasses=new ArrayList<>();
        dateBase.collection("tours").document("toursample").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        });
        dateBase.collection("tours").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(int i = 0;i < task.getResult().getDocuments().size();i++){
                    tourClasses.add(task.getResult().getDocuments().get(i).toObject(Tour.class));
                }
                    initRecyclerView();
            }
        });
        }
    }).start();
    }


}
