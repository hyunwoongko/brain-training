package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.adapter.FriendAdapter;
import com.hyunwoong.braintraining.contract.FriendContract;
import com.hyunwoong.braintraining.presenter.FriendPresenter;


public class FriendView extends BaseView implements FriendContract.view {

    private FriendPresenter mPresenter;
    private FriendAdapter friendAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_friend);

        mPresenter = new FriendPresenter();

        SearchView searchView = (SearchView) findViewById(R.id.searchbar);
        mPresenter.SetView(searchView, this);
        mPresenter.SetupSearchView();
        mPresenter.CheckList();
        friendAdapter = new FriendAdapter(this, mPresenter.MakeList());
        // 어댑터 생성

        ListView FriendListView = (ListView) findViewById(R.id.friendList);
        FriendListView.setAdapter(friendAdapter);
        FriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String itemName = friendAdapter.getItemName(position);
                mPresenter.MoveTo(FriendInfoView.class, "UID", itemName);
                mPresenter.Toast("해당 유저의 정보로 이동합니다.");
            }
        });
        FriendListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String itemName = friendAdapter.getItemName(position);
                mPresenter.RemoveItem(itemName);
                mPresenter.Toast("친구목록에서 제거되었습니다.");
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        mPresenter.SetBGM("FriendBGM");
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

}
