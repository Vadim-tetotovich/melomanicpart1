package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomMenuLoad extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu_load);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_1);

    }

    mainMenuFragment mainMenuFragment = new mainMenuFragment();

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.page_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mainMenuFragment).commit();
                return true;

            case R.id.page_2:
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, secondFragment).commit();
                return true;

            case R.id.page_3:
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, thirdFragment).commit();
                return true;
        }
        return false;
    }
}