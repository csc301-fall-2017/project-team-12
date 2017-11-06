package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.stackd.stackd.db.entities.CompanyTagEntity;

@Dao
public interface CompanyTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCompanyTag(CompanyTagEntity companyTag);
}
