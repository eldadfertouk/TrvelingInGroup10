package com.example.trvelingingroup10.content;

import com.example.trvelingingroup10.Traveler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor

@ToString




public class TravelerContent extends Traveler {

    /**
     * An array of sample (content) items.
     */
    public static final List<TravelerItem> ITEMS = new ArrayList<TravelerItem>();

    /**
     * A map of sample (content) items, by ID.
     */
    public static final Map<String, TravelerItem> ITEM_MAP = new HashMap<String, TravelerItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem( createTravelerItem( i ) );
        }
    }

    public TravelerContent(String toString, String toString1, String toString2, String s) {
        super(toString, toString1, toString2, s);
    }

    private static void addItem(TravelerItem travelerItem) {
        ITEMS.add( travelerItem );
        ITEM_MAP.put( travelerItem.travelerId, travelerItem );
    }

    private static TravelerItem createTravelerItem(int position) {
        return new TravelerItem( String.valueOf( position ), "Traveler " + position, makeDetails( position ),"all groups" );
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append( "Details about Item: " ).append( position );
        for (int i = 0; i < position; i++) {
            builder.append( "\nMore details information here." );
        }
        return builder.toString();
    }

    /**
     * A content item representing a piece of content.
     */
    public static class TravelerItem {
        private String travelerId;
        private String travelerContent;
        private String travelerDetails;
        private String travelerGroup;
        private String travelerAddress;
        private String travelerName;
        private String travelerDateOfBirth;
        private Boolean isRealised,isKosher,isBlind,isDeaf,isNeedHelp,isMiner;


        public TravelerItem(String id, String content, String details,String travelerGroup) {
            this.travelerId = id;
            this.travelerContent = content;
            this.travelerDetails = details;
            this.travelerGroup = travelerGroup;
        }

        public TravelerItem(String travelerId, String travelerContent, String travelerDetails, String travelerGroup, String travelerAddress, String travelerName, String travelerDateOfBirth, Boolean isRealised, Boolean isKosher, Boolean isBlind, Boolean isDeaf, Boolean isNeedHelp, Boolean isMiner) {
            this.travelerId = travelerId;
            this.travelerContent = travelerContent;
            this.travelerDetails = travelerDetails;
            this.travelerGroup = travelerGroup;
            this.travelerAddress = travelerAddress;
            this.travelerName = travelerName;
            this.travelerDateOfBirth = travelerDateOfBirth;
            this.isRealised = isRealised;
            this.isKosher = isKosher;
            this.isBlind = isBlind;
            this.isDeaf = isDeaf;
            this.isNeedHelp = isNeedHelp;
            this.isMiner = isMiner;
        }

        @Override
        public String toString() {
            return travelerContent;
        }
    }
}
