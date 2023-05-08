package com.example.pick_a_fish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pick_a_fish.Modals.Post;
import com.example.pick_a_fish.PostDetailActivity;
import com.example.pick_a_fish.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context mContext;
    List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_post_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgPostProfile);
        holder.tvDate.setText(timeStampToString((long)mData.get(position).getTimeStamp()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvDate;
        ImageView imgPost;
        ImageView imgPostProfile;


        public MyViewHolder(View itemView){
            super(itemView);


            tvTitle = itemView.findViewById(R.id.row_post_title);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);
            tvDate = itemView.findViewById(R.id.row_post_date);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title",mData.get(position).getTitle());
                    postDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    postDetailActivity.putExtra("description", mData.get(position).getDescription());
                    postDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPhoto",mData.get(position).getUserPhoto());


                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername());

                    long timestamp = (long) mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate",timestamp);
                    mContext.startActivity(postDetailActivity);



                }
            });


        }


    }


    private  String timeStampToString(long time){

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yy",calendar).toString();
        return date;

    }
}
