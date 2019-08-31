package com.example.trvelingingroup10.content;

import com.example.trvelingingroup10.Groups.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.*;


@Getter
@Setter

@ToString



public class GroupContent extends Group {

    /**
     * An array of sample (content) items.
     */
    public static final List<GroupItem> GROUP_ITEMS = new ArrayList<GroupItem>();

    /**
     * A map of sample (content) items, by ID.
     */
    public static final Map<String, GroupItem> GROUP_ITEM_MAP = new HashMap<String, GroupItem>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem( createGroupItem( i ) );
        }
    }

    private static void addItem(GroupItem groupItem) {
        GROUP_ITEMS.add( groupItem );
        GROUP_ITEM_MAP.put( groupItem.groupId, groupItem );
    }

    private static GroupItem createGroupItem(int position) {

        return new GroupItem( String.valueOf( position ) ,"group "+position,makeDetails( position ),"balablablablabl");
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
    public static class GroupItem {



        public String groupId,groupName,groupCode,dateOfTourStart,groupContactInfo,groupLeader,groupDetails,groupContent;


        public GroupItem(String id, String name,String content, String details) {
            this.groupId = id;
            this.groupContent = content;
            this.groupDetails = details;
            this.groupName = name;
        }
        public GroupItem(){
            Group group = new Group();
        }


        @Override
        public String toString() {
            return groupContent;
        }
    }
}
