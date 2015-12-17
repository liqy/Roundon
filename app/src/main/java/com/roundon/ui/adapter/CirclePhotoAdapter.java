package com.roundon.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.roundon.model.Photo;

import java.util.ArrayList;

/**
 * Created by liqy on 15/12/17.
 */
public class CirclePhotoAdapter extends RecyclerView.Adapter<CirclePhotoAdapter.ViewHolder>{

    ArrayList<Photo> photos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view){
            super(view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
