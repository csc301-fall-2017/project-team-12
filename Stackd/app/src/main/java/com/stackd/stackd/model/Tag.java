package com.stackd.stackd.model;

import android.content.Context;

import com.stackd.stackd.db.AppDatabase;

/**
 * Created by lana on 11/6/17.
 */

public class Tag implements ITag {

    Long id;
    String name;
    AppDatabase db;
    Context context;

    public Tag(Context context, Long id, String name){
        this.id = id;
        this.name = name;
        this.context = context;
        this.db = AppDatabase.getAppDatabase(context);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
