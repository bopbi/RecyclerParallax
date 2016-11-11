package com.arjunalabs.android.recyclerparallax;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bobbyadiprabowo on 3/3/15.
 */
public class ParallaxAdapter extends RecyclerView.Adapter<ParallaxAdapter.ParallaxViewHolder> {

    private List<ParallaxModel> parallaxModelList;
    private static final int imageViewHeight = 100;
    private float translateScale;

    public ParallaxAdapter(List<ParallaxModel> parallaxModelList, int recyclerViewHeight, float recyclerViewYPos) {
        this.parallaxModelList = parallaxModelList;
        translateScale =  (float)imageViewHeight / (float)recyclerViewHeight;

    }

    @Override
    public ParallaxViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_parallax, viewGroup, false);
        return new ParallaxViewHolder(v);
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

        parallaxViewHolder.parallaxImage.setImageResource(drawableImage);

        // calculate matrix translation;
        int viewHeight = parallaxViewHolder.parallaxImage.getMeasuredHeight();
        int drawableHeight = parallaxViewHolder.parallaxImage.getDrawable().getIntrinsicHeight();
        int position = (drawableHeight - viewHeight) / 2;

        Matrix matrix = parallaxViewHolder.parallaxImage.getImageMatrix();
        matrix.postTranslate(0, -position);
        parallaxViewHolder.parallaxImage.setImageMatrix(matrix);

        parallaxViewHolder.itemView.setTag(parallaxViewHolder);
    }

    @Override
    public int getItemCount() {
        // hardcoded first
        return parallaxModelList.size();
    }

    @Override
    public void onViewRecycled(ParallaxViewHolder parallaxViewHolder) {
        super.onViewRecycled(parallaxViewHolder);
        parallaxViewHolder.parallaxImage.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix matrix = parallaxViewHolder.parallaxImage.getImageMatrix();
        // this is set manually to show to the center
        matrix.reset();
        parallaxViewHolder.parallaxImage.setImageMatrix(matrix);

    }

    public static class ParallaxViewHolder extends RecyclerView.ViewHolder {
        public ImageView parallaxImage;
        public TextView parallaxText;

        public ParallaxViewHolder(View itemView) {
            super(itemView);
            parallaxImage = (ImageView) itemView.findViewById(R.id.image_background);
            parallaxImage.setScaleType(ImageView.ScaleType.MATRIX);
            parallaxText = (TextView) itemView.findViewById(R.id.text_title);
        }
    }

    public float getTranslationScale() {
        return translateScale;
    }
}
