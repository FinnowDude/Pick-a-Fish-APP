package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class home_fragment extends Fragment {

    View view;
    ImageView imgScan_Camera;
    ImageView imgSuggest_Camera;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

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