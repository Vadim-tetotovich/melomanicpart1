package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";

    EditText nameInput;
    int isCheckedSwitch = 0;
    public SharedPreferences mSettings;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonContinue = findViewById(R.id.button_continue);

        nameInput = (EditText) findViewById(R.id.textInputEditText);
        Switch switchVisible = findViewById(R.id.switch_accept);


        switchVisible.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) {
                isCheckedSwitch = 1;
            } else {
                isCheckedSwitch = 0;
            }
        });

        buttonContinue.setOnClickListener(view -> {
            if (nameInput.length() != 0 && isCheckedSwitch == 1) {
                String textInputNamePerson = nameInput.getText().toString();

                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("NAME", textInputNamePerson);
                editor.apply();
                finish();
                Intent intent = new Intent(this, choosegenres.class);
                startActivity(intent);
            }
        });

    }
}

