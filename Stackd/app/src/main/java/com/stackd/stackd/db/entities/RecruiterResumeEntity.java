package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Musa on 11/6/2017.
 */

@Entity(tableName = "recruiter_resume",
        primaryKeys = {"recId", "resId"},
        indices = {@Index(
                value = {"recId", "resId"}),
                @Index("recId"),
                @Index("resId"),
                @Index("date")},

        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = ResumeEntity.class,
                parentColumns = "id",
                childColumns = "resId",
                onDelete = CASCADE),
                @ForeignKey(
                        entity = RecruiterEntity.class,
                        parentColumns = "id",
                        childColumns = "recId",
                        onDelete = CASCADE)})

public class RecruiterResumeEntity {

    private Long recId;
    private Long resId;
    private String date;


    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long rid) {
        this.recId = recId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long tid) {
        this.resId = resId;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
