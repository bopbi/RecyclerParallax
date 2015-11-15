package com.arjunalabs.android.recyclerparallax;

import android.graphics.Matrix;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by bobbyadiprabowo on 11/15/15.
 */
public class ParallaxScrollListener extends RecyclerView.OnScrollListener {

    private float scrollSpeed = 0.5f;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int firstVisible = layoutManager.findFirstVisibleItemPosition();
        int visibleCount = Math.abs(firstVisible - layoutManager.findLastVisibleItemPosition());

        Matrix imageMatrix;
        float tempSpeed = 0;

        if (dy > 0) {
            tempSpeed = scrollSpeed;
        } else if (dy < 0) {
            tempSpeed = -scrollSpeed;
        }

        for (int i = firstVisible; i < (firstVisible + visibleCount); i++) {
            ImageView imageView = ((ParallaxAdapter.ParallaxViewHolder) recyclerView.getLayoutManager().findViewByPosition(i).getTag()).parallaxImage;
            imageMatrix = imageView.getImageMatrix();
            imageMatrix.postTranslate(0, tempSpeed);
            imageView.setImageMatrix(imageMatrix);
            imageView.invalidate();
        }

    }
}
