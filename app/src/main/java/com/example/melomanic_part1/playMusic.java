package com.example.melomanic_part1;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class playMusic extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";
    String url = "https://api.jsonbin.io/b/626d79c025069545a32b468d/5";

    TextView title;
    MediaPlayer mPlayer;
    Button chooseBtn1, chooseBtn2, chooseBtn3, chooseBtn4;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        random = new Random();

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String titleGenre = sharedPreferences.getString("thisGenre", "unknown");

        title = findViewById(R.id.musicGenreTitle);
        chooseBtn1 = findViewById(R.id.chooseBtn1);
        chooseBtn2 = findViewById(R.id.chooseBtn2);
        chooseBtn3 = findViewById(R.id.chooseBtn3);
        chooseBtn4 = findViewById(R.id.chooseBtn4);

        title.setText(titleGenre);

        RequestQueue mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject playlist = response.getJSONObject(titleGenre);
                            JSONArray musics = playlist.getJSONArray("music");
                            JSONObject music = musics.getJSONObject(random.nextInt(musics.length()));
                            String src = music.getString("src");
                            String correctMusicTitle = music.getString("musicName");

                            chooseBtn1.setText(correctMusicTitle);

                            mPlayer = MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(src, "raw", getPackageName()));
                            mPlayer.start();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
