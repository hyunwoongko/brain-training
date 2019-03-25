package com.hyunwoong.braintraining.model;

import java.util.ArrayList;

public class FriendModel {

    private String FriendItem;
    private ArrayList<FriendModel> friendItems;

    public void init(){
        friendItems = new ArrayList<FriendModel>();
    }
    public String getFriendItem() {
        return FriendItem;
    }
    public ArrayList<FriendModel> getFriendItems() {
        return friendItems;
    }
    public void setFriendItem(String friendItem) {
        FriendItem = friendItem;
    }
    public void setFriendItems(ArrayList<FriendModel> friendItems) {
        this.friendItems = friendItems;
    }
}
