package com.example.trvelingingroup10;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.settings_activity );
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.settings_frame, new SettingsFragment() )
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource( R.xml.root_preferences, rootKey );
        }
    }

    static
    class ViewHolder {
        @BindView(R.id.switch1)
        Switch switch1;
        @BindView(R.id.settings_frame)
        FrameLayout settings_frame;
        @BindView(R.id.checkBox4)
        CheckBox checkBox4;
        @BindView(R.id.checkBox5)
        CheckBox checkBox5;
        @BindView(R.id.radioButton)
        RadioButton radioButton;
        @BindView(R.id.radioButton2)
        RadioButton radioButton2;
        @BindView(R.id.radioButton3)
        RadioButton radioButton3;

        ViewHolder(View view) {
            ButterKnife.bind( this, view );
        }
    }
}