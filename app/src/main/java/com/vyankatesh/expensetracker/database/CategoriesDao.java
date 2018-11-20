package com.vyankatesh.expensetracker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoriesDao {
    @Query("SELECT * FROM Categories order by mId asc")
    List<Categories> getAllCategories();

    @Insert
    void insert(Categories category);

    @Query("Delete from Categories where mId= :id")
    void deleteCategory(int id);
}
