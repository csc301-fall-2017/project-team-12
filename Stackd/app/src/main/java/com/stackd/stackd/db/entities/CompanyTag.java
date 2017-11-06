package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

@Entity(tableName = "company_tag",
        indices = @Index(
                value = {"cid", "tid"},
                unique = true),
        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = Company.class,
                parentColumns = "id",
                childColumns = "cid",
                onDelete = RESTRICT),
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "id",
                        childColumns = "tid",
                        onDelete = RESTRICT)})
public class CompanyTag {

    private Long cid;
    private Long tid;


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
