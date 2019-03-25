package com.hyunwoong.braintraining.model;

import java.util.ArrayList;

public class FavoriteModel {

    private String FavoriteItem;
    private ArrayList<FavoriteModel> favoriteItems;

    public void init(){
        favoriteItems = new ArrayList<FavoriteModel>();
    }
    public String getFavoriteItem() {
        return FavoriteItem;
    }
    public ArrayList<FavoriteModel> getFavoriteItems() {
        return favoriteItems;
    }
    public void setFavoriteItem(String favoriteItem) {
        FavoriteItem = favoriteItem;
    }
    public void setFavoriteItems(ArrayList<FavoriteModel> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }
}
