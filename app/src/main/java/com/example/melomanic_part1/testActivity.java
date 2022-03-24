package com.example.melomanic_part1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class testActivity extends AppCompatActivity {

    int currentProgress = 0;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ProgressBar progressBar = findViewById(R.id.progress_Bar);


        final CountDownTimer countDownTimer = new CountDownTimer(11*1000, 100) {
            @Override
            public void onTick(long l) {
                currentProgress += 1;
                progressBar.setProgress(currentProgress);
                progressBar.setMax(100);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Progress Finished", Toast.LENGTH_LONG).show();
            }
        };

        countDownTimer.start();
    }
}