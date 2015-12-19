package com.roundon.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roundon.R;
import com.roundon.model.Batch;

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
        this.batches=new ArrayList<>();
    }

    public void addList(List<Batch> list){
        if (list!=null){
            this.batches.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.item_batch,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Batch batch=this.batches.get(position);
        holder.name.setText(batch.published_at);
    }

    @Override
    public int getItemCount() {
        return this.batches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
