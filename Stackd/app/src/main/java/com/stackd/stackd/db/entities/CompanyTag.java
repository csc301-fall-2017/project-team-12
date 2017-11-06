package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "company_tag",
        indices = {@Index(
                value = {"cid", "tid"},
                unique = true),
                @Index(value = "cid"),
                @Index(value = "tid")},

        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = Company.class,
                parentColumns = "id",
                childColumns = "cid",
                onDelete = CASCADE),
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "id",
                        childColumns = "tid",
                        onDelete = CASCADE)})
public class CompanyTag {

    @PrimaryKey
    private Long id;
    private Long cid;
    private Long tid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
