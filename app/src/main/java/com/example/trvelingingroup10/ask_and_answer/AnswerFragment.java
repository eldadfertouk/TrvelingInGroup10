package com.example.trvelingingroup10.ask_and_answer;

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
/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFragment extends Fragment implements Serializable {

    private FirebaseFirestore database ;
    private ArrayList<AnswerQuestionClass> answerQuestionClasses;
    private Context context;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_answer, container, false);
        loadDataBase();
        loadTheQuestions();
        return view;
    }

    private void initRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);

        RecyclerView recyclerView = view.findViewById(R.id.show_answer_in_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewQuestionAnswer adapter = new RecyclerViewQuestionAnswer(answerQuestionClasses);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    private void loadDataBase() {

        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

    }

    private void loadTheQuestions(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                answerQuestionClasses =new ArrayList<>();
                database.collection("travelersquestions").document("sf").get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    }
                });
                database.collection("trvelersquestions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                            answerQuestionClasses.add(task.getResult().getDocuments().get(i).toObject(AnswerQuestionClass.class));
                    }
                        initRecyclerView();
                    }
                });
            }
        }).start();
    }


}