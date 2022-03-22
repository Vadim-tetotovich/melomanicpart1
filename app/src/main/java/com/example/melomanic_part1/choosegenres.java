package com.example.melomanic_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.concurrent.atomic.AtomicInteger;


public class choosegenres extends AppCompatActivity {

//    private final static String FILE_NAME = "content.txt";
    public static final String APP_PREFERENCES = "FilePreferences";

    TextView textView;
    public SharedPreferences mSettings;

    Button rockBtn, popBtn, indieBtn, punkBtn, russianBtn, hiphopBtn, btn2000s, btn2010s, btn90s, btn80s, letsGoBtn;
    int countChossedGenres = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegenres);

        textView = findViewById(R.id.personName);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String perName = sharedPref.getString("NAME", "unknown");

        textView.setText("Hi, " + perName + "!");




        OnClickListener onClickListener = view -> {
            view.setBackgroundColor(getResources().getColor(R.color.choose_btn_backcolor_active));
            countChossedGenres++;
        };

        rockBtn = findViewById(R.id.btn_choose_rock);
        rockBtn.setOnClickListener(onClickListener);
        popBtn = findViewById(R.id.btn_choose_pop);
        popBtn.setOnClickListener(onClickListener);
        indieBtn = findViewById(R.id.btn_choose_indie);
        indieBtn.setOnClickListener(onClickListener);
        punkBtn = findViewById(R.id.btn_choose_punk);
        punkBtn.setOnClickListener(onClickListener);
        russianBtn = findViewById(R.id.btn_choose_russian);
        russianBtn.setOnClickListener(onClickListener);
        hiphopBtn = findViewById(R.id.hbtn_choose_hiphop);
        hiphopBtn.setOnClickListener(onClickListener);
        btn2000s = findViewById(R.id.btn_choose_2000);
        btn2000s.setOnClickListener(onClickListener);
        btn2010s = findViewById(R.id.btn_choose_2010);
        btn2010s.setOnClickListener(onClickListener);
        btn90s = findViewById(R.id.btn_choose_90);
        btn90s.setOnClickListener(onClickListener);
        btn80s = findViewById(R.id.btn_choose_80);
        btn80s.setOnClickListener(onClickListener);

        letsGoBtn = findViewById(R.id.lets_go_btn);
        letsGoBtn.setOnClickListener(view -> {
            if (countChossedGenres == 3) {

            }
        });

    }
}