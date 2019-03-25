package com.hyunwoong.braintraining.database;

public class UserCurrentDB {
// for Database

    private volatile static UserCurrentDB mInstance = null;

    private UserCurrentDB() {

    }

    public static UserCurrentDB getInstance() {
        if (mInstance == null) {
            synchronized (UserCurrentDB.class) {
                if (mInstance == null) {
                    mInstance = new UserCurrentDB();
                }
            }
        }
        return mInstance;
    } // Singleton

    public static void setInstance(UserCurrentDB mInstance) {
        UserCurrentDB.mInstance = mInstance;
    }

    private String nickname;
    private String userId;
    private String photoUrl;
    private String pushToken;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
