package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class end_game extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    SharedPreferences sharedPreferences;
    ProgressBar avatarProgress;
    ImageView avatar80, avatar50;
    TextView endGameTitle, endGameText, endGameScore;
    Button btnHome, btnRestart;

    String thisGenre, lastScore;
    String noAvatar = "Almost there!", yesAvatar = "You earn new avatar!";
    int niceScore;
    String rockScore, popScore, punkScore, indieScore, russianScore, hiphopScore, s2010Score, s2000Score, s90Score, s80Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        getSharPrefValues();
        findElementsById();
        setContent();

        avatarProgress.setMax(100);
        avatarProgress.setProgress(niceScore);

        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, BottomMenuLoad.class);
            startActivity(intent);
        });

    }

    private void setContent() {
        endGameTitle.setText(thisGenre);
        endGameScore.setText(lastScore);

        niceScore = Integer.parseInt(lastScore);
        if (niceScore >= 50) {
            endGameText.setText(yesAvatar);
            setAvatar50();
        } else if (niceScore >= 80) {
            setAvatar80();
        } else {
            endGameText.setText(noAvatar);
        }

    }

    private void setAvatar80() {
        avatar80.setMaxWidth(64);
        avatar80.setMaxHeight(64);
        switch (thisGenre) {
            case "Rock":
                avatar80.setImageResource(R.drawable.rock80);
                checkedScore(rockScore);
                break;
            case "Pop":
                avatar80.setImageResource(R.drawable.pop80);
                checkedScore(popScore);
                break;
            case "Punk":
                avatar80.setImageResource(R.drawable.punk80);
                checkedScore(punkScore);
                break;
            case "Indie":
                avatar80.setImageResource(R.drawable.indie80);
                checkedScore(indieScore);
                break;
            case "Russian":
                avatar80.setImageResource(R.drawable.russian80);
                checkedScore(russianScore);
                break;
            case "Hip-Hop":
                avatar80.setImageResource(R.drawable.hiphop80);
                checkedScore(hiphopScore);
                break;
            case "2010s":
                avatar80.setImageResource(R.drawable.s2010_80);
                checkedScore(s2010Score);
                break;
            case "2000s":
                avatar80.setImageResource(R.drawable.s2000_80);
                checkedScore(s2000Score);
                break;
            case "90s":
                avatar80.setImageResource(R.drawable.s90_80);
                checkedScore(s90Score);
                break;
            case "80s":
                avatar80.setImageResource(R.drawable.s80_80);
                checkedScore(s80Score);
                break;
        }
    }

    private void setAvatar50() {
        avatar50.setMaxWidth(64);
        avatar50.setMaxHeight(64);
        switch (thisGenre) {
            case "Rock":
                avatar50.setImageResource(R.drawable.rock50);
                checkedScore(rockScore);
                break;
            case "Pop":
                avatar50.setImageResource(R.drawable.pop50);
                checkedScore(popScore);
                break;
            case "Punk":
                avatar50.setImageResource(R.drawable.punk50);
                checkedScore(punkScore);
                break;
            case "Indie":
                avatar50.setImageResource(R.drawable.indie50);
                checkedScore(indieScore);
                break;
            case "Russian":
                avatar50.setImageResource(R.drawable.russian50);
                checkedScore(russianScore);
                break;
            case "Hip-Hop":
                avatar50.setImageResource(R.drawable.hiphop50);
                checkedScore(hiphopScore);
                break;
            case "2010s":
                avatar50.setImageResource(R.drawable.s2010_50);
                checkedScore(s2010Score);
                break;
            case "2000s":
                avatar50.setImageResource(R.drawable.s2000_50);
                checkedScore(s2000Score);
                break;
            case "90s":
                avatar50.setImageResource(R.drawable.s90_50);
                checkedScore(s90Score);
                break;
            case "80s":
                avatar50.setImageResource(R.drawable.s80_50);
                checkedScore(s80Score);
                break;
        }
    }

    private void checkedScore(String score) {
        if (niceScore > Integer.parseInt(score)) {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(score, lastScore);
            editor.apply();
        }
    }

    private void getSharPrefValues() {
        thisGenre = sharedPreferences.getString("thisGenre", "unknown");
        lastScore = sharedPreferences.getString("EndScore", "unknown");

        rockScore = sharedPreferences.getString("rockScore", "");
        popScore = sharedPreferences.getString("popScore", "");
        punkScore = sharedPreferences.getString("punkScore", "");
        indieScore = sharedPreferences.getString("indieScore", "");
        russianScore = sharedPreferences.getString("russianScore", "");
        hiphopScore = sharedPreferences.getString("hiphopScore", "");
        s2010Score = sharedPreferences.getString("s2010Score", "");
        s2000Score = sharedPreferences.getString("s2000Score", "");
        s90Score = sharedPreferences.getString("s90Score", "");
        s80Score = sharedPreferences.getString("s80Score", "");
    }

    private void findElementsById() {
        avatarProgress = findViewById(R.id.progressBarAvatar);
        avatar80 = findViewById(R.id.endGameAvatar80);
        avatar50 = findViewById(R.id.endGameAvatar50);
        endGameTitle = findViewById(R.id.endGameTitle);
        endGameText = findViewById(R.id.endGameText);
        endGameScore = findViewById(R.id.endGameScoreValue);
        btnHome = findViewById(R.id.endGameHome);
        btnRestart = findViewById(R.id.endGameRestart);
    }
}