package com.example.pick_a_fish.Utiliy;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceingItemsDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;



    public SpaceingItemsDecorator(int verticalSpaceHeight){
        this.verticalSpaceHeight = verticalSpaceHeight;


    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = verticalSpaceHeight;
    }

}
