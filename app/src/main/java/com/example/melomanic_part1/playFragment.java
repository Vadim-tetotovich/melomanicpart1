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

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link playFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class playFragment extends Fragment {

    public static final String APP_PREFERENCES = "FilePreferences";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View playView;

    public playFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment playFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static playFragment newInstance(String param1, String param2) {
        playFragment fragment = new playFragment();
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

    ImageButton rockBtn, popBtn, indieBtn, punkBtn, russianBtn, hiphopBtn, btn2000s, btn2010s, btn90s, btn80s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        playView = inflater.inflate(R.layout.fragment_play, container, false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        View.OnClickListener onClickListener = view -> {
            String description = (String) view.getContentDescription();
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("thisGenre", description);
            editor.apply();

            Intent intent = new Intent(getActivity(),playMusic.class);
            startActivity(intent);
        };

        this.playView = inflater.inflate(R.layout.fragment_play, container, false);

        rockBtn =  this.playView.findViewById(R.id.rockPlayBtn);
        rockBtn.setOnClickListener(onClickListener);
        popBtn = this.playView.findViewById(R.id.popPlayBtn);
        popBtn.setOnClickListener(onClickListener);
        indieBtn = this.playView.findViewById(R.id.indiePlayBtn);
        indieBtn.setOnClickListener(onClickListener);
        punkBtn = this.playView.findViewById(R.id.punkPlayBtn);
        punkBtn.setOnClickListener(onClickListener);
        russianBtn = this.playView.findViewById(R.id.russianPlayBtn);
        russianBtn.setOnClickListener(onClickListener);
        hiphopBtn = this.playView.findViewById(R.id.hiphopPlayBtn);
        hiphopBtn.setOnClickListener(onClickListener);
        btn2000s = this.playView.findViewById(R.id.s2000PlayBtn);
        btn2000s.setOnClickListener(onClickListener);
        btn2010s = this.playView.findViewById(R.id.s2010PlayBtn);
        btn2010s.setOnClickListener(onClickListener);
        btn90s = this.playView.findViewById(R.id.s90PlayBtn);
        btn90s.setOnClickListener(onClickListener);
        btn80s = this.playView.findViewById(R.id.s80PlayBtn);
        btn80s.setOnClickListener(onClickListener);


        return playView;
    }
}