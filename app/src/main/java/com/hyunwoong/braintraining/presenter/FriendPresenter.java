package com.hyunwoong.braintraining.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;

import com.hyunwoong.braintraining.contract.FriendContract;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.database.UserFriendDB;
import com.hyunwoong.braintraining.database.UserOtherDB;
import com.hyunwoong.braintraining.model.FriendModel;
import com.hyunwoong.braintraining.utils.Callback;
import com.hyunwoong.braintraining.utils.FcmHelper;
import com.hyunwoong.braintraining.utils.FirebaseHelper;
import com.hyunwoong.braintraining.view.FriendView;

import java.util.ArrayList;

public class FriendPresenter extends BasePresenter implements FriendContract.presenter {

    private SearchView searchView;
    private FriendView view;
    FriendModel model;

    @Override
    public void SetView(SearchView searchView, FriendView view) {
        this.searchView = searchView;
        this.view = view;
        super.SetView(view);
    }

    @Override
    public void ClearView() {
        super.ClearView();
    }

    @Override
    public void SetModel() {
        model = new FriendModel();
        super.SetModel();
    }

    public void InitModel() {
        model.init();
    }


    @Override
    public void ClearModel() {
        super.ClearModel();
    }

    public void SetupSearchView() {
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("친구추가할 유저코드를 입력해주세요.(숫자)");
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String keword) {
                AddFriend(keword);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    public void AddFriend(final String keword) {
        FirebaseHelper.getInstance().getUserOtherValue(keword, true, new Callback.UserOtherCallback() {
            //UID에 일치하는 유저DB를 가져옴.
            //입력은 숫자만 받음. ---> onlynumber를 켜서 자동으로 경로에 kakao:를 추가함.
            @Override
            public void onUserOtherCallback(UserOtherDB userOtherDB) {
                UserOtherDB.setInstance(userOtherDB);

                String nickname = UserOtherDB.getInstance().getNickname();
                String UserID = UserOtherDB.getInstance().getUserId(); // kakao:XXXXXXXXX

                if (UserID != null) {
                    if (UserID.equals(UserCurrentDB.getInstance().getUserId())) {
                        Toast("자기 자신을 추가 할 수 없습니다.");
                    } else {

                        if (UserFriendDB.getInstance().getFriendList() == null) {
                            DialogBuild(nickname, UserID); // 첫 친구추가때는 비교할 친구 리스트가 없어서 에러발생
                        } else {
                            //친구가 한 명 이상 있을때만 비교함.
                            if (UserFriendDB.getInstance().getFriendList().contains(UserID)) {
                                Toast("이미 추가한 친구입니다.");
                            } else {
                                DialogBuild(nickname, UserID);
                            }
                        }
                    }
                } else {
                    Toast("잘못된 유저 코드입니다.");
                }
            }
        });

    }


    private void DialogBuild(final String nickname, final String UID) {
        new AlertDialog.Builder(view)
                .setTitle("친구 추가")
                .setMessage(nickname + "님을 친구로 추가하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FcmHelper.getInstance().SendMessage(UserCurrentDB.getInstance().getNickname(),
                                UserCurrentDB.getInstance().getNickname() + "님께서 회원님을 친구 추가 하였습니다."
                        ); // 해당 유저에게 푸시 알림을 보냄.
                        Toast(nickname + "님을 친구로 추가했습니다."); // 띄우는건 닉네임
                        AddList(UID); // DB에 저장하는 실 데이터는 UID

                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void AddList(String nickname) {
        ArrayList<String> items = UserFriendDB.getInstance().getFriendList();

        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(nickname);

        UserFriendDB.getInstance().setFriendList(items);
        FirebaseHelper.getInstance().setValue("UserFriendDB", UserFriendDB.getInstance().getFriendList());
        view.recreate();
    }


    // 어댑터에서 디비모델의 리스트를 체크해서 리스트뷰에 뿌려줌.
    public void CheckList() {

        if (model == null) {
            SetModel();
            InitModel();
        }
        ArrayList<String> items = UserFriendDB.getInstance().getFriendList();
        if (items != null) {


            for (final String temp : items) { // temp = uid
                FriendModel item = new FriendModel();
                item.setFriendItem(temp);
                model.getFriendItems().add(item);
            }
        } else {
            model.init();
        }
    }

    public ArrayList MakeList() {

        return model.getFriendItems();
    }


    public void RemoveItem(String name) {
        ArrayList<String> items = UserFriendDB.getInstance().getFriendList();

        if (items == null) {
            items = new ArrayList<>();
        }
        items.remove(name);

        UserFriendDB.getInstance().setFriendList(items);
        FirebaseHelper.getInstance().setValue("UserFriendDB", UserFriendDB.getInstance().getFriendList());
        view.recreate();
    }

}
