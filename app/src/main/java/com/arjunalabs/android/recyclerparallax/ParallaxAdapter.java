package com.arjunalabs.android.recyclerparallax;

import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
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

    public ParallaxAdapter(List<ParallaxModel> parallaxModelList) {
        this.parallaxModelList = parallaxModelList;
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
        if (i % 2 == 0) {
            parallaxViewHolder.parallaxImage.setImageResource(R.drawable.lorempixel2);
        } else if (i % 3 == 0) {
            parallaxViewHolder.parallaxImage.setImageResource(R.drawable.lorempixel3);
        } else {
            parallaxViewHolder.parallaxImage.setImageResource(R.drawable.lorempixel);
        }

        parallaxViewHolder.itemView.setTag(parallaxModel);
    }

    @Override
    public int getItemCount() {
        // hardcoded first
        return parallaxModelList.size();
    }

    public static class ParallaxViewHolder extends RecyclerView.ViewHolder {
        public ImageView parallaxImage;
        public TextView parallaxText;

        public ParallaxViewHolder(View itemView) {
            super(itemView);
            parallaxImage = (ImageView) itemView.findViewById(R.id.image_background);
            parallaxImage.setScaleType(ImageView.ScaleType.MATRIX);
            Matrix matrix = parallaxImage.getImageMatrix();
            // because the image background size is 400 x 300
            // this is set manually to show to the center
            matrix.postTranslate(0, -100);
            parallaxImage.setImageMatrix(matrix);
            parallaxText = (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
