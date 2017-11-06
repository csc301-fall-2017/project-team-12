package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.RecruiterResumeEntity;
import com.stackd.stackd.db.entities.ResumeEntity;
import com.stackd.stackd.db.entities.ResumeTagEntity;
import com.stackd.stackd.helpers.Resume;

/**
 * Created by Musa on 11/6/2017.
 */

public interface RecruiterResumeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecruiterResume(RecruiterResumeEntity recruiterResume);

    @Query("SELECT r.*" +
            "FROM recruiter_resume rs JOIN resume r ON rs.resId = r.id " +
            "WHERE rs.recId = :recID;")
    public ResumeEntity[] getAllResumes(Long recId);
}
