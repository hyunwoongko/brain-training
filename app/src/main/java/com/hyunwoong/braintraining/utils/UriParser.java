package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyunwoong.braintraining.database.UserCurrentDB;

public class UriParser {

    private ImageView Image;
    private Context context;

    public UriParser(ImageView Image, Context context) {
        this.context = context;
        this.Image = Image;
    }

    public void ParseURI() {

        if (UserCurrentDB.getInstance().getPhotoUrl() != null) {
            Glide.with(context)
                    .load(Uri.parse(UserCurrentDB.getInstance().getPhotoUrl()))
                    .into(Image);
        }
        else {
            Log.d("URI ERROR" , "URI ERROR");
        }
    }
}
