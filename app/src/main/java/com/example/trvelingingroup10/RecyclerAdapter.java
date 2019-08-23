package com.example.trvelingingroup10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Traveler>arrayList;
    private Context mContext;
    public static int position;

    public RecyclerAdapter(ArrayList<Traveler>arrayList,Context context){
        this.arrayList=arrayList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return new ViewHolder( LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_traveler,viewGroup,false));
    }

    // TODO: 5/17/19 find a way to combine the data between the adapter and the fragment in the onClick (section 6 in the word doc)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,@SuppressLint("RecyclerView") final int i) {
        viewHolder.fullName.setText(arrayList.get(i).getFullName());
        viewHolder.displayName.setText( arrayList.get(i).getDisplayName() );
        viewHolder.dateOfBirth.setText( arrayList.get( i ).getDateOfBirth() );
        viewHolder.userEmail.setText( arrayList.get( i ).getUserEmail() );
        viewHolder.userUid.setText( arrayList.get( i ).getUserUid() );
        viewHolder.userProfilePicture.setImageDrawable( viewHolder.userProfilePicture.getResources().getDrawable( arrayList.get( i ).getNumberToAdd() ) );


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position =i;
                mContext.startActivity(new Intent(mContext, TravelerFragment.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView fullName;
        private TextView displayName;
        private TextView dateOfBirth;
        private TextView userEmail;
        private TextView userUid;
        private ImageView userProfilePicture;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            fullName=itemView.findViewById( R.id.user_fullName );
            displayName=itemView.findViewById( R.id.user_displayName );
            dateOfBirth=itemView.findViewById( R.id.user_date_of_birth );
            userEmail=itemView.findViewById( R.id.user_email );
            userUid=itemView.findViewById( R.id.user_uid );
            userProfilePicture=itemView.findViewById( R.id.image_user_Profile_Picture );


        }
    }
}
