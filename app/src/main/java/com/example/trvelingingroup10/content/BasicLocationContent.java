package com.example.trvelingingroup10.content;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicLocationContent {

    /**
     * An array of sample (content) items.
     */
    public static final List<BasicLocationItem> LOCATION_ITEMS = new ArrayList<BasicLocationItem>();

    /**
     * A map of sample (content) items, by ID.
     */
    public static final Map<String, BasicLocationItem> LOCATION_ITEM_MAP = new HashMap<String, BasicLocationItem>();

    private static final int COUNT = 50;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem( createLocationItem( i ) );
        }
    }

    private static void addItem(BasicLocationItem item) {
        LOCATION_ITEMS.add( item );
        LOCATION_ITEM_MAP.put( item.id, item );
    }

    private static BasicLocationItem createLocationItem(int position) {

        return new BasicLocationItem( String.valueOf( position ), "Item " + position, makeDetails( position ) );
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append( "Basic Location points: " ).append( position );
        for (int i = 0; i < position; i++) {
            builder.append( "\nMore details information here." );
        }
        return builder.toString();
    }

    /**
     * A content item representing a piece of content.
     */
    public static class BasicLocationItem {
        public final String id;
        public static Double lon,lat;
        public GeoPoint currentLocation,lastKnownLocation;
        public final String content;
        public String details;

        public BasicLocationItem(String id, Double lon, Double lat, GeoPoint currentLocation, String content){
            this.id = id;
            this.lon = lon;
            this.lat = lat;
            this.currentLocation = currentLocation;

            this.content = content;
        }

        public BasicLocationItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;

        }

        public BasicLocationItem(String id, GeoPoint currentLocation, GeoPoint lastKnownLocation, String content, String details) {
            this.id = id;
            this.currentLocation = currentLocation;
            this.lastKnownLocation = lastKnownLocation;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
