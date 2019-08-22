package com.example.trvelingingroup10;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private static final String ARG_MAP1 = "guideFragMap";
    private static final String ARG_MAP2 = "travelerFragMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.mainMapFrag );
        mapFragment.getMapAsync( this );
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(31.675551,34.569365))
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();
//build standard google map with eldad's home marker

        // Add a marker in eldad's home and move the camera

        LatLng eldadHome = new LatLng( 31.675551, 34.569365 );
        mMap.addMarker( new MarkerOptions().position( eldadHome ).title( "My HOME or HOTEL" )
                .icon( bitmapDescriptorFromVector( this,R.drawable.hotelic ) ) );
         LatLng travelerPointsOfInterest = new LatLng( 31.675551,34.56400 );
        mMap.addMarker( new MarkerOptions().position( travelerPointsOfInterest ).title( "Traveler MAP" )
                .icon( bitmapDescriptorFromVector( this,R.drawable.travelersmall ) ) );

        LatLng guidePointsOfInterest = new LatLng( 31.675551,34.56900 );
        mMap.addMarker( new MarkerOptions().position( guidePointsOfInterest ).title
                ( "Guide MAP" ).icon( bitmapDescriptorFromVector(this,R.drawable.guidesmall  ) ) );

        mMap.animateCamera( CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
