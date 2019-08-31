package com.example.trvelingingroup10.tours;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvelingingroup10.R;

import java.util.ArrayList;

public class RecyclerViewTours extends RecyclerView.Adapter<RecyclerViewTours.ViewHolder> {

    public ArrayList<Tour>tours;

    public RecyclerViewTours(ArrayList<Tour> tours){
        this.tours=tours;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_tours,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //todo: check how to diferance time and date
        holder.tourGuide.setText(tours.get(position).getTourGuide());
        holder.tourName.setText(tours.get(position).getTourName());
        holder.startDate.setText(tours.get(position).getStartDate());
        holder.endTime.setText(tours.get(position).getEndTime());
        holder.startTime.setText(tours.get(position).getStartTime());
        holder.endDate.setText(tours.get(position).getEndDate());
        holder.contactInfo.setText(tours.get(position).getContactInfo());
        holder.isOnGoingTour.setChecked(tours.get(position).getOngoingTour());

        if(!tours.get(position).getOngoingTour())
            holder.cardView.setCardBackgroundColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        CardView cardView;
        TextView tourGuide,tourName,endDate,endTime,startTime,startDate,contactInfo,listOfTravelersOnTour;
        Switch isOnGoingTour;



        public ViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.tour_card_view);
            tourGuide=itemView.findViewById(R.id.tour_guide);
            tourName=itemView.findViewById(R.id.tour_name_txt_fld );
            endDate=itemView.findViewById(R.id.end_date_txt_fld );
            startTime=itemView.findViewById(R.id.start_time_txt_fld );
            endTime=itemView.findViewById(R.id.end_time );
            startDate=itemView.findViewById(R.id.start_date_txt_fld );
            contactInfo=itemView.findViewById(R.id.contact_inf_txt_fld);
            isOnGoingTour=itemView.findViewById(R.id.is_ongoing_tour);






        }
    }
}
