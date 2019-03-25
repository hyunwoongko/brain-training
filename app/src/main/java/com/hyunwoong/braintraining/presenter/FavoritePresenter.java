package com.hyunwoong.braintraining.presenter;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.FavoriteContract;
import com.hyunwoong.braintraining.model.FavoriteModel;
import com.hyunwoong.braintraining.utils.PreferenceHelper;
import com.hyunwoong.braintraining.view.CalculateGameView;
import com.hyunwoong.braintraining.view.ColorGameView;
import com.hyunwoong.braintraining.view.CountGameView;
import com.hyunwoong.braintraining.view.FavoriteView;
import com.hyunwoong.braintraining.view.LabialGameView;
import com.hyunwoong.braintraining.view.MixGameView;
import com.hyunwoong.braintraining.view.MomentGameView;
import com.hyunwoong.braintraining.view.ShellGameView;
import com.hyunwoong.braintraining.view.WordGameView;

import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter extends BasePresenter implements FavoriteContract.presenter {

    private FavoriteModel model;
    private FavoriteView view;

    @Override
    public void SetView(FavoriteView view) {
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void SetModel() {
        model = new FavoriteModel();
    }

    @Override
    public void InitModel() {
        model.init();
    }

    @Override
    public void CheckList() {

        if (model == null) {
            SetModel();
            InitModel();
        }
        List<String> items = PreferenceHelper.getInstance(view).getItem("favorite");

        if (items != null) {
            for (String temp : items) {
                FavoriteModel item = new FavoriteModel();
                item.setFavoriteItem(temp);
                model.getFavoriteItems().add(item);
            }
        } else {
            model.init();
        }
    }

    @Override
    public void SelectItem(String string) {

        Button_Beep();

        if (string.equals(getString(R.string.CalculateGame)))
            MoveTo(CalculateGameView.class);

        else if (string.equals(getString(R.string.CountGame)))
            MoveTo(CountGameView.class);

        else if (string.equals(getString(R.string.MomentGame)))
            MoveTo(MomentGameView.class);

        else if (string.equals(getString(R.string.ColorGame)))
            MoveTo(ColorGameView.class);

        else if (string.equals(getString(R.string.MixGame)))
            MoveTo(MixGameView.class);

        else if (string.equals(getString(R.string.ShellGame)))
            MoveTo(ShellGameView.class);

        else if (string.equals(getString(R.string.WordGame)))
            MoveTo(WordGameView.class);

        else if (string.equals(getString(R.string.LabialGame)))
            MoveTo(LabialGameView.class);

    }

    @Override
    public void RemoveItem(String gameName) {
        List<String> items = PreferenceHelper.getInstance(view).getItem("favorite");

        if (items == null) {
            items = new ArrayList<>();
        }
        items.remove(gameName);
        PreferenceHelper.getInstance(view).setItem("favorite", items);
        Toast("즐겨찾기에서 제거되었습니다.");

        model.getFavoriteItems().remove(gameName);
        view.recreate();

    }

    @Override
    public ArrayList MakeList() {
        return model.getFavoriteItems();
    }

}
