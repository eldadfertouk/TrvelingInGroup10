package com.example.trvelingingroup10.Groups;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.content.GroupContent.GroupItem;
import com.example.trvelingingroup10.content.GroupContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class GroupFragment extends Fragment implements Serializable {

    private FirebaseFirestore database;
    private ArrayList<Group> groupClasses;
    private Context context;
    private View view;
    
  
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_GROUP_ID ="group-Id";
    private static final String ARG_GROUP_NAME="group-name";
    private static final String ARG_GROUP_CONTENT="group-content";
    private static final String ARG_GROUP_DETAILS="group-details";
    private static final String ARG_GROUP_START_DATE="start date";
    private static final String ARG_GROUP_START_TIME="start time";
    private static final String ARG_GROUP_MONITOR_LEVEL="group monitor level";
    private static final String ARG_GROUP_END_DATE="end date";
    private static final String ARG_GROUP_END_TIME="end time";
    private static final String ARG_GROUP_LEADER="group leader";
    private static final String ARG_GROUP_LIST_OF_TRAVELERS="travelers list";

    private int mColumnCount = 1;

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

    public static GroupFragment newInstance(int columnCount) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putInt( ARG_COLUMN_COUNT, columnCount );
        args.putInt( ARG_GROUP_ID,columnCount+1 );
        args.putInt( ARG_GROUP_NAME,columnCount+2 );
        args.putInt( ARG_GROUP_CONTENT ,columnCount+3 );
        args.putInt( ARG_GROUP_DETAILS,columnCount+4 );
        args.putInt( ARG_GROUP_START_DATE,columnCount+5 );
        args.putInt( ARG_GROUP_START_TIME,columnCount+6 );
        args.putInt( ARG_GROUP_END_DATE,columnCount+7 );
        args.putInt( ARG_GROUP_END_TIME,columnCount+8 );
        args.putInt( ARG_GROUP_LEADER,columnCount+9 );
        args.putInt( ARG_GROUP_LIST_OF_TRAVELERS,columnCount+10 );
        args.putInt( ARG_GROUP_MONITOR_LEVEL,columnCount+11 );

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
        view = inflater.inflate( R.layout.fragment_group_list, container, false );
        loadDataBase();
        loadTheGroups();
        // Set the adapter
        if (view instanceof RecyclerView) {
            context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            recyclerView.setAdapter( new MyGroupRecyclerViewAdapter( GroupContent.GROUP_ITEMS, mListener ) );
        }
        return view;
    }
    private void initRecyclerView(){

// TODO: 15/12/2018 איתחול של אובייקט מסוג recycelviev
        // TODO: 15/12/2018 אשר תפקידו הוא להציג לנו כמות מידע מסויימת לפי תבנית שהגדרנו מראש
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        RecyclerView recyclerView = view.findViewById(R.id.group_fragment_list );
        recyclerView.setLayoutManager(layoutManager);
        MyGroupRecyclerViewAdapter adapter = new MyGroupRecyclerViewAdapter(groupClasses);
        recyclerView.setAdapter(adapter);
    }

    private void loadTheGroups() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                groupClasses = new ArrayList<>();
                database.collection("groups").document("DOgf6HQ9p0O9Fq0Qxdiy").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {

                    }

                });
                database.collection("groups").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (int i =0;i<task.getResult().getDocuments().size();i++){
                            groupClasses.add(task.getResult().getDocuments().get(i).toObject(Group.class));
                        }
                        //todo:check fields and match to firestore
                        initRecyclerView();
                    }
                });
            }
        }).start();
    }

    private void loadDataBase() {
        database=FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
        database.setFirestoreSettings(settings);
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
