package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.content.ContextWrapper;

import com.hyunwoong.braintraining.database.UserOtherDB;
import com.hyunwoong.braintraining.model.NotificationModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FcmHelper{

    private static FcmHelper mInstance = null;

    private final String serverKey = "key=AIzaSyCIFyMbGNGumIGxZnmK9V9HIyHrII06l8M";
    private final String fcmUrl = "https://gcm-http.googleapis.com/gcm/send";

    private FcmHelper() {
    }

    public static synchronized FcmHelper getInstance() {
        if (mInstance == null) {
        }
        try {
            if (mInstance == null)
                mInstance = new FcmHelper();
            return mInstance;
        } finally {
        }
    } // Singleton

    public void passPushTokenToServer() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String token = FirebaseInstanceId.getInstance().getToken();
        // 토큰 생성

        Map<String, Object> map = new HashMap<>();
        map.put("pushToken", token);

        FirebaseDatabase.getInstance().getReference().child("AllUserDB").child(uid).child("UserCurrentDB").updateChildren(map);
        // 서버로 바로 올림.
    } // 로그인 성공시에만 사용,


    public void SendMessage(String Title, String Text) {
        final Gson gson = new Gson();
        final NotificationModel model = new NotificationModel();

        String token = UserOtherDB.getInstance().getPushToken();
        model.setTo(token);
        model.getNotification().title = Title;
        model.getNotification().text = Text;
        model.getData().title = Title;
        model.getData().text = Text;

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf8"), gson.toJson(model));

        Request request = new Request.Builder()
                .header("Content-Type", "applicaion/json")
                .addHeader("Authorization", serverKey)
                .url(fcmUrl)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    } // 메시지 보내기!
}
