package com.example.melomanic_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

public class MainDisplay extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String sharedBtn1 = sharedPref.getString("button1", "unknown");
        String sharedBtn2 = sharedPref.getString("button2", "unknown");
        String sharedBtn3 = sharedPref.getString("button3", "unknown");

        Button button1 = findViewById(R.id.main_display_btn1);
        Button button2 = findViewById(R.id.main_display_btn2);
        Button button3 = findViewById(R.id.main_display_btn3);

        button1.setText(sharedBtn1);
        button2.setText(sharedBtn2);
        button3.setText(sharedBtn3);

    }
}