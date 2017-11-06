package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.TagEntity;
import com.stackd.stackd.db.entities.CompanyTagEntity;

@Dao
public interface CompanyTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCompanyTag(CompanyTagEntity companyTag);

    @Query("SELECT t.* FROM company_tag ct JOIN tag t ON ct.tid = t.id WHERE cid = :cid")
    public TagEntity[] getCompanyTags(Long cid);
}
