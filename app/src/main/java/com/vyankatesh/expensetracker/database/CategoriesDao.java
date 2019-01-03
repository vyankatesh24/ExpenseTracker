package com.vyankatesh.expensetracker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategoriesDao {
    @Query("SELECT * FROM Categories order by mId asc")
    List<Categories> getAllCategories();

    @Insert
    void insert(Categories category);

    @Query("Delete from Categories where mId= :id")
    void deleteCategory(int id);

    @Query("UPDATE Categories SET Category= :category WHERE mId = :id")
    void updateCategory(int id, String category);
}
