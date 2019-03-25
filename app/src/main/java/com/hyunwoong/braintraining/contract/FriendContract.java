package com.hyunwoong.braintraining.contract;

import android.support.v7.widget.SearchView;

import com.hyunwoong.braintraining.view.FriendView;

import java.util.ArrayList;

public interface FriendContract {

    interface view {

    }

    interface presenter {
        void SetView(SearchView searchView, FriendView view);
        void SetupSearchView();
        void AddFriend(final String keword);
        void AddList(String nickname);
        void CheckList();
        ArrayList MakeList();
        void RemoveItem(String name);
    }
}
