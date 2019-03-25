package com.hyunwoong.braintraining.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.contract.LoginContract;
import com.hyunwoong.braintraining.database.BestScoreDB;
import com.hyunwoong.braintraining.database.UserCurrentDB;
import com.hyunwoong.braintraining.database.UserFriendDB;
import com.hyunwoong.braintraining.model.ServerModel;
import com.hyunwoong.braintraining.presenter.LoginPresenter;
import com.hyunwoong.braintraining.utils.Callback;
import com.hyunwoong.braintraining.utils.FcmHelper;
import com.hyunwoong.braintraining.utils.FirebaseHelper;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginView extends BaseView implements LoginContract.view {

    private LoginPresenter mPresenter;
    private int serverTimes;
    private int kakaoTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();
        Session.getCurrentSession().addCallback(new KakaoSessionCallback());
        mPresenter = new LoginPresenter();
        mPresenter.setPERMISSIO1N(Manifest.permission.WRITE_EXTERNAL_STORAGE, //1
                Manifest.permission.RECORD_AUDIO, //2
                Manifest.permission.READ_PHONE_STATE, //3
                Manifest.permission.RECEIVE_SMS);

        if (!mPresenter.hasPermissions(this, mPresenter.getPERMISSION())) {
            ActivityCompat.requestPermissions(this, mPresenter.getPERMISSION(), 1);

            serverTimes = 0;
            kakaoTimes = 0;
            mPresenter.SetView(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Session.getCurrentSession().addCallback(new KakaoSessionCallback());
        mPresenter.SetBGM("MainBGM");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.SetSession(requestCode, resultCode, data);
    }


    @Override
    public Task<String> getFirebaseJwt(final String kakaoAccessToken, String url) {
        final TaskCompletionSource<String> source = new TaskCompletionSource<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        url += "/verifyToken";
        HashMap<String, String> validationObject = new HashMap<>();
        validationObject.put("token", kakaoAccessToken);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(validationObject), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String firebaseToken = response.getString("firebase_token");
                    source.setResult(firebaseToken);
                } catch (Exception e) {
                    source.setException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Kakao", error.toString());
                source.setException(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("token", kakaoAccessToken);
                return params;
            }
        };

        queue.add(request);
        return source.getTask();
    }


    // 이너클래스 제외 MVP 패턴 적용 완료.
    //TODO: 이너클래스 문제 해결해야함. (180527)
    public class KakaoSessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            kakaoTimes++;
            if (kakaoTimes == 1) {
                Toast.makeText(getApplicationContext(), "서버에 접속중입니다.", Toast.LENGTH_SHORT).show();
            }
            final String accessToken = Session.getCurrentSession().getAccessToken();


            FirebaseDatabase.getInstance().getReference("server").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override public void onDataChange(DataSnapshot dataSnapshot) {
                    getFirebaseJwt(accessToken,dataSnapshot.getValue(ServerModel.class).getUrl()).continueWithTask(new Continuation<String, Task<AuthResult>>() {

                        @Override
                        public Task<AuthResult> then(@NonNull Task<String> task) throws Exception {
                            String firebaseToken = task.getResult();
                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            return auth.signInWithCustomToken(firebaseToken);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    serverTimes++;
                                    if (serverTimes == 1) {

                                        UserCurrentDB.getInstance().setPhotoUrl(String.valueOf(currentUser.getPhotoUrl()));
                                        UserCurrentDB.getInstance().setUserId(currentUser.getUid());
                                        UserCurrentDB.getInstance().setNickname(currentUser.getDisplayName());
                                        //Kakao(Auth)-> Model(UserInfo) === GetUID

                                        FirebaseHelper.getInstance().setValue("UserCurrentDB", UserCurrentDB.getInstance());
                                        //Model(UserInfo) -> DB(UserInfo)


                                        FirebaseHelper.getInstance().getGameScoreValue(new Callback.BestScoreCallback() {
                                            @Override
                                            public void onBestScoreCallback(BestScoreDB bestScoreDB) {
                                                BestScoreDB.setInstance(bestScoreDB);
                                            }
                                        });

                                        FirebaseHelper.getInstance().getUserFriendValue(new Callback.UserFriendCallback() {
                                            @Override
                                            public void onUserFriendCallback(Object Value) {
                                                FirebaseHelper.getInstance().getuList().add(String.valueOf(Value));
                                                UserFriendDB.getInstance().setFriendList(FirebaseHelper.getInstance().getuList());
                                            }
                                        });

                                        FirebaseHelper.getInstance().getUserCurrentValue(new Callback.UserCurrentCallback() {
                                            @Override
                                            public void onUserCurrentCallback(UserCurrentDB userCurrentDB) {
                                                UserCurrentDB.setInstance(userCurrentDB);
                                                Session.getCurrentSession().clearCallbacks();
                                                Session.getCurrentSession().close();
                                                FcmHelper.getInstance().passPushTokenToServer();
                                                Toast.makeText(getApplicationContext(), "서버에 접속하였습니다.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), MainView.class);
                                                startActivity(intent);
                                            }
                                        });


                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "서버 접속에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                Session.getCurrentSession().clearCallbacks();
                                Session.getCurrentSession().close();
                                if (task.getException() != null) {
                                    Log.e("Kakao", task.getException().toString());
                                }
                            }
                        }
                    });
                }

                @Override public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Session.getCurrentSession().clearCallbacks();
            Session.getCurrentSession().close();
            if (exception != null) {
                Log.e("Kakao", exception.toString());
                Toast.makeText(getApplicationContext(), "서버 접속에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}