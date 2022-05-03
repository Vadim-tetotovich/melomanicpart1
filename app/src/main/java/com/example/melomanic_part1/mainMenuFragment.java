package com.example.melomanic_part1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    SharedPreferences sharedPreferences;
    Button button1, button2, button3;
    int imgRes;
    String sharedBtn1, sharedBtn2, sharedBtn3;

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
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        sharedBtn1 = sharedPreferences.getString("button1", "unknown");
        sharedBtn2 = sharedPreferences.getString("button2", "unknown");
        sharedBtn3 = sharedPreferences.getString("button3", "unknown");

        this.view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        button1 = this.view.findViewById(R.id.main_display_btn1);
        button2 = this.view.findViewById(R.id.main_display_btn2);
        button3 = this.view.findViewById(R.id.main_display_btn3);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);

        setIconForBtn(sharedBtn1, button1);
        setIconForBtn(sharedBtn2, button2);
        setIconForBtn(sharedBtn3, button3);

        button1.setText(sharedBtn1);
        button2.setText(sharedBtn2);
        button3.setText(sharedBtn3);

        return view;
    }

    Button.OnClickListener onClickListener = view -> {
        Button b = (Button) view;
        String text = (String) b.getText();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("thisGenre", text);
        editor.apply();

        getActivity().finish();
        Intent intent = new Intent(getActivity(),playMusic.class);
        startActivity(intent);
    };

    private void setIconForBtn(String sharedBtn, Button button) {
        switch (sharedBtn) {
            case "Rock":
                imgRes = R.drawable.rockicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
            case "Pop":
                imgRes = R.drawable.popicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
            case "Punk":
                imgRes = R.drawable.punkicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
            case "Indie":
                imgRes = R.drawable.indieicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
            case "Russian":
                imgRes = R.drawable.russianicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
            case "Hip-Hop":
                imgRes = R.drawable.hiphopicon;
                button.setCompoundDrawablesRelativeWithIntrinsicBounds(imgRes,0,0,0);
                break;
        }

    }

}
