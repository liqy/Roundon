package com.roundon.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roundon.R;
import com.roundon.model.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqy on 15/12/19.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories;
    Activity activity;

    public CategoryAdapter(Activity activity) {
        this.activity = activity;
        this.categories = new ArrayList<>();
    }

    public void addList(List<Category> list){
        if (list!=null){
            this.categories.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = this.categories.get(position);
        holder.name.setText(category.title);
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
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
