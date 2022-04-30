package com.example.melomanic_part1;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class playMusic extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";
    String url = "https://api.jsonbin.io/b/626d79c025069545a32b468d/2";

    TextView title;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String titleGenre = sharedPreferences.getString("thisGenre", "unknown");

        title = findViewById(R.id.musicGenreTitle);
        title.setText(titleGenre);

        RequestQueue mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("music");
                            JSONObject musics = jsonArray.getJSONObject(0);
                            String src = musics.getString("src");
                            mPlayer = MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(src, "raw", getPackageName()));
                            mPlayer.start();
//                            title.setText(name);
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