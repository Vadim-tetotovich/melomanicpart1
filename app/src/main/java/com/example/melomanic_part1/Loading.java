package com.example.melomanic_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

public class Loading extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    boolean isOpenApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        isOpenApp = sharedPref.getBoolean("openApp", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isOpenApp) {
                    startActivity(new Intent(Loading.this, MainDisplay.class));
                } else {
                    startActivity(new Intent(Loading.this, MainActivity.class));
                }
                finish();
            }
        }, 2000);
    }
}