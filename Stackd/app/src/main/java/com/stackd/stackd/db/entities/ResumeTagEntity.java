package com.stackd.stackd.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "resume_tag",
        indices = {@Index(
                value = {"rid", "tid"},
                unique = true),
                @Index("rid"),
                @Index("tid")},

        /* Foreign key constraints */
        foreignKeys = {@ForeignKey(
                entity = ResumeEntity.class,
                parentColumns = "id",
                childColumns = "rid",
                onDelete = CASCADE),
                @ForeignKey(
                        entity = TagEntity.class,
                        parentColumns = "id",
                        childColumns = "tid",
                        onDelete = CASCADE)})
public class ResumeTagEntity {

    @PrimaryKey
    private Long id;
    private Long rid;
    private Long tid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
