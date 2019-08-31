package com.example.trvelingingroup10.app_users;

import android.app.Activity;
import android.os.Bundle;

import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.content.TravelerContent;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a {@link UserListActivity}
 * in two-pane mode (on tablets) or a {@link UserDetailActivity}
 * on handsets.
 */
public class UserDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "traveler_id";

    /**
     * The dummy content this fragment is presenting.
     */
    //private BasicLocationContent.DummyItem mItem;
    private TravelerContent.TravelerItem travelerItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        if (getArguments().containsKey( ARG_ITEM_ID )) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            travelerItem = TravelerContent.TRAVELER_ITEM_MAP.get( getArguments().getString( ARG_ITEM_ID ));
            //mItem = BasicLocationContent.ITEM_MAP.get( getArguments().getString( ARG_ITEM_ID ) );

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById( R.id.toolbar_layout );
            if (appBarLayout != null) {
                appBarLayout.setTitle( travelerItem.toString() );
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_traveler , container, false );

        // Show the dummy content as text in a TextView.
        if (travelerItem != null) {
            ((TextView) rootView.findViewById( R.id.travelersListFrag  )).setText( travelerItem.toString() );
        }

        return rootView;
    }
}
