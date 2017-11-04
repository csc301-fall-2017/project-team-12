package com.stackd.stackd.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1, entities = {})
public abstract class AppDatabase extends RoomDatabase {
    // DAOs go here ...
}
