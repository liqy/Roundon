package com.roundon.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.roundon.R;
import com.roundon.model.Photo;
import com.roundon.ui.FullPhotoActivity;
import com.roundon.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqy on 15/12/20.
 */
public class CardPhotoAdapter extends RecyclerView.Adapter<CardPhotoAdapter.ViewHolder> {

    List<Photo> photos;
    Activity activity;
    int type;

    public CardPhotoAdapter(Activity activity, int type) {
        this.activity = activity;
        this.type = type;
        this.photos = new ArrayList<>();
    }

    public CardPhotoAdapter(Activity activity) {
        this.activity = activity;
        this.type = 0;
        this.photos = new ArrayList<>();
    }

    public void addPhotos(List<Photo> list,int page) {
        if (list != null) {
            if (page==1){
                this.photos.clear();
            }
            this.photos.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_photo, parent, false);
        return new ViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        Glide.with(activity).load(photo.urls.thumb).into(holder.circleImageView);
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
        @Bind(R.id.cardImage)
        ImageView circleImageView;

        public ViewHolder(View view, int type) {
            super(view);
            ButterKnife.bind(this, view);
            DisplayMetrics dm = Utils.getDisplayMetrics(view.getContext());
            ViewGroup.LayoutParams params = circleImageView.getLayoutParams();
            if (type == 0) {
                params.width = dm.widthPixels;
                params.height = dm.widthPixels;
            }
            else {
                int width = (int) (dm.widthPixels / 3.0);
                params.width = width;
                params.height = width;
            }
            circleImageView.setLayoutParams(params);
        }
    }
}
