package com.example.moneytracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneytracker.R;
import com.example.moneytracker.database.model.Categories;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CategoriesAdapter extends SelectableAdapter<CategoriesAdapter.CategoriesHolder> {
    private List<Categories> categoryList;
    private ClickListener clickListener;

    public CategoriesAdapter(List<Categories> categoryList, ClickListener clickListener) {
        this.categoryList = categoryList;
        this.clickListener = clickListener;
    }

    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoriesHolder vh = new CategoriesHolder(itemView, clickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, int position) {
        Categories category = categoryList.get(position);
        holder.name.setText(category.getName());
        holder.view.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return this.categoryList.size();
    }

    public void refresh(List<Categories> data) {
        categoryList.clear();
        categoryList.addAll(data);
        notifyDataSetChanged();
    }

    public void removeItems(List<Integer> positions) {

        Collections.sort(positions, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs - lhs;
            }
        });

        while (!positions.isEmpty()) {
            removeItem(positions.get(0));
            positions.remove(0);
        }

    }

    public void removeItem(int position) {
        if (categoryList.get(position) != null) {
            categoryList.get(position).delete();
            categoryList.remove(position);
            notifyItemRemoved(position);
        }
    }

    class CategoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        View view;

        private ClickListener clickListener;
        public TextView name;
        public CategoriesHolder(View v, ClickListener clickListener) {
            super(v);
            view = v.findViewById(R.id.selected_overlay);
            name = (TextView) v.findViewById(R.id.category_name);
            this.clickListener = clickListener;
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void bindCategory(Categories category) {
            name.setText(category.getName());
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if (clickListener != null) {
                clickListener.onItemLongClick(getAdapterPosition());
                return true;
            } else {
                return false;
            }
        }
    }

}
