package com.stackd.stackd.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.stackd.stackd.db.entities.TagEntity;

@Dao
public interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTag(TagEntity tag);

    @Query("SELECT * FROM tag WHERE id = :id")
    public TagEntity[] getTagsById(Long id);
}
