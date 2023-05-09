package com.example.pick_a_fish.Utiliy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pick_a_fish.R;
import com.example.pick_a_fish.information_activity;

import java.util.ArrayList;

public class ModelRecyclerView extends RecyclerView.Adapter<ModelRecyclerView.ViewHolder> {


    Context context;
    ArrayList<modelmodel> arrayList = new ArrayList<>();

    public ModelRecyclerView(Context context, ArrayList<modelmodel> arrayList){

        this.context=context;
        this.arrayList = arrayList;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.move_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.infoIMG.setImageResource(arrayList.get(position).getInfoIMG());
        holder.infoName.setText(arrayList.get(position).getInfoName());
        holder.infoDesc.setText(arrayList.get(position).getInfoDesc());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"LOREM IPSUM" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, information_activity.class);
                intent.putExtra("fishname",arrayList.get(position).getInfoName());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView infoName, infoDesc;
        ImageView infoIMG;
        CardView cardView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            infoIMG = itemView.findViewById(R.id.imgInfo);
            infoName = itemView.findViewById(R.id.infoName);
            infoDesc = itemView.findViewById(R.id.infoDesc);

            cardView = itemView.findViewById(R.id.cardview);



        }
    }
}
