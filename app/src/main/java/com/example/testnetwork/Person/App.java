package com.example.testnetwork.Person;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class App extends Application {

    public static App instance;

    private AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .build();
    }



    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDataBase(){
        return dataBase;
    }

}

