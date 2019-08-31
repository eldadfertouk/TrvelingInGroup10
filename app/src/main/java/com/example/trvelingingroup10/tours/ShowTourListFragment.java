package com.example.trvelingingroup10.tours;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvelingingroup10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowTourListFragment extends Fragment implements Serializable {

    private FirebaseFirestore database ;
    private ArrayList<Tour> tours;
    private Context context;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_list_of_tours , container, false);


        loadDataBase();
        loadtheQestions();

        return view;
    }



    private void initRecyclerView(){

// TODO: 15/12/2018 איתחול של אובייקט מסוג recycelviev
        // TODO: 15/12/2018 אשר תפקידו הוא להציג לנו כמות מידע מסויימת לפי תבנית שהגדרנו מראש
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);

        RecyclerView recyclerView = view.findViewById(R.id.list_of_tour_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewTours adapter = new RecyclerViewTours(tours);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        // TODO: 15/12/2018  זוהי המתודה הראשונה שנקראת לעבודה כאשר אנחנו נפעיל פרגמנט חדש ואנחנו בעצם נשתמש באובייקט context
        // TODO: 15/12/2018 על מנת לקשר את הפרגמנט שלנו אל הפעילות המכילה אותו
    }

    private void loadDataBase() {
        // TODO: 13/12/2018 מתודה שמאתחלת את האובייקט שלנו מסוג פיירבייס עם הגדרות נחוצות
        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

    }

    private void loadtheQestions() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 15/12/2018 כאן אנחנו ניגשים אל האוסף שקוראים לו studentqestions
                // TODO: 15/12/2018 ולוקחים את כל השאלות שנשאלו וטוענים אותם אל תוך אובייקט מסוג מחלקת תשובות שזוהי מחלקה שיצרתי והיא מייצגת לי אוייבקט של שאלה ותשובה
                tours=new ArrayList<>();
                database.collection("studentqestions").document("sf").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    }
                });
                database.collection("studentqestions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (int i = 0; i <task.getResult().getDocuments().size() ; i++) {
                            tours.add(task.getResult().getDocuments().get(i).toObject(Tour.class));
                        }
                        initRecyclerView();
                    }
                });
            }
        }).start();
    }


}
