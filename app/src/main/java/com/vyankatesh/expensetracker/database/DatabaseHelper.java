package com.vyankatesh.expensetracker.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

public class DatabaseHelper {

    private final Context mContext;

    public DatabaseHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void insertRecord(String category) {
        new Insert().execute(new Categories(category));
    }

    public void insertTransaction(Transactions transaction){
        AsyncTask.execute(() -> {
            AppDatabase database = AppDatabase.getDatabase(mContext.getApplicationContext());
            database.transactionsDao().insert(transaction);
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class Insert extends AsyncTask<Categories, Void, Void> {

        @Override
        protected Void doInBackground(Categories... categories) {
            AppDatabase db = AppDatabase.getDatabase(mContext.getApplicationContext());
            db.categoriesDao().insert(categories[0]);
            return null;
        }
    }
}
