package com.example.trvelingingroup10.ask_and_answer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvelingingroup10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Date;


public class QuestionFragment extends Fragment {


    private View view;
    private FirebaseFirestore database ;
    private Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view = inflater.inflate(R.layout.fragment_question, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.show_question_in_recyclerview);
        recyclerView.setLayoutManager(layoutManager);

        loadDataBase();
        setButtonListeners();
        return view;
    }


    private void loadDataBase() {
        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

    }


    private void setButtonListeners() {
        Button button=view.findViewById(R.id.send_my_question_firebase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText text = view.findViewById(R.id.qestion_to_send_firebase_txt_fld);

                database.collection("travelersquestions").document(""+new Date())
                        .set(new AnswerQuestionClass("ממתין לתשובה",text.getText()
                                .toString(),false)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context,"השאלה נשלחה למדריך ותענה בקרוב.",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}