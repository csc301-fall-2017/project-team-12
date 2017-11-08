package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity( tableName = "company_tag",
        primaryKeys = {"cid", "tid"},
        indices = @Index(
                value = {"cid", "tid"},
                unique = true),
        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = CompanyEntity.class,
                parentColumns = "id",
                childColumns = "cid"),
                @ForeignKey(
                        entity = TagEntity.class,
                        parentColumns = "id",
                        childColumns = "tid")})

public class CompanyTagEntity {

    private Long cid;
    private Long tid;

    public CompanyTagEntity(Long cid, Long tid) {
        this.cid = cid;
        this.tid = tid;
    }


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}