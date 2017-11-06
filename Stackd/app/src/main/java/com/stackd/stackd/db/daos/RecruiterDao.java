package com.stackd.stackd.db.daos;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;

@Dao
public interface RecruiterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecruiter(Recruiter recruiter);

    @Query("SELECT * FROM recruiter")
    public Recruiter[] getAllRecruiters();

    @Query("SELECT * FROM recruiter WHERE id = :id")
    public Recruiter[] getRecruiter(Long id);

    @Query("SELECT * FROM recruiter WHERE first_name = :firstName AND last_name = :lastName")
    public Recruiter[] getRecruiterByName(String firstName, String lastName);
}
