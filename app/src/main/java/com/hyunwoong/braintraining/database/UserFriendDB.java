package com.hyunwoong.braintraining.database;

import java.util.ArrayList;

public class UserFriendDB {

    private volatile static UserFriendDB mInstance = null;


    public static UserFriendDB getInstance() {
        if (mInstance == null) {
            synchronized (UserFriendDB.class) {
                if (mInstance == null) {
                    mInstance = new UserFriendDB();
                }
            }
        }
        return mInstance;
    } // Singleton

    public static void setInstance(UserFriendDB mInstance) {
        UserFriendDB.mInstance = mInstance;
    }

    private ArrayList<String> FriendList;

    public ArrayList<String> getFriendList() {
        return FriendList;
    }

    public void setFriendList(ArrayList<String> friendList) {
        FriendList = friendList;
    }

    public void ClearFriendList() {
        FriendList.clear();
    }
}
