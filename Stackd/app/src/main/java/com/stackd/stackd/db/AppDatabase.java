package com.stackd.stackd.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.stackd.stackd.db.entities.Company;
import com.stackd.stackd.db.entities.CompanyTag;
import com.stackd.stackd.db.entities.Tag;

@Database(version = 1, entities = {Tag.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // DAOs go here ...
}
