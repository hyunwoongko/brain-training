package com.hyunwoong.braintraining.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.database.UserOtherDB;
import com.hyunwoong.braintraining.model.FriendModel;

import java.util.List;

public class FriendAdapter extends BaseAdapter {

    private List<FriendModel> nickname;
    private Context mContext;

    public FriendAdapter(Context context, List<FriendModel> items) {
        mContext = context;
        nickname = items;
    }

    public String getItemName(int position) {
        return nickname.get(position).getFriendItem();
    }

    @Override
    public int getCount() {
        return nickname.size();
    }

    @Override
    public Object getItem(int position) {
        return nickname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_friend, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.NickName.setText(nickname.get(position).getFriendItem());
        return convertView;
    }

    class ViewHolder {
        TextView NickName;

        public ViewHolder(View view) {
            NickName = (TextView) view.findViewById(R.id.friendItems);
            view.setTag(this);
        } // 뷰홀더 적용
    }
}
