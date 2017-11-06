package com.stackd.stackd.model;

import android.content.Context;

import com.stackd.stackd.db.AppDatabase;
import com.stackd.stackd.db.entities.CompanyTagEntity;
import com.stackd.stackd.db.entities.TagEntity;

/**
 * Created by lana on 11/6/17.
 */

public class Company implements ICompany {

    Long id;
    String name;
    AppDatabase db;
    Context context;
    public Company(Context context, Long id, String name ){
        this.id = id;
        this.name = name;
        this.context = context;
        this.db = AppDatabase.getAppDatabase(context);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TagEntity[] getTags() {
       return db.companyTagDao().getCompanyTags(this.id);

    }

    @Override
    public void addTag(ITag tag) {
        db.companyTagDao().insertCompanyTag(new CompanyTagEntity(this.id, tag.getId()));
    }

}
