package com.example.trvelingingroup10.Groups;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.trvelingingroup10.Groups.GroupFragment.OnListFragmentInteractionListener;
import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.content.GroupContent.GroupItem;

import java.net.URI;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GroupItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.

 */
public class MyGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<GroupItem> mValues;
    private OnListFragmentInteractionListener mListener;


    public MyGroupRecyclerViewAdapter(List<GroupItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    private ArrayList<Group> groupClasses;

    public MyGroupRecyclerViewAdapter(List<GroupItem> mValues, OnListFragmentInteractionListener mListener, ArrayList<Group> groupClasses){
        this.mValues = mValues;
        this.mListener = mListener;
        this.groupClasses = groupClasses;
    }

    public MyGroupRecyclerViewAdapter(ArrayList<Group> groupClasses) {
        this.groupClasses = groupClasses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_group, parent, false );
        return new ViewHolder( view );
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
  /*      holder.mGroupItem = mValues.get( position );
        holder.mIdView.setText( mValues.get( position ).groupId );
        holder.mContentView.setText( mValues.get( position ).groupContent );*/
        holder.joinGroupBtn.setOnClickListener(this);
  //      holder.startDate.setTime(Long.parseLong(mValues.get(position).dateOfTourStart));
        holder.mView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction( holder.mGroupItem );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return groupClasses.size();
    }

    @Override
    public void onClick(View v) {
        //todo: build on click respons on the recycel adapter
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mIdView;
        private TextView mContentView;
        private GroupItem mGroupItem;
        private TextView introMassage,currentGroupOnView,dateOfTour,addressTxt;
        private Time startTime,endTime;
        private Date startDate,endDate;
        private RadioGroup groupMonitorLevel;
        private RadioButton levelA,levelB,levelC;
        private ImageView groupProfilePicture;
        private URI imageURI;
        private Button joinGroupBtn;


        public ViewHolder(View view) {
            super( view );
            mView = view;
            mIdView = (TextView) view.findViewById( R.id.item_number );
            mContentView = (TextView) view.findViewById( R.id.content );
            introMassage = (TextView) view.findViewById(R.id.mTextMessage);
          //  passwordTxt = (TextView) view.findViewById(R.id.passwordFld);
            dateOfTour = (TextView) view.findViewById(R.id.strat_tour_date);
            groupMonitorLevel = view.findViewById(R.id.monitor_level);
            levelA = view.findViewById(R.id.monitor_level_a_RB);
            levelB = view.findViewById(R.id.monitor_level_b_RB);
            levelC = view.findViewById(R.id.monitor_level_c_RB);
            joinGroupBtn = view.findViewById(R.id.join_to_this_group_Btn);

//            mGroupItem = new GroupItem("11","myTestGroup","test group full content","test group details");


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
