package com.hyunwoong.braintraining.utils;

import android.content.Context;
import android.util.Log;

import com.dialoid.speech.tts.TextToSpeech;
import com.kakao.sdk.newtoneapi.TextToSpeechListener;
import com.kakao.util.helper.SystemInfo;

import java.util.Arrays;

/* 카카오 뉴톤 API TTS 클라이언트 클래스
* 기존에는 API키로 카카오 인증을 진행했었는데
* 이번 버전부터 APP키로 바뀌면서 라이브러리 자체에 오류가 있음.
* Context 수정과 APP키를 직접 입력해줌으로써 해결.
 */
public class TTSClient {

    private static final String NEWTONE_TALK_1 = "TTS_HTS";
    private static final String NEWTONE_TALK_2 = "TTS_US";
    private static final String VOICE_WOMAN_READ_CALM = "WOMAN_READ_CALM";
    private static final String VOICE_MAN_READ_CALM = "MAN_READ_CALM";
    private static final String VOICE_WOMAN_DIALOG_BRIGHT = "WOMAN_DIALOG_BRIGHT";
    private static final String VOICE_MAN_DIALOG_BRIGHT = "MAN_DIALOG_BRIGHT";
    // 음성

    private static final int ERROR_NETWORK = 2;
    private static final int ERROR_NETWORK_TIMEOUT = 3;
    private static final int ERROR_CLIENT_INETRNAL = 5;
    private static final int ERROR_SERVER_INTERNAL = 6;
    private static final int ERROR_SERVER_TIMEOUT = 7;
    private static final int ERROR_SERVER_AUTHENTICATION = 8;
    private static final int ERROR_SERVER_SPEECH_TEXT_BAD = 9;
    private static final int ERROR_SERVER_SPEECH_TEXT_EXCESS = 10;
    private static final int ERROR_SERVER_UNSUPPORTED_SERVICE = 11;
    private static final int ERROR_SERVER_ALLOWED_REQUESTS_EXCESS = 13;
    private static final int ERROR_SERVER_SPEECH_TEXT_FORBIDDEN = 14;
    private static final int ERROR_UNKNOWN = 99;

    private String appKey;
    private String speechMode;
    private double speechSpeed;
    private String speechVoice;

    private String speechText;
    private TextToSpeech ttsClient;
    private TextToSpeechListener listener;

    private int receivedSize;
    private int sentSize;

    public String getSpeechMode() {
        return speechMode;
    }

    public double getSpeechSpeed() {
        return speechSpeed;
    }

    public String getSpeechVoice() {
        return speechVoice;
    }

    public String getSpeechText() {
        return speechText;
    }

    @SuppressWarnings("unused")
    public void setSpeechText(String text) {
        speechText = text;
    }

    @SuppressWarnings("unused")
    public void setListener(TextToSpeechListener listener) {
        this.listener = listener;
    }

    public TTSClient(TTSClient.Builder builder) {
        speechMode = builder.getSpeechMode();
        speechSpeed = builder.getSpeechSpeed();
        speechVoice = builder.getSpeechVoice();
        listener = builder.getListener();
    }

    public int getReceivedDataSize() {
        return receivedSize;
    }

    public int getSentDataSize() {
        return sentSize;
    }

    private TextToSpeech.Listener internalListener = new TextToSpeech.Listener() {
        @Override
        public void onInactive() {
            synchronized (this) {
                if (ttsClient != null) {
                    receivedSize = ttsClient.getRecvDataSizeSession();
                    sentSize = ttsClient.getSentDataSizeSession();
                }
                ttsClient = null;
            }

            if (listener != null) {
                listener.onFinished();
            }
        }

        @Override
        public void onError(int code, String message) {
            synchronized (this) {
                ttsClient = null;
            }

            if (listener != null) {
                int checkedCode = 0;
                int errorCodes[] = {
                        ERROR_NETWORK,
                        ERROR_NETWORK_TIMEOUT,
                        ERROR_CLIENT_INETRNAL,
                        ERROR_SERVER_INTERNAL,
                        ERROR_SERVER_TIMEOUT,
                        ERROR_SERVER_AUTHENTICATION,
                        ERROR_SERVER_SPEECH_TEXT_BAD,
                        ERROR_SERVER_SPEECH_TEXT_EXCESS,
                        ERROR_SERVER_UNSUPPORTED_SERVICE,
                        ERROR_SERVER_ALLOWED_REQUESTS_EXCESS,
                        ERROR_SERVER_SPEECH_TEXT_FORBIDDEN
                };
                for (int errorCode : errorCodes) {
                    if (code == errorCode) {
                        checkedCode = code;
                        break;
                    }
                }
                if (checkedCode == 0) {
                    checkedCode = ERROR_UNKNOWN;
                }

                listener.onError(checkedCode, message);
            }
        }
    };

    public boolean isPlaying() {
        synchronized (this) {
            return ttsClient != null;
        }
    }

    public boolean play() {
        if (isPlaying()) {
            return false;
        }

        receivedSize = 0;
        sentSize = 0;

        ttsClient = TextToSpeech.getInstance();


        ttsClient.setListener(internalListener);
        ttsClient.setTimeout(5000);

        Context appContext = TTSManager.getInstance().getApplicationContext();
        // 콘텍스트 오류 해결
        // 기존에 카카오 개발자에서 실수해서 TTSManager가 아닌 STTManager에서 콘텍스트를 얻어와서
        // 에러가 발생하였음.

        SystemInfo.initialize(appContext);
        String KAheader = SystemInfo.getKAHeader()+" newtoneSdk/6.0.0";
        Log.d("jack", "KA header : "+KAheader);

        appKey ="a6f7b4e7c17d4a8d440a48d286fe4554";
        // REST API AppKey를 직접 대입해서 해결

        ttsClient.setAPPKey(appKey);
        ttsClient.setKAheader(KAheader);
        ttsClient.setServer("cheez.voice.search.daum.net", 30000);
        ttsClient.setService(getSpeechMode());
        ttsClient.setSpeechText(getSpeechText());
        ttsClient.setSpeechVoice(getSpeechVoice());
        ttsClient.setSpeechSpeed(getSpeechSpeed());

        if (!ttsClient.playTTS()) {
            return false;
        }


        return true;
    }

    @SuppressWarnings("unused")
    public boolean play(String speechText) {
        setSpeechText(speechText);

        return play();
    }

    @SuppressWarnings("unused")
    public void stop() {
        synchronized (this) {
            if (ttsClient != null) {
                ttsClient.cancel();
                ttsClient = null;
            }
        }
    }

    public static class Builder {
        private String apiKey;
        private String appKey;
        private String speechMode = TextToSpeech.SpeechMode.NEWTONE_TALK_US; //.NEWTONE_TALK_2;
        private double speechSpeed = 1.1;
        private String speechVoice = VOICE_WOMAN_READ_CALM;
        private TextToSpeechListener listener;

        @Deprecated
        public TTSClient.Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public TTSClient.Builder setAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public String getSpeechMode() {
            return speechMode;
        }

        public TTSClient.Builder setSpeechMode(String mode) {
            speechMode = mode;
            return this;
        }

        public double getSpeechSpeed() {
            return speechSpeed;
        }

        @SuppressWarnings("unused")
        public TTSClient.Builder setSpeechSpeed(double speed) {
            speechSpeed = speed;
            return this;
        }

        public String getSpeechVoice() {
            return speechVoice;
        }

        public TTSClient.Builder setSpeechVoice(String voice) {
            String[] voices = {VOICE_WOMAN_READ_CALM, VOICE_MAN_READ_CALM, VOICE_WOMAN_DIALOG_BRIGHT, VOICE_MAN_DIALOG_BRIGHT};

            if (Arrays.asList(voices).contains(voice)) {
                speechVoice = voice;
            }

            return this;
        }

        public TextToSpeechListener getListener() {
            return listener;
        }

        @SuppressWarnings("unused")
        public TTSClient.Builder setListener(TextToSpeechListener listener) {
            this.listener = listener;
            return this;
        }

        public TTSClient build()  throws IllegalArgumentException {
            return new TTSClient(this);
        }
    }
}
