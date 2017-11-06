package com.stackd.stackd.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

import java.util.Date;

@Entity(tableName = "resume",
        indices = {@Index(
                value = "url",
                unique = true)},
        /* Foreign key constraints */
        foreignKeys = @ForeignKey(
                entity = Recruiter.class,
                parentColumns = "id",
                childColumns = "rid",
                onDelete = RESTRICT))
public class Resume {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long rid;

    @ColumnInfo(name = "candidate_name")
    private String candidateName;

    @ColumnInfo(name = "collection_date")
    private Date collectionDate;
    private String url;
    private String recruiterComments;


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

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecruiterComments() {
        return recruiterComments;
    }

    public void setRecruiterComments(String recruiterComments) {
        this.recruiterComments = recruiterComments;
    }
}
