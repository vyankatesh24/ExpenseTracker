package com.vyankatesh.expensetracker.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Categories {
    @PrimaryKey(autoGenerate = true)
    private int mId;
    @ColumnInfo(name = "Category")
    private String mCategory;

    Categories(String mCategory) {
        this.mCategory = mCategory;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
