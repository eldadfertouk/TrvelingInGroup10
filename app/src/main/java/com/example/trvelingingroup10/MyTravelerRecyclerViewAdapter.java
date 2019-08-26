package com.example.trvelingingroup10;

import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.trvelingingroup10.content.TravelerContent;
import com.example.trvelingingroup10.TravelerFragment.OnListFragmentInteractionListener;
import com.example.trvelingingroup10.content.TravelerContent.TravelerItem;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.example.trvelingingroup10.content.TravelerContent.TravelerItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
public class MyTravelerRecyclerViewAdapter extends RecyclerView.Adapter<MyTravelerRecyclerViewAdapter.ViewHolder> {

    private final List<TravelerContent.TravelerItem> mValues;
    private final OnListFragmentInteractionListener mListener;



    public MyTravelerRecyclerViewAdapter(List<TravelerItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_traveler, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTravelerItem = mValues.get( position );
 /*       holder.mIdView.setText( mValues.get( position ).id );
        holder.mNameView.setText(mValues.get(position). travelerGroup);
        holder.mContentView.setText( mValues.get( position ).content );
        holder.userDisplayName.setText(mValues.get(position).);
        holder.userUid.setText();
        holder.userFullName.setText();
        holder.userEmail.setText();
        holder.userDateOfBirth.setText();
   */
        holder.mView.setOnClickListener( new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction( holder.mTravelerItem );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mIdView;
        private final TextView mNameView;
        private final TextView mContentView;
        private final Button  addTravelerToGroupBtn,sendRegDataToFireBaseBtn,startAppBtn;
        private final TextView userDisplayName,userUid,userFullName,userEmail,userDateOfBirth;
        private Image userProfilePicture;
        private final CheckBox isRealigns,isKosher, isBlind,isDeaf,isMiner,isNeedHelp;


        public TravelerItem mTravelerItem;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mIdView = (TextView) view.findViewById( R.id.item_number );
            mNameView = (TextView) view.findViewById(R.id.traveler_name);
            mContentView = (TextView) view.findViewById( R.id.content );
            addTravelerToGroupBtn = (Button) view.findViewById(R.id.add_traveler_to_group);
            sendRegDataToFireBaseBtn = view.findViewById(R.id.sendTravelRegDataBtn);
            startAppBtn = view.findViewById(R.id.startTravelBtn);
            userUid = (TextView) view.findViewById(R.id.user_uid);
            userFullName = (TextView) view.findViewById(R.id.user_fullName);
            userDateOfBirth = (TextView) view.findViewById(R.id.user_date_of_birth);
            userEmail = (TextView) view.findViewById(R.id.user_email);
            userDisplayName = (TextView) view.findViewById(R.id.user_displayName);
            isRealigns = view.findViewById(R.id.is_Realigns_CB);
            isKosher = view.findViewById(R.id.is_Kosher_CB);
            isBlind = view.findViewById(R.id.is_Blaind_CB);
            isDeaf = view.findViewById(R.id.is_Deff_CB);
            isMiner = view.findViewById(R.id.is_Miner_CB);
            isNeedHelp = view.findViewById(R.id.is_Need_Help);


            //todo: add user picture
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
