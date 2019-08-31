package com.example.trvelingingroup10.ask_and_answer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.trvelingingroup10.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class AskAndAnswerActivity extends AppCompatActivity {

    private FirebaseFirestore database ;
    private AnswerFragment answerFragment;
    private QuestionFragment questionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_and_answer);
        answerFragment = new AnswerFragment();
        questionFragment = new QuestionFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.answers_question_recycler_view,questionFragment).commit();
        loadDataBase();
        setTheFragmentSwitch();
    }
    private void setTheFragmentSwitch() {

        BottomNavigationView bottomNavigationView=findViewById(R.id.answer_question_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.ask_question:
                        transaction.replace(R.id.answer_to_question_change, answerFragment).commit();

                        break;
                    case R.id.show_answers:
                        transaction.replace(R.id.answer_to_question_change, questionFragment).commit();

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
