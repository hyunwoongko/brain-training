package com.hyunwoong.braintraining.contract;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.view.SelectView;
import com.hyunwoong.braintraining.utils.TypeWriter;

public interface SelectContract extends BaseContract{

    interface veiw{
        void onGameSelectDialogueClicked(View v);
        //Dialog Framework

        void OnLongClickListener();
        void OnClickListener();
    }

    interface presenter{
        void SetView(TextView nickname , TextView userId , ImageView profileImage, TypeWriter typeWriter ,
                     FrameLayout dialogFramelayout , FrameLayout buttonFramelayout , SelectView view);
        void ClearView();
        void SetModel();
        void ClearModel();
        void Dialog();
        void DrawUserInfo();
        void InfoDestory();
        void AddToFavorite(String gameName);
    }
}
