package com.arjunalabs.android.recyclerparallax;

import android.graphics.Matrix;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by bobbyadiprabowo on 11/15/15.
 */
public class ParallaxScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        ((ParallaxAdapter)recyclerView.getAdapter()).reTranslate();
    }
}
