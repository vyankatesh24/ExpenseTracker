package com.vyankatesh.expensetracker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TransactionsDao {

    @Insert
    void insert(Transactions transaction);

    @Query("SELECT * FROM transactions")
    List<Transactions> getAllTransactions();

}
