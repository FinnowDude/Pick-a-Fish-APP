package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class home_fragment extends Fragment {

    View view;
    ImageView imgScan_Camera;
    ImageView imgSuggest_Camera;
    TextView txtIntelligent,txtBreak,txtStudy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_fragment, container, false);


        //Related Topic onClick

        txtIntelligent = view.findViewById(R.id.txtIntelligent);
        txtBreak = view.findViewById(R.id.txtBreak);
        txtStudy = view.findViewById(R.id.txtStudy);

        txtStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), fish_study.class);
                startActivity(intent);
            }
        });
        txtBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), breakups.class);
                startActivity(intent);
            }
        });
        txtIntelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), intelligence.class);
                startActivity(intent);
            }
        });


        imgScan_Camera = view.findViewById(R.id.scan_camera);
        imgScan_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), scan_camera.class);
                startActivity(intent);


            }
        });

        imgSuggest_Camera = view.findViewById(R.id.suggestfish_camera);
        imgSuggest_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),suggestfish.class);
                startActivity(intent);
            }
        });

        return view;
    }


}