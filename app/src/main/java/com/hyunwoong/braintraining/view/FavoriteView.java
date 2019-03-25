package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.adapter.FavoriteAdapter;
import com.hyunwoong.braintraining.contract.FavoriteContract;
import com.hyunwoong.braintraining.presenter.FavoritePresenter;

public class FavoriteView extends BaseView implements FavoriteContract.view {

    private FavoriteAdapter favoriteAdapter;
    private FavoritePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_favorite);
        mPresenter = new FavoritePresenter();
        mPresenter.SetView(this);
        mPresenter.CheckList();
        favoriteAdapter = new FavoriteAdapter(this, mPresenter.MakeList());
        // 어댑터 생성

        ListView FavoriteListView = (ListView) findViewById(R.id.FavoriteList);
        FavoriteListView.setAdapter(favoriteAdapter);
        FavoriteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String itemName = favoriteAdapter.getItemName(position);
                mPresenter.SelectItem(itemName);
            }
        }); // ListView 세팅

        FavoriteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String itemName = favoriteAdapter.getItemName(position);
                mPresenter.RemoveItem(itemName);

                return false;
            }
        });
    }
}
