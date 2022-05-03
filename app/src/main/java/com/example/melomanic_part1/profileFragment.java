package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    public static final String APP_PREFERENCES = "FilePreferences";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View viewProfile;
    Snackbar snackbar;

    SharedPreferences sharedPreferences;
    ImageButton editGenresBtn, rock80, rock50, pop80, pop50, indie80, indie50, punk80, punk50, russian80, russian50, hiphop80, hiphop50, s2010_80,s2010_50,s2000_80, s2000_50, s90_80, s90_50, s80_80, s80_50;
    String personName, sharedBtn1, sharedBtn2, sharedBtn3, avatarImage;
    TextView nameText, genre1, genre2, genre3;
    String rockScore, popScore, punkScore, indieScore, russianScore, hiphopScore, s2010Score, s2000Score, s90Score, s80Score;
    ArrayList<ImageButton> enablesBtn = new ArrayList<>();
    ImageView imageAvatar;

    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        this.viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);
        findElById();
        getSherPref();
        addInArrayListBtn();

        enablesBtn.forEach(imageButton -> {
            imageButton.setEnabled(false);
        });

        checkedAvatarImage();

        setImageBtnAvatars();

        nameText.setText("Hello, " + personName + "!");
        genre1.setText(sharedBtn1);
        genre2.setText(sharedBtn2);
        genre3.setText(sharedBtn3);

        enablesBtn.forEach(bnt -> {
            bnt.setOnClickListener(view -> {
                snackbar = Snackbar.make(view, "Set as avatar?", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.black))
                        .setActionTextColor(getResources().getColor(R.color.choose_btn_backcolor_default))
                        .setDuration(5000)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                snackbar.show();
                snackbar.setAction("SET", view1 -> {
                    workSnackBar((ImageButton) view);
                    snackbar.dismiss();
                });
            });
        });

        editGenresBtn.setOnClickListener(view -> {
            getActivity().finish();
            Intent intent = new Intent(getActivity(),editGenres.class);
            startActivity(intent);
        });

        return viewProfile;
    }

    private void checkedAvatarImage() {
        imageAvatar.setMaxWidth(136);
        imageAvatar.setMaxHeight(136);
        switch (avatarImage) {
            case "defaultAvatarImage":
                imageAvatar.setImageResource(R.drawable.default_avatar);
                break;
            case "rock80Avatar":
                imageAvatar.setImageResource(R.drawable.rock80);
                break;
            case "rock50Avatar":
                imageAvatar.setImageResource(R.drawable.rock50);
                break;
            case "pop80Avatar":
                imageAvatar.setImageResource(R.drawable.pop80);
                break;
            case "pop50Avatar":
                imageAvatar.setImageResource(R.drawable.pop50);
                break;
            case "punk80Avatar":
                imageAvatar.setImageResource(R.drawable.punk80);
                break;
            case "punk50Avatar":
                imageAvatar.setImageResource(R.drawable.punk50);
                break;
            case "indie80Avatar":
                imageAvatar.setImageResource(R.drawable.indie80);
                break;
            case "indie50Avatar":
                imageAvatar.setImageResource(R.drawable.indie50);
                break;
            case "russian80Avatar":
                imageAvatar.setImageResource(R.drawable.russian80);
                break;
            case "russian50Avatar":
                imageAvatar.setImageResource(R.drawable.russian50);
                break;
            case "hiphop80Avatar":
                imageAvatar.setImageResource(R.drawable.hiphop80);
                break;
            case "hiphop50Avatar":
                imageAvatar.setImageResource(R.drawable.hiphop50);
                break;
            case "2010s80Avatar":
                imageAvatar.setImageResource(R.drawable.s2010_80);
                break;
            case "2010s50Avatar":
                imageAvatar.setImageResource(R.drawable.s2010_50);
                break;
            case "2000s80Avatar":
                imageAvatar.setImageResource(R.drawable.s2000_80);
                break;
            case "2000s50Avatar":
                imageAvatar.setImageResource(R.drawable.s2000_50);
                break;
            case "90s80Avatar":
                imageAvatar.setImageResource(R.drawable.s90_80);
                break;
            case "90s50Avatar":
                imageAvatar.setImageResource(R.drawable.s90_50);
                break;
            case "80s80Avatar":
                imageAvatar.setImageResource(R.drawable.s80_80);
                break;
            case "80s50Avatar":
                imageAvatar.setImageResource(R.drawable.s80_50);
                break;
        }
    }

    private void workSnackBar(ImageButton btn) {
        String bntDescription = (String) btn.getContentDescription();
        setAvatar(bntDescription);
        avatarImage = sharedPreferences.getString("avatarImage", "unknown");
        checkedAvatarImage();
    }

    private void setAvatar(String description) {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("avatarImage", description);
        editor.apply();
    }

    private void setImageBtnAvatars() {
        if (Integer.parseInt(rockScore) >= 50) {
            rock50.setEnabled(true);
            rock50.setImageResource(R.drawable.rock50);
        }
        if (Integer.parseInt(rockScore) >= 80) {
            rock80.setEnabled(true);
            rock80.setImageResource(R.drawable.rock80);
        }
        if (Integer.parseInt(popScore) >= 50) {
            pop50.setEnabled(true);
            pop50.setImageResource(R.drawable.pop50);
        }
        if (Integer.parseInt(popScore) >= 80) {
            pop80.setEnabled(true);
            pop80.setImageResource(R.drawable.pop80);
        }
        if (Integer.parseInt(indieScore) >= 50) {
            indie50.setEnabled(true);
            indie50.setImageResource(R.drawable.indie50);
        }
        if (Integer.parseInt(indieScore) >= 80) {
            indie80.setEnabled(true);
            indie80.setImageResource(R.drawable.indie80);
        }
        if (Integer.parseInt(punkScore) >= 50) {
            punk50.setEnabled(true);
            punk50.setImageResource(R.drawable.punk50);
        }
        if (Integer.parseInt(punkScore) >= 80) {
            punk80.setEnabled(true);
            punk80.setImageResource(R.drawable.punk80);
        }
        if (Integer.parseInt(russianScore) >= 50) {
            russian50.setEnabled(true);
            russian50.setImageResource(R.drawable.russian50);
        }
        if (Integer.parseInt(russianScore) >= 80) {
            russian80.setEnabled(true);
            russian80.setImageResource(R.drawable.russian80);
        }
        if (Integer.parseInt(hiphopScore) >= 50) {
            hiphop50.setEnabled(true);
            hiphop50.setImageResource(R.drawable.hiphop50);
        }
        if (Integer.parseInt(hiphopScore) >= 80) {
            hiphop80.setEnabled(true);
            hiphop80.setImageResource(R.drawable.hiphop80);
        }
        if (Integer.parseInt(s2010Score) >= 50) {
            s2010_50.setEnabled(true);
            s2010_50.setImageResource(R.drawable.s2010_50);
        }
        if (Integer.parseInt(s2010Score) >= 80) {
            s2010_80.setEnabled(true);
            s2010_80.setImageResource(R.drawable.s2010_80);
        }
        if (Integer.parseInt(s2000Score) >= 50) {
            s2000_50.setEnabled(true);
            s2000_50.setImageResource(R.drawable.s2000_50);
        }
        if (Integer.parseInt(s2000Score) >= 80) {
            s2000_80.setEnabled(true);
            s2000_80.setImageResource(R.drawable.s2000_80);
        }
        if (Integer.parseInt(s90Score) >= 50) {
            s90_50.setEnabled(true);
            s90_50.setImageResource(R.drawable.s90_50);
        }
        if (Integer.parseInt(s90Score) >= 80) {
            s90_80.setEnabled(true);
            s90_80.setImageResource(R.drawable.s90_80);
        }
        if (Integer.parseInt(s80Score) >= 50) {
            s80_50.setEnabled(true);
            s80_50.setImageResource(R.drawable.s80_50);
        }
        if (Integer.parseInt(s80Score) >= 80) {
            s80_80.setEnabled(true);
            s80_80.setImageResource(R.drawable.s80_80);
        }
    }

    private void addInArrayListBtn() {
        enablesBtn.add(rock50);
        enablesBtn.add(rock80);
        enablesBtn.add(pop50);
        enablesBtn.add(pop80);
        enablesBtn.add(indie50);
        enablesBtn.add(indie80);
        enablesBtn.add(punk50);
        enablesBtn.add(punk80);
        enablesBtn.add(russian50);
        enablesBtn.add(russian80);
        enablesBtn.add(hiphop50);
        enablesBtn.add(hiphop80);
        enablesBtn.add(s2000_50);
        enablesBtn.add(s2000_80);
        enablesBtn.add(s2010_50);
        enablesBtn.add(s2010_80);
        enablesBtn.add(s90_50);
        enablesBtn.add(s90_80);
        enablesBtn.add(s80_50);
        enablesBtn.add(s80_80);
    }

    private void getSherPref() {
        personName = sharedPreferences.getString("NAME", "unknown");
        sharedBtn1 = sharedPreferences.getString("button1", "unknown");
        sharedBtn2 = sharedPreferences.getString("button2", "unknown");
        sharedBtn3 = sharedPreferences.getString("button3", "unknown");

        avatarImage = sharedPreferences.getString("avatarImage", "unknown");

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

    private void findElById() {
        nameText = this.viewProfile.findViewById(R.id.profileText);
        genre1 = this.viewProfile.findViewById(R.id.genres1);
        genre2 = this.viewProfile.findViewById(R.id.genres2);
        genre3 = this.viewProfile.findViewById(R.id.genres3);
        editGenresBtn = this.viewProfile.findViewById(R.id.edit_genres);

        imageAvatar = this.viewProfile.findViewById(R.id.avatarImage);

        rock50 = this.viewProfile.findViewById(R.id.rock_50);
        rock80= this.viewProfile.findViewById(R.id.rock_80);
        pop50 = this.viewProfile.findViewById(R.id.pop_50);
        pop80 = this.viewProfile.findViewById(R.id.pop_80);
        indie50 = this.viewProfile.findViewById(R.id.indie_50);
        indie80 = this.viewProfile.findViewById(R.id.indie_80);
        punk50 = this.viewProfile.findViewById(R.id.punk_50);
        punk80= this.viewProfile.findViewById(R.id.punk_80);
        russian50 = this.viewProfile.findViewById(R.id.russian_50);
        russian80 = this.viewProfile.findViewById(R.id.russian_80);
        hiphop50 = this.viewProfile.findViewById(R.id.hiphop_50);
        hiphop80 = this.viewProfile.findViewById(R.id.hiphop_80);
        s2010_50 = this.viewProfile.findViewById(R.id.s2010_50);
        s2010_80 = this.viewProfile.findViewById(R.id.s2010_80);
        s2000_50 = this.viewProfile.findViewById(R.id.s2000_50);
        s2000_80 = this.viewProfile.findViewById(R.id.s2000_80);
        s90_50 = this.viewProfile.findViewById(R.id.s90_50);
        s90_80 = this.viewProfile.findViewById(R.id.s90_80);
        s80_50 = this.viewProfile.findViewById(R.id.s80_50);
        s80_80 = this.viewProfile.findViewById(R.id.s80_80);
    }

}