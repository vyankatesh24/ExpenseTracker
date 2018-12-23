package com.vyankatesh.expensetracker.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Transactions {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "note")
    private String note;

    public Transactions(String category, String date, int amount, String note) {
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    /*@ColumnInfo(name = "category")
    private String mCategory;

    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "amount")
    private int mAmount;

    @ColumnInfo(name = "note")
    private String mNote;

    public Transactions(String mCategory, String mDate, int mAmount, String mNote) {
        this.mCategory = mCategory;
        this.mDate = mDate;
        this.mAmount = mAmount;
        this.mNote = mNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcategory() {
        return mCategory;
    }

    public void setcategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String gedate() {
        return mDate;
    }

    public void setdate(String mDate) {
        this.mDate = mDate;
    }

    public int getamount() {
        return mAmount;
    }

    public void setamount(int mAmount) {
        this.mAmount = mAmount;
    }

    public String getnote() {
        return mNote;
    }

    public void setnote(String mNote) {
        this.mNote = mNote;
    }*/
}
