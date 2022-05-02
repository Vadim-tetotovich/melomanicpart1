package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    boolean isOpenApp = false;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


        isOpenApp = sharedPref.getBoolean("openApp", false);

        new Handler().postDelayed(() -> {
            if (isOpenApp) {
                startActivity(new Intent(Loading.this, BottomMenuLoad.class));
            } else {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("rockScore", "0");
                editor.putString("popScore", "0");
                editor.putString("indieScore", "0");
                editor.putString("punkScore", "0");
                editor.putString("russianScore", "0");
                editor.putString("hiphopScore", "0");
                editor.putString("s2010Score", "0");
                editor.putString("s2000Score", "0");
                editor.putString("s90Score", "0");
                editor.putString("s80Score", "0");
                editor.apply();
                startActivity(new Intent(Loading.this, MainActivity.class));
            }
            finish();
        }, 2000);
    }
}