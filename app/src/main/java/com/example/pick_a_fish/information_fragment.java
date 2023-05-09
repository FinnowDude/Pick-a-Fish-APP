package com.example.pick_a_fish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pick_a_fish.Utiliy.ModelRecyclerView;
import com.example.pick_a_fish.Utiliy.modelmodel;

import java.util.ArrayList;

public class information_fragment extends Fragment {

    View view;

    RecyclerView recyclerView;

    ArrayList<modelmodel> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_information_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycleViewInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));



        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM", "LOREM IPSUM"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM2", "LOREM IPSUM2"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM3", "LOREM IPSUM3"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM4", "LOREM IPSUM4"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM5", "LOREM IPSUM5"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM6", "LOREM IPSUM6"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM7", "LOREM IPSUM7"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM8", "LOREM IPSUM8"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "LOREM IPSUM9", "LOREM IPSUM9"));


        ModelRecyclerView modelRecyclerView = new ModelRecyclerView(getContext(), arrayList);
        recyclerView.setAdapter(modelRecyclerView);


        return view;
    }


}