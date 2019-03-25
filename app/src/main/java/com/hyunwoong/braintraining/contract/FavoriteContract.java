package com.hyunwoong.braintraining.contract;

import com.hyunwoong.braintraining.view.FavoriteView;

import java.util.ArrayList;

public interface FavoriteContract {

    interface view {

    }

    interface presenter {
        void SetView(FavoriteView view);
        void CheckList();
        void SelectItem(String string);
        void RemoveItem(String gameName);
        ArrayList MakeList();
    }

}
