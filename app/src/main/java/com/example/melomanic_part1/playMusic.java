package com.example.melomanic_part1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class playMusic extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String titleGenre = sharedPreferences.getString("thisGenre", "unknown");

        title = findViewById(R.id.musicGenreTitle);
        title.setText(titleGenre);


    }
}