package com.hado.myexample.instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hado.myexample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstagramActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "ee586d716c744ca5bd8c697e7f718b01";
    public static final String CLIENT_SECRET = "7e52e940f153469e9794d36f73a0ef4d";
    public static final String CALLBACK_URL = "https://www.google.com.vn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_authorize)
    public void authorizeClick() {
        InstagramAuthenticationDialog.getInstance(this, CLIENT_ID, CALLBACK_URL, new InstagramAuthenticationDialog.OAuthInstagramListener() {
            @Override
            public void onSuccess(String accessToken) {

            }
        }).show();
    }

}
