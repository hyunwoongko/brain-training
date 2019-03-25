package com.hyunwoong.braintraining.database;

public class UserOtherDB {
// for Database

    private volatile static UserOtherDB mInstance = null;

    private UserOtherDB() {
    }

    public static UserOtherDB getInstance() {
        if (mInstance == null) {
            synchronized (UserOtherDB.class) {
                if (mInstance == null) {
                    mInstance = new UserOtherDB();
                }
            }
        }
        return mInstance;
    } // Singleton

    public static void setInstance(UserOtherDB mInstance) {
        UserOtherDB.mInstance = mInstance;
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
