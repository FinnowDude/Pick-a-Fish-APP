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



        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Bangus", "Milk Fish"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Bolinao", "Anchovies"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Dalagang Bukid", "Yellow Tail fusilier"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Galunggong", "Blue Mackerel Scad"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Hasa hasa", "Short Mackerel"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Maya maya", "Whole red snapper"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Sapsap", "Slipmouth fish"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Tamban", "Herring or Sardine"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Tilapia", "St. Peter's fish"));
        arrayList.add(new modelmodel(R.drawable.place_holder_rt, "Tulingan", "Mackerel Tuna"));



        ModelRecyclerView modelRecyclerView = new ModelRecyclerView(getContext(), arrayList);
        recyclerView.setAdapter(modelRecyclerView);


        return view;
    }

}