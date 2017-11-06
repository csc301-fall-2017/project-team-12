package com.stackd.stackd.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.stackd.stackd.db.daos.CompanyDao;
import com.stackd.stackd.db.daos.CompanyTagDao;
import com.stackd.stackd.db.daos.RecruiterDao;
import com.stackd.stackd.db.daos.ResumeDao;
import com.stackd.stackd.db.daos.ResumeTagDao;
import com.stackd.stackd.db.daos.ReviewDao;
import com.stackd.stackd.db.daos.TagDao;
import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.CompanyTag;
import com.stackd.stackd.db.entities.Recruiter;
import com.stackd.stackd.db.entities.Resume;
import com.stackd.stackd.db.entities.ResumeTag;
import com.stackd.stackd.db.entities.Review;
import com.stackd.stackd.db.entities.Tag;

@Database(version = 1, entities = {Company.class, CompanyTag.class, Recruiter.class,
                                    Resume.class, ResumeTag.class, Review.class, Tag.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CompanyDao companyDao();
    public abstract CompanyTagDao companyTagDao();
    public abstract RecruiterDao recruiterDao();
    public abstract ResumeDao resumeDao();
    public abstract ResumeTagDao resumeTagDao();
    public abstract ReviewDao reviewDao();
    public abstract TagDao tagDao();
}
