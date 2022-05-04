package com.example.melomanic_part1;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class playMusic extends AppCompatActivity {

    public static final String APP_PREFERENCES = "FilePreferences";
    String url = "https://api.jsonbin.io/b/626d79c025069545a32b468d/9";

    SharedPreferences sharedPreferences;
    RequestQueue mQueue;
    TextView title, level, score;
    MediaPlayer mPlayer;
    Button chooseBtn1, chooseBtn2, chooseBtn3, chooseBtn4;
    Random random;
    JSONObject music;
    String correctMusicTitle,titleGenre;
    int levelCount = 0, i = 0, levelValue = 0;
    JSONObject randomObject;
    String randomTitle;
    int isCorrectSong = 0, scoreValueS = 0, handlerRunProgress = 0;
    ProgressBar songProgressBar;
    Handler handlerProgress;
    ImageView sticker183, sticker200;

    ArrayList<String> guessedSongs = new ArrayList<>();
    ArrayList<String> musicTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        titleGenre = sharedPreferences.getString("thisGenre", "unknown");

        random = new Random();

        title = findViewById(R.id.musicGenreTitle);
        chooseBtn1 = findViewById(R.id.chooseBtn1);
        chooseBtn2 = findViewById(R.id.chooseBtn2);
        chooseBtn3 = findViewById(R.id.chooseBtn3);
        chooseBtn4 = findViewById(R.id.chooseBtn4);
        level = findViewById(R.id.levelCount);
        score = findViewById(R.id.scoreValue);
        songProgressBar = findViewById(R.id.progressBarForSong);
        sticker183 = findViewById(R.id.transparent183);
        sticker200 = findViewById(R.id.transparent200);

        title.setText(titleGenre);

        mQueue = Volley.newRequestQueue(this);
        handlerProgress = new Handler();

        playSong();
    }

    public void playSong() {
        @SuppressLint({"SetTextI18n", "ResourceType"}) JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (handlerRunProgress != 0) {
                            Thread.sleep(1000);
                        }

//                        sticker200.setImageResource(R.drawable.transparent200);
//                        sticker183.setImageResource(R.drawable.transparent183);

                        chooseBtn1.setTextColor(getResources().getColor(R.color.categories_title));
                        chooseBtn2.setTextColor(getResources().getColor(R.color.categories_title));
                        chooseBtn3.setTextColor(getResources().getColor(R.color.categories_title));
                        chooseBtn4.setTextColor(getResources().getColor(R.color.categories_title));

                        level.setText((levelCount + 1) + " / 10");
                        String sc = "" + scoreValueS;
                        score.setText(sc);

                        JSONObject playlist = response.getJSONObject(titleGenre);
                        JSONArray musics = playlist.getJSONArray("music");

                        do {
                            music = musics.getJSONObject(random.nextInt(musics.length()));
                            correctMusicTitle = music.getString("musicName");
                        } while (!(checkedGuessedSongs(correctMusicTitle)));

                        String src = music.getString("src");

                        guessedSongs.add(correctMusicTitle);
                        musicTitles.add(correctMusicTitle);

                        for (i = 0; i < 3; i++) {
                            randomTitles(musics);
                        }

                        Collections.shuffle(musicTitles);

                        setTextButtons(musicTitles);

                        mPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(src, "raw", getPackageName()));

                        progressBarSong();

                        chooseBtn1.setOnClickListener(onClickListener);
                        chooseBtn2.setOnClickListener(onClickListener);
                        chooseBtn3.setOnClickListener(onClickListener);
                        chooseBtn4.setOnClickListener(onClickListener);

                    } catch (JSONException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        mQueue.add(request);
        levelValue++;
    }

    private void progressBarSong() {
        mPlayer.start();

        songProgressBar.setMax(100);
        songProgressBar.setProgress(0);

        ObjectAnimator.ofInt(songProgressBar, "progress", 100)
                .setDuration(10000)
                .start();
    }

    Button.OnClickListener onClickListener = view -> {
        Button b = (Button) view;
            String textBtn = b.getText().toString();

            String correctTextBtn1 = chooseBtn1.getText().toString();
            String correctTextBtn2 = chooseBtn2.getText().toString();
            String correctTextBtn3 = chooseBtn3.getText().toString();
            String correctTextBtn4 = chooseBtn4.getText().toString();

            if (textBtn.equals(correctMusicTitle)) {
//                setSticker();
                b.setTextColor(getResources().getColor(R.color.correctly));
                isCorrectSong = 1;
                setScoreForTheLevel();
            } else {
                if (correctMusicTitle.equals(correctTextBtn1))
                    chooseBtn1.setTextColor(getResources().getColor(R.color.correctly));
                else if (correctMusicTitle.equals(correctTextBtn2))
                    chooseBtn2.setTextColor(getResources().getColor(R.color.correctly));
                else if (correctMusicTitle.equals(correctTextBtn3))
                    chooseBtn3.setTextColor(getResources().getColor(R.color.correctly));
                else if (correctMusicTitle.equals(correctTextBtn4))
                    chooseBtn4.setTextColor(getResources().getColor(R.color.correctly));
                b.setTextColor(getResources().getColor(R.color.wrong));
            }
            mPlayer.stop();

        if (levelValue < 10) {
            musicTitles.clear();
            levelCount++;
            playSong();
        } else {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("EndScore", String.valueOf(scoreValueS));
            editor.apply();
            finish();
            Intent intent = new Intent(this, end_game.class);
            startActivity(intent);
        }
    };

    private void setSticker() {
        Random stickerRandom = new Random();
        int stickRand = stickerRandom.nextInt(2);
        if (stickRand == 1) {
            Random placeRandom = new Random();
            int place = placeRandom.nextInt(2);
            if (place == 1) {
                sticker200.setMaxWidth(200);
                sticker200.setMaxHeight(200);
                sticker200.setImageResource(R.drawable.sticker2);
            } else {
                sticker183.setMaxWidth(183);
                sticker183.setMaxHeight(183);
                sticker183.setImageResource(R.drawable.sticker1);
            }
        }
    }

    private void setScoreForTheLevel() {
        int currentProgress = mPlayer.getCurrentPosition();
        if (currentProgress >= 8500) {
            scoreValueS = scoreValueS + 5;
        } else if (currentProgress <= 2000) {
            scoreValueS = scoreValueS + 10;
        } else if (currentProgress > 2000 && currentProgress < 8500) {
            scoreValueS = scoreValueS + ((int) (10 - Math.floor(currentProgress / 1000)) + 2);
        }
    }

    public boolean checkedGuessedSongs(String correctSongTitle) {
        return !guessedSongs.contains(correctSongTitle);
    }

    public boolean checkRepeatedTitles(String randomTitle) {
        return !musicTitles.contains(randomTitle);
    }

    public void randomTitles(JSONArray musics) throws JSONException {
        do {
            randomObject = musics.getJSONObject(random.nextInt(musics.length()));
            randomTitle = randomObject.getString("musicName");
        } while (!(checkRepeatedTitles(randomTitle)));
            musicTitles.add(randomTitle);
    }

    public void setTextButtons(ArrayList<String> titles) {
        chooseBtn1.setText(titles.get(0));
        chooseBtn2.setText(titles.get(1));
        chooseBtn3.setText(titles.get(2));
        chooseBtn4.setText(titles.get(3));
    }
}
