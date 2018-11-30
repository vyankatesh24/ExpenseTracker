package com.vyankatesh.expensetracker.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vyankatesh.expensetracker.R;
import com.vyankatesh.expensetracker.adapter.CategoriesAdapter;
import com.vyankatesh.expensetracker.database.AppDatabase;
import com.vyankatesh.expensetracker.database.Categories;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTab3 extends Fragment {

    @BindView(R.id.list_categories)
    RecyclerView mCategoriesView;
    private CategoriesAdapter mCategoriesAdapter;
    private List<Categories> mCategoriesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab3, container, false);
        new LoadCategories().execute();
        ButterKnife.bind(this, view);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mCategoriesView.getContext(),
                LinearLayoutManager.VERTICAL);
        mCategoriesView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    public void setData() {
        new LoadCategories().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadCategories extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getDatabase(Objects.requireNonNull(getContext()).getApplicationContext());
            mCategoriesList = db.categoriesDao().getAllCategories();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mCategoriesList != null && !mCategoriesList.isEmpty()) {
                mCategoriesAdapter = new CategoriesAdapter(mCategoriesList);
                mCategoriesView.setAdapter(mCategoriesAdapter);
            }
            super.onPostExecute(aVoid);
        }
    }
}
