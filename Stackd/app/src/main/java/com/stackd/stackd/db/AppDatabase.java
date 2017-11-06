package com.stackd.stackd.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

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

import com.stackd.stackd.helpers.Converters;

@Database(version = 1,
        entities = {Company.class, CompanyTag.class,
                Resume.class, ResumeTag.class,
                Tag.class,
                Recruiter.class,
                Review.class},
        exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CompanyDao companyDao();
    public abstract CompanyTagDao companyTagDao();
    public abstract ResumeDao resumeDao();
    public abstract ResumeTagDao resumeTagDao();
    public abstract RecruiterDao recruiterDao();
    public abstract ReviewDao reviewDao();
    public abstract TagDao tagDao();

    private static String name = "StackdDB";
    private static AppDatabase db;

    public static AppDatabase getAppDatabase(Context context) {
        if( db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.name).build();
        }
        return db;
    }
}
