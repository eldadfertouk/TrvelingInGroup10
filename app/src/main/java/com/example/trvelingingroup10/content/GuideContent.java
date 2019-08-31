package com.example.trvelingingroup10.content;

import com.example.trvelingingroup10.guides.Guide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor

@ToString

public class GuideContent extends Guide {

    /**
     * An array of guides items.
     */
    public static final List<GuideItem> GUIDE_ITEMS = new ArrayList<GuideItem>();

    /**
     * A map of sample guide items, by ID.
     */
    public static final Map<String, GuideItem> GUIDE_ITEM_MAP = new HashMap<String, GuideItem>();

    private static final int COUNT = 15;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem( createGuideItem( i ) );
        }
    }

    private static void addItem(GuideItem guideItem) {
        GUIDE_ITEMS.add( guideItem );
        GUIDE_ITEM_MAP.put( guideItem.guideId, guideItem );
    }

    private static GuideItem createGuideItem(int position) {
        return new GuideItem( String.valueOf( position ), "jhony depp " + position, makeDetails( position ),"group fetailes" );
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append( "Details about Guide: " ).append( position );
        for (int i = 0; i < position; i++) {
            builder.append( "\nMore details information here." );
        }
        return builder.toString();
    }

    /**
     * A content item representing a piece of content.
     */
    public static class GuideItem {

        private String guideId,guideName,guideContent,guideDtails,fullName,displayName,dateOfBirth,guideEmail,guideUid;
        public GuideItem(){
            Guide guide=new Guide();
        }
        public GuideItem(String guideId, String guideName, String guideContent, String guideDtails, String fullName, String displayName, String dateOfBirth, String guideEmail, String guideUid) {
            this.guideId = guideId;
            this.guideName = guideName;
            this.guideContent = guideContent;
            this.guideDtails = guideDtails;
            this.fullName = fullName;
            this.displayName = displayName;
            this.dateOfBirth = dateOfBirth;
            this.guideEmail = guideEmail;
            this.guideUid = guideUid;
        }

        public GuideItem(String id, String guidename, String content, String details) {
            this.guideId = id;
            this.guideName = guidename;
            this.guideContent = content;
            this.guideContent = details;
        }

        @Override
        public String toString() {
            return guideContent;
        }
    }
}
