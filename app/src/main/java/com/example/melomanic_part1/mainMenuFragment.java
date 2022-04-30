package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainMenuFragment extends Fragment {

    public static final String APP_PREFERENCES = "FilePreferences";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;

    public mainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mainMenuFragment newInstance(String param1, String param2) {
        mainMenuFragment fragment = new mainMenuFragment();
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

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String sharedBtn1 = sharedPreferences.getString("button1", "unknown");
        String sharedBtn2 = sharedPreferences.getString("button2", "unknown");
        String sharedBtn3 = sharedPreferences.getString("button3", "unknown");

        this.view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        Button button1 = this.view.findViewById(R.id.main_display_btn1);
        Button button2 = this.view.findViewById(R.id.main_display_btn2);
        Button button3 = this.view.findViewById(R.id.main_display_btn3);

        int imgRes = R.drawable.rockicon;
        button1.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);

        button1.setText(sharedBtn1);
        button2.setText(sharedBtn2);
        button3.setText(sharedBtn3);

        return view;
    }

}
