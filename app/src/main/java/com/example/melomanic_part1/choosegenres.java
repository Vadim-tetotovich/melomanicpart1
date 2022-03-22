package com.example.melomanic_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;



public class choosegenres extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    public static final String APP_PREFERENCES = "FilePreferences";

    TextView textView;
    public SharedPreferences mSettings;

    Button rockBtn, popBtn, indieBtn, punkBtn, russianBtn, hiphopBtn, btn2000s, btn2010s, btn90s, btn80s;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegenres);

        textView = findViewById(R.id.personName);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String perName = sharedPref.getString("NAME", "unknown");

        textView.setText("Hi, " + perName + "!");

//        rockBtn.findViewById(R.id.btn_choose_rock).setOnClickListener(onClickListener);
//        popBtn.findViewById(R.id.btn_choose_pop).setOnClickListener(onClickListener);
//        indieBtn.findViewById(R.id.btn_choose_indie).setOnClickListener(onClickListener);
//        punkBtn.findViewById(R.id.btn_choose_punk).setOnClickListener(onClickListener);
//        russianBtn.findViewById(R.id.btn_choose_russian).setOnClickListener(onClickListener);
//        hiphopBtn.findViewById(R.id.btn_choose_hiphop).setOnClickListener(onClickListener);
//        btn2000s.findViewById(R.id.btn_choose_2000).setOnClickListener(onClickListener);
//        btn2010s.findViewById(R.id.btn_choose_2010).setOnClickListener(onClickListener);
//        btn90s.findViewById(R.id.btn_choose_90).setOnClickListener(onClickListener);
//        btn80s.findViewById(R.id.btn_choose_80).setOnClickListener(onClickListener);
    }
}