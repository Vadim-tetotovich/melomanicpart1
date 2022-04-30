package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String personName = sharedPreferences.getString("NAME", "unknown");
        String sharedBtn1 = sharedPreferences.getString("button1", "unknown");
        String sharedBtn2 = sharedPreferences.getString("button2", "unknown");
        String sharedBtn3 = sharedPreferences.getString("button3", "unknown");

        this.viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView nameText = this.viewProfile.findViewById(R.id.profileText);
        TextView genre1 = this.viewProfile.findViewById(R.id.genres1);
        TextView genre2 = this.viewProfile.findViewById(R.id.genres2);
        TextView genre3 = this.viewProfile.findViewById(R.id.genres3);

        Button editGenresBtn = this.viewProfile.findViewById(R.id.edit_genres);

        nameText.setText("Hello, " + personName + "!");
        genre1.setText(sharedBtn1);
        genre2.setText(sharedBtn2);
        genre3.setText(sharedBtn3);

        editGenresBtn.setOnClickListener(view -> {

        });

        return viewProfile;
    }

}