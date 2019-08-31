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
        view=inflater.inflate(R.layout.fragment_question, container, false);

        loadDataBase();
        setButtonLiseners();
        return view;
    }



    private void loadDataBase() {
        // TODO: 13/12/2018 מתודה שמאתחלת את האובייקט שלנו מסוג פיירבייס עם הגדרות נחוצות
        database = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

    }




    private void setButtonLiseners() {
        // TODO: 13/12/2018 איתחול של כפתור שבעת לחיצה עליו נשלח את השאלה שלנו אל השרת
        Button button=view.findViewById(R.id.send_me_question_firebase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText text = view.findViewById(R.id.qestion_to_send_firebase);

                database.collection("studentqestions").document(""+new Date()).set(new AnswerQuestionClass("ממתין לתשובה",text.getText().toString(),false)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context,"שאלה נוספה אל מאגר השאלות.",Toast.LENGTH_LONG).show();
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