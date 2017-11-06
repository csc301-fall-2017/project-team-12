package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "review",
        indices = {@Index(
                value = {"rcid", "reid"},
                unique = true)},
        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = RecruiterEntity.class,
                parentColumns = "id",
                childColumns = "rcid"),
                @ForeignKey(
                        entity = ResumeEntity.class,
                        parentColumns = "id",
                        childColumns = "reid")})
public class ReviewEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private Long rcid; /* Recruiter ID */
    private Long reid; /* Resume ID */

    @IntRange(from=0, to=10)
    private int rating;
    private Date reviewDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRcid() {
        return rcid;
    }

    public void setRcid(Long rcid) {
        this.rcid = rcid;
    }

    public Long getReid() {
        return reid;
    }

    public void setReid(Long reid) {
        this.reid = reid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
