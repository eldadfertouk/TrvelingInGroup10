package com.example.trvelingingroup10.ask_and_answer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvelingingroup10.R;

import java.util.ArrayList;

public class RecyclerViewQuestionAnswer extends RecyclerView.Adapter<RecyclerViewQuestionAnswer.ViewHolder> {

    private ArrayList<AnswerQuestionClass> answerQuestionClasses;



    public RecyclerViewQuestionAnswer(ArrayList<AnswerQuestionClass>answerQestionClasses){
        this.answerQuestionClasses=answerQestionClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_answers_questions,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.answer.setText(answerQuestionClasses.get(position).getAnswer());
        holder.question.setText(answerQuestionClasses.get(position).getQuestion());
        if(!answerQuestionClasses.get(position).isAnswered())
            holder.cardView.setCardBackgroundColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return answerQuestionClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        CardView cardView;
        TextView answer,question;



        public ViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.recycler_view_card_view);
            answer=itemView.findViewById(R.id.show_answer_in_recyclerview);
            question=itemView.findViewById(R.id.show_question_in_recyclerview);





        }
    }


}
