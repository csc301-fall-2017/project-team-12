package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "resume_tag",
        indices = @Index(
                value = {"rid", "tid"},
                unique = true),
        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = Resume.class,
                parentColumns = "id",
                childColumns = "rid"),
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "id",
                        childColumns = "tid")})
public class ResumeTag {

    private Long rid;
    private Long tid;


    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
