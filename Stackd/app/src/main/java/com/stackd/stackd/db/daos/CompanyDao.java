package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.CompanyEntity;

import java.util.List;

@Dao
public interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertCompany(CompanyEntity company);

    @Delete
    public void removeCompany(CompanyEntity company);

    @Query("SELECT * FROM company")
    public CompanyEntity[] getAllCompanies();

    @Query("SELECT * FROM company WHERE name = :companyName")
    public CompanyEntity[] getCompany(String companyName);
}
