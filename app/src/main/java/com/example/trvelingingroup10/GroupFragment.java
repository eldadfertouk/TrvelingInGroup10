package com.example.trvelingingroup10;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trvelingingroup10.content.GroupTourContent.GroupItem;
import com.example.trvelingingroup10.content.GroupTourContent;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class GroupFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_GROUP_ID ="group-Id";
    private static final String ARG_GROUP_NAME="group-name";
    private static final String ARG_GROUP_CONTENT="group-content";
    private static final String ARG_GROUP_DETAILS="group-details";

    private int mColumnCount = 5;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupFragment() {
    }
    public static GroupFragment getInstance(){
        return new GroupFragment();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GroupFragment newInstance(int columnCount) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putInt( ARG_COLUMN_COUNT, columnCount );
        args.putInt( ARG_GROUP_ID,columnCount+1 );
        args.putInt( ARG_GROUP_NAME,columnCount+2 );
        args.putInt( ARG_GROUP_CONTENT ,columnCount+3 );
        args.putInt( ARG_GROUP_DETAILS,columnCount+4 );
        fragment.setArguments( args );
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        if (getArguments() != null) {

            mColumnCount = getArguments().getInt( ARG_COLUMN_COUNT );

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_group_list, container, false );

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            recyclerView.setAdapter( new MyGroupRecyclerViewAdapter( GroupTourContent.ITEMS, mListener ) );
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {


        void onListFragmentInteraction(GroupItem item);

    }
}
