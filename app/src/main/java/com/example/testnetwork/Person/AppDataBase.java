package com.example.testnetwork.Person;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

@Database(entities = {Person.class},version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PersonDao personDao();

//    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//        }
//    };
}
