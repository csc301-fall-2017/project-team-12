package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "tag",
        indices = {@Index(value = "name", unique = true)})
public class TagEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
