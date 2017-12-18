package com.arjunalabs.android.recyclerparallax;

import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobbyadiprabowo on 3/3/15.
 */
public class ParallaxAdapter extends RecyclerView.Adapter<ParallaxAdapter.ParallaxViewHolder> {

    private List<ParallaxModel> parallaxModelList;
    private int recyclerViewHeight;
    private List<ParallaxViewHolder> parallaxViewHolderList;

    public ParallaxAdapter(List<ParallaxModel> parallaxModelList, int recyclerViewHeight) {
        this.parallaxModelList = parallaxModelList;
        this.recyclerViewHeight = recyclerViewHeight;
        parallaxViewHolderList = new ArrayList<>();
    }

    @Override
    public ParallaxViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_parallax, viewGroup, false);
        ParallaxViewHolder parallaxViewHolder = new ParallaxViewHolder(v, recyclerViewHeight);
        parallaxViewHolderList.add(parallaxViewHolder);
        return parallaxViewHolder;
    }

    @Override
    public void onBindViewHolder(ParallaxViewHolder parallaxViewHolder, int i) {
        ParallaxModel parallaxModel = parallaxModelList.get(i);

        parallaxViewHolder.parallaxText.setText(parallaxModel.getTitle());

        // for now we will hardcode
        // the example image height is 300px
        int drawableImage = R.drawable.lorempixel;
        if (i % 2 == 0) {
            drawableImage = R.drawable.lorempixel2;
        } else if (i % 3 == 0) {
            drawableImage = R.drawable.lorempixel3;
        }

        // calculate matrix translation;
        parallaxViewHolder.translate();

        parallaxViewHolder.parallaxImage.setImageResource(drawableImage);

        parallaxViewHolder.itemView.setTag(parallaxViewHolder);

    }


    public void reTranslate() {
        for (ParallaxViewHolder parallaxViewHolder : parallaxViewHolderList) {
            parallaxViewHolder.translate();
        }
    }

    @Override
    public int getItemCount() {
        // hardcoded first
        return parallaxModelList.size();
    }

    @Override
    public void onViewRecycled(ParallaxViewHolder parallaxViewHolder) {
        super.onViewRecycled(parallaxViewHolder);

        parallaxViewHolder.translate();
        // this is set manually to show to the center
    }

    public static class ParallaxViewHolder extends RecyclerView.ViewHolder {
        public ImageView parallaxImage;
        public TextView parallaxText;
        private int recyclerViewHeight;
        private View itemView;

        public ParallaxViewHolder(View itemView, int recyclerViewHeight) {
            super(itemView);
            this.itemView = itemView;
            parallaxImage = (ImageView) itemView.findViewById(R.id.image_background);
            parallaxImage.setScaleType(ImageView.ScaleType.MATRIX);
            parallaxText = (TextView) itemView.findViewById(R.id.text_title);
            this.recyclerViewHeight = recyclerViewHeight;
        }

        public void translate() {
            float translate = -itemView.getY() * ((float)parallaxImage.getMeasuredHeight() / (float)recyclerViewHeight);
            Matrix matrix = new Matrix();
            matrix.postTranslate(0, translate);

            parallaxImage.setImageMatrix(matrix);
        }
    }
}
