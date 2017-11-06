package com.stackd.stackd.db.daos;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.RecruiterEntity;
import com.stackd.stackd.db.entities.ResumeEntity;

@Dao
public interface RecruiterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecruiter(RecruiterEntity recruiter);

    @Query("SELECT * FROM recruiter")
    public RecruiterEntity[] getAllRecruiters();

    @Query("SELECT * FROM recruiter WHERE id = :id")
    public RecruiterEntity[] getRecruiter(Long id);

    @Query("SELECT * FROM recruiter WHERE first_name = :firstName AND last_name = :lastName")
    public RecruiterEntity[] getRecruiterByName(String firstName, String lastName);
}
