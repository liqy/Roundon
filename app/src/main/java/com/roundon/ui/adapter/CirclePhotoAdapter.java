package com.roundon.ui.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.roundon.R;
import com.roundon.model.Photo;
import com.roundon.ui.FullPhotoActivity;
import com.roundon.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liqy on 15/12/17.
 */
public class CirclePhotoAdapter extends RecyclerView.Adapter<CirclePhotoAdapter.ViewHolder> {

    List<Photo> photos;
    Activity activity;
    int type;

    public CirclePhotoAdapter(Activity activity, int type) {
        this.activity = activity;
        this.type = type;
        this.photos = new ArrayList<>();
    }

    public CirclePhotoAdapter(Activity activity) {
        this.activity = activity;
        this.type = 0;
        this.photos = new ArrayList<>();
    }

    public void addPhotos(List<Photo> list) {
        if (list != null) {
            this.photos.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle_photo, parent, false);
        return new ViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        holder.circleImageView.setBorderColor(Color.parseColor(photo.color));
        Glide.with(activity).load(photo.urls.small).into(holder.circleImageView);

        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullPhotoActivity.openFullPhotoActivity(activity, photo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

//        @Bind(R.id.rotateLayout)
//        RotateLayout rotateLayout;

        @Bind(R.id.circle_image)
        CircleImageView circleImageView;

        public ViewHolder(View view, int type) {
            super(view);
            ButterKnife.bind(this, view);
//            rotateLayout.setOrientation(90, true);
            DisplayMetrics dm = Utils.getDisplayMetrics(view.getContext());
            ViewGroup.LayoutParams params = circleImageView.getLayoutParams();
            if (type == 0) {
                params.width = dm.widthPixels;
                params.height = dm.widthPixels;
                circleImageView.setLayoutParams(params);
            }
        }
    }
}
