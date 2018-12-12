package com.vyankatesh.expensetracker.adapter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vyankatesh.expensetracker.R;
import com.vyankatesh.expensetracker.database.Categories;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewCategoryHolder> {

    private List<Categories> mCategoriesList;
    public interface OnItemClickListener{
        void onItemClicked(int position, int itemID);
    }

    private OnItemClickListener listener;

    public CategoriesAdapter(List<Categories> mCategoriesList, OnItemClickListener listener) {
        this.listener = listener;
        this.mCategoriesList = mCategoriesList;

    }

    public void setItems(List<Categories> list){
        mCategoriesList.clear();
        mCategoriesList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoriesAdapter.ViewCategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategoryHolder holder, int i) {

        holder.itemView.setOnClickListener(onclick -> {
            listener.onItemClicked(i, mCategoriesList.get(i).getId());
        });
        holder.mTextCategory.setText(mCategoriesList.get(i).getCategory());
    }

    public void setData(List<Categories> list) {
        mCategoriesList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public class ViewCategoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_category)
        TextView mTextCategory;

        ViewCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
