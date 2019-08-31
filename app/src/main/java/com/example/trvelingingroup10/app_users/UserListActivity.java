package com.example.trvelingingroup10.app_users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.content.TravelerContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * An activity representing a list of users. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link UserDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class UserListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_list );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        toolbar.setTitle( getTitle() );

        FloatingActionButton fab =  findViewById( R.id.fab );
        fab.setOnClickListener(view -> Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                .setAction( "Action", null ).show());

        if (findViewById( R.id.user_detail_container ) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById( R.id.user_list );
        assert recyclerView != null;
        setupRecyclerView( (RecyclerView) recyclerView );
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter( new SimpleItemRecyclerViewAdapter( this, TravelerContent.TRAVELER_ITEMS, mTwoPane ) );
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final UserListActivity mParentActivity;
        private final List<TravelerContent.TravelerItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravelerContent.TravelerItem travelerItem = (TravelerContent.TravelerItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString( UserDetailFragment.ARG_ITEM_ID,travelerItem.toString() );
                    UserDetailFragment fragment = new UserDetailFragment();
                    fragment.setArguments( arguments );
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace( R.id.user_detail_container, fragment )
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent( context, UserDetailActivity.class );
                    intent.putExtra( UserDetailFragment.ARG_ITEM_ID, travelerItem.toString() );

                    context.startActivity( intent );
                }
            }
        };

        SimpleItemRecyclerViewAdapter(UserListActivity parent,
                                      List<TravelerContent.TravelerItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from( parent.getContext() )
                    .inflate( R.layout.user_list_content, parent, false );
            return new ViewHolder( view );
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText( mValues.get( position ).toString() );
            holder.mContentView.setText( mValues.get( position ).toString() );

            holder.itemView.setTag( mValues.get( position ) );
            holder.itemView.setOnClickListener( mOnClickListener );
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super( view );
                mIdView =  view.findViewById( R.id.id_text );
                mContentView =  view.findViewById( R.id.content );
            }
        }
    }
}
