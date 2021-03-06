package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class editGenres extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    Button rockBtn, popBtn, indieBtn, punkBtn, russianBtn, hiphopBtn, btn2000s, btn2010s, btn90s, btn80s, submitBtn;
    ArrayList<Button> selectedButtons = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_genres);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String perName = sharedPref.getString("NAME", "unknown");

        TextView editGenresTitle = findViewById(R.id.editGenresTitle);

        editGenresTitle.setText("What’s up " + perName + "!");

        View.OnClickListener onClickListener = view -> {
            Button button = (Button)view;
            if (this.selectedButtons.contains(button))
            {
                view.setBackgroundColor(getResources().getColor(R.color.choose_btn_backcolor_default));
                this.selectedButtons.remove(button);
            }
            else if (this.selectedButtons.size() < 3)
            {
                view.setBackgroundColor(getResources().getColor(R.color.choose_btn_backcolor_active));
                this.selectedButtons.add(button);
            }
        };

        rockBtn = findViewById(R.id.btn_edit_rock);
        rockBtn.setOnClickListener(onClickListener);
        popBtn = findViewById(R.id.btn_edit_pop);
        popBtn.setOnClickListener(onClickListener);
        indieBtn = findViewById(R.id.btn_edit_indie);
        indieBtn.setOnClickListener(onClickListener);
        punkBtn = findViewById(R.id.btn_edit_punk);
        punkBtn.setOnClickListener(onClickListener);
        russianBtn = findViewById(R.id.btn_edit_russian);
        russianBtn.setOnClickListener(onClickListener);
        hiphopBtn = findViewById(R.id.hbtn_edit_hiphop);
        hiphopBtn.setOnClickListener(onClickListener);
        btn2000s = findViewById(R.id.btn_edit_2000);
        btn2000s.setOnClickListener(onClickListener);
        btn2010s = findViewById(R.id.btn_edit_2010);
        btn2010s.setOnClickListener(onClickListener);
        btn90s = findViewById(R.id.btn_edit_90);
        btn90s.setOnClickListener(onClickListener);
        btn80s = findViewById(R.id.btn_edit_80);
        btn80s.setOnClickListener(onClickListener);

        submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(view -> {
            if (this.selectedButtons.size() == 3) {
                String button1 = selectedButtons.get(0).getText().toString();
                String button2 = selectedButtons.get(1).getText().toString();
                String button3 = selectedButtons.get(2).getText().toString();

                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("button1", button1);
                editor.putString("button2", button2);
                editor.putString("button3", button3);
                editor.putBoolean("openApp", true);
                editor.apply();
                finish();
                Intent intent = new Intent(this, BottomMenuLoad.class);
                startActivity(intent);
            }
        });

    }
}