package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.Company;

import java.util.List;

@Dao
public interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertCompany(Company company);

    @Delete
    public void removeCompany(Company company);

    @Query("SELECT * FROM company")
    public Company[] getAllCompanies();

    @Query("SELECT * FROM company WHERE name = :companyName")
    public Company[] getCompany(String companyName);
}
