package com.example.testnetwork.Person;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Person{
    @ColumnInfo(name = "_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    public String login;
    public String password;
    public int age;
    public int salary;
    public String friendsRequestIDs;
    public String friendsIDs;

}
