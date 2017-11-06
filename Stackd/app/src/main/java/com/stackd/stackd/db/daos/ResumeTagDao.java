package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.stackd.stackd.db.entities.ResumeTagEntity;
import com.stackd.stackd.db.entities.ResumeTagEntity;

@Dao
public interface ResumeTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertResumeTag(ResumeTagEntity resumeTag);
}
