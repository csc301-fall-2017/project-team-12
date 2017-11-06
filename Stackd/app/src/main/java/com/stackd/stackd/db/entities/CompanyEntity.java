package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "company")
public class CompanyEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;

    public CompanyEntity(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public Long getId() { return this.id; }
    public void setName(String name) {
        this.name = name;
    }

}
