package com.hyunwoong.braintraining.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.SelectContract;
import com.hyunwoong.braintraining.presenter.SelectPresenter;
import com.hyunwoong.braintraining.view.BaseView;
import com.hyunwoong.braintraining.view.CalculateGameView;
import com.hyunwoong.braintraining.view.ColorGameView;
import com.hyunwoong.braintraining.view.CountGameView;
import com.hyunwoong.braintraining.view.LabialGameView;
import com.hyunwoong.braintraining.view.MixGameView;
import com.hyunwoong.braintraining.view.MomentGameView;
import com.hyunwoong.braintraining.view.ShellGameView;
import com.hyunwoong.braintraining.view.WordGameView;
import com.hyunwoong.braintraining.utils.TypeWriter;

public class SelectView extends BaseView implements SelectContract.veiw {

    private ImageButton Calculate;
    private ImageButton Count;
    private ImageButton Moment;
    private ImageButton Color;
    private ImageButton Mix;
    private ImageButton Shell;
    private ImageButton Word;
    private ImageButton Labial;
    // View

    private SelectPresenter mPresenter;
    // Presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_select);
        mPresenter = new SelectPresenter();

        TextView nickname = (TextView) findViewById(R.id.select_nickname);
        TextView userId = (TextView) findViewById(R.id.select_userId);
        ImageView profileImage = (ImageView) findViewById(R.id.select_profile_image);
        TypeWriter typeWriter = (TypeWriter) findViewById(R.id.select_dialog);
        FrameLayout dialogFramelayout = (FrameLayout) findViewById(R.id.selectBrainTrainer);
        FrameLayout buttonFramelayout = (FrameLayout) findViewById(R.id.buttonPanel);

        Calculate = findViewById(R.id.game_button1);
        Count = findViewById(R.id.game_button2);
        Moment = findViewById(R.id.game_button3);
        Color = findViewById(R.id.game_button4);
        Mix = findViewById(R.id.game_button5);
        Shell = findViewById(R.id.game_button6);
        Word = findViewById(R.id.game_button7);
        Labial = findViewById(R.id.game_button8);

        mPresenter.SetView(nickname, userId, profileImage, typeWriter,
                dialogFramelayout, buttonFramelayout, this);

        mPresenter.DrawUserInfo();
        OnClickListener();
        OnLongClickListener();
    }

    @Override
    public void onGameSelectDialogueClicked(View v) {
        mPresenter.Button_Beep();
        mPresenter.Dialog();
    }

    @Override
    public void OnClickListener() {
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(CalculateGameView.class);
            }

        });


        Count.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(CountGameView.class);
            }
        });

        Moment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(MomentGameView.class);
            }
        });
        Color.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(ColorGameView.class);
            }
        });

        Mix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(MixGameView.class);
            }
        });

        Shell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(ShellGameView.class);
            }
        });

        Word.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(WordGameView.class);
            }
        });

        Labial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.Button_Beep();
                mPresenter.MoveTo(LabialGameView.class);
            }
        });
    }

    @Override
    public void OnLongClickListener() {

        Calculate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.CalculateGame));
                return false;
            }
        });

        Count.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.CountGame));
                return false;
            }
        });

        Moment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.MomentGame));
                return false;
            }
        });

        Color.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.ColorGame));
                return false;
            }
        });

        Mix.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.MixGame));
                return false;
            }
        });

        Shell.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.ShellGame));
                return false;
            }
        });

        Word.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.WordGame));
                return false;
            }
        });
        Labial.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresenter.AddToFavorite(mPresenter.getString(R.string.LabialGame));
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.SetBGM("SelectBGM");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.InfoDestory();
        mPresenter = null;
    }
}