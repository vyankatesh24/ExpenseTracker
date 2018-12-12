package com.vyankatesh.expensetracker.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vyankatesh.expensetracker.R;
import com.vyankatesh.expensetracker.adapter.CategoriesAdapter;
import com.vyankatesh.expensetracker.database.AppDatabase;
import com.vyankatesh.expensetracker.database.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTab3 extends Fragment implements CategoriesAdapter.OnItemClickListener {

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
        mCategoriesAdapter = new CategoriesAdapter(new ArrayList<>(0), this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mCategoriesView.getContext(),
                LinearLayoutManager.VERTICAL);
        mCategoriesView.setAdapter(mCategoriesAdapter);
        mCategoriesView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    public void setData() {
        new LoadCategories().execute();
    }

    @Override
    public void onItemClicked(int position, int itemID) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("Delete Category", (dialog, which) -> {
            AppDatabase database = AppDatabase.getDatabase(Objects.requireNonNull(getContext()).getApplicationContext());
            Log.v("SizeOfList", String.valueOf(mCategoriesList.size()));
            AsyncTask.execute(() -> database.categoriesDao().deleteCategory(itemID));
            mCategoriesList.remove(position);
            Log.v("SizeOfList", String.valueOf(mCategoriesList.size()));
            new LoadCategories().execute();
            mCategoriesAdapter.setItems(mCategoriesList);
        });
        builder.setNegativeButton("Edit Category", (dialog, which) -> {

        });
        builder.setTitle("Choose any option");
        builder.setMessage("");
        AlertDialog dialog = builder.create();
        dialog.show();
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
                mCategoriesAdapter.setData(mCategoriesList);
            }
            super.onPostExecute(aVoid);
        }
    }
}
