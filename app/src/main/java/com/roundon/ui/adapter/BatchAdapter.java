package com.roundon.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roundon.R;
import com.roundon.model.Batch;
import com.roundon.ui.widget.SplashLabel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqy on 15/12/19.
 */
public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.ViewHolder> {

    List<Batch> batches;
    Activity activity;

    public BatchAdapter(Activity activity) {
        this.activity = activity;
        this.batches = new ArrayList<>();
    }

    public void addList(List<Batch> list,int page) {
        if (list != null) {
            if (page==1){
                this.batches.clear();
            }
            this.batches.addAll(list);
            notifyDataSetChanged();
        }
    }

    public Batch getData(int pos){
        return this.batches.get(pos-1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_batch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Batch batch = this.batches.get(position);
        holder.published_at.setText(batch.published_at.substring(0, batch.published_at.indexOf("T")));
        holder.cameraman.setDesc(batch.curator.name);
        holder.bio.setDesc(batch.curator.bio);
    }

    @Override
    public int getItemCount() {
        return this.batches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.published_at)
        TextView published_at;

        @Bind(R.id.cameraman)
        SplashLabel cameraman;

        @Bind(R.id.bio)
        SplashLabel bio;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
