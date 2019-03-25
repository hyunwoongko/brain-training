package com.hyunwoong.braintraining.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.model.FavoriteModel;

import java.util.List;

public class FavoriteAdapter extends BaseAdapter {

    private List<FavoriteModel> FavoriteItem;
    private Context mContext;

    public FavoriteAdapter(Context context, List<FavoriteModel> FavoriteItem) {
        mContext = context;
        this.FavoriteItem = FavoriteItem;
    }

    public String getItemName(int position) {
        return FavoriteItem.get(position).getFavoriteItem();
    }

    @Override
    public int getCount() {
        return FavoriteItem.size();
    }

    @Override
    public Object getItem(int position) {
        return FavoriteItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_favorite, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.FavoriteItem.setText(FavoriteItem.get(position).getFavoriteItem());
        return convertView;
    }

    class ViewHolder {
        TextView FavoriteItem;

        public ViewHolder(View view) {
            FavoriteItem = (TextView) view.findViewById(R.id.FavoriteItems);
            view.setTag(this);
        } // 뷰홀더 적용
    }
}
