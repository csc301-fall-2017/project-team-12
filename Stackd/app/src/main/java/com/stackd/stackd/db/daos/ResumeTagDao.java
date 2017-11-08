package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.ResumeEntity;
import com.stackd.stackd.db.entities.ResumeTagEntity;
import com.stackd.stackd.db.entities.ResumeTagEntity;

@Dao
public interface ResumeTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertResumeTag(ResumeTagEntity resumeTag);

    @Query("SELECT r.* FROM" +
            " resume r JOIN (SELECT * FROM resume_tag WHERE tid = :tid) rt ON r.id = rt.rid")
    public ResumeEntity[] getResumesByTag(Long tid);
}
