package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.Review;

@Dao
public interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertReview(Review review);

    @Query("SELECT * FROM review WHERE reid = :resumeId")
    public Review[] getAllResumeReviews(Long resumeId);
}
