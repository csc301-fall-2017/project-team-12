package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.Resume;

@Dao
public interface ResumeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertResume(Resume resume);

    @Query("SELECT * FROM resume")
    public Resume[] getAllResumes();
}
