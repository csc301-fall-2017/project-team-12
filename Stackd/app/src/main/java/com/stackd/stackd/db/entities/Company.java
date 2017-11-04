package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "company")
public class Company {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
