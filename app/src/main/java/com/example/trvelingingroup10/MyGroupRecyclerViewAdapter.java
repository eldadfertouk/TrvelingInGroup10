package com.example.trvelingingroup10;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.trvelingingroup10.GroupFragment.OnListFragmentInteractionListener;
import com.example.trvelingingroup10.content.GroupTourContent.GroupItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GroupItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.

 */
public class MyGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder> {

    private final List<GroupItem> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyGroupRecyclerViewAdapter(List<GroupItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_group, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mGroupItem = mValues.get( position );
   //     holder.mIdView.setText( mValues.get( position ).id );
   //     holder.mContentView.setText( mValues.get( position ).content );

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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mIdView;
        private TextView mContentView;
        private GroupItem mGroupItem;
        private TextView introMassage,passwordTxt,currentGroupOnView,dateOfTour,addressTxt;


        public ViewHolder(View view) {
            super( view );
            mView = view;
            mIdView = (TextView) view.findViewById( R.id.item_number );
            mContentView = (TextView) view.findViewById( R.id.content );
            introMassage = (TextView) view.findViewById(R.id.mTextMessage);
            passwordTxt = (TextView) view.findViewById(R.id.passwordFld);
            dateOfTour = (TextView) view.findViewById(R.id.date_of_tour);

//            mGroupItem = new GroupItem("11","myTestGroup","test group full content","test group details");


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
