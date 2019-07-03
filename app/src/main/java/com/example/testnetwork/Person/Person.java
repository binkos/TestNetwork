package com.example.testnetwork.Person;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;


@Entity(primaryKeys = {"_id","login"})
public class Person{
    @ColumnInfo(name = "_id")
    @NonNull
    public long id;
    @NonNull
    public String login;
    public String password;
    public int age;
    public int salary;
}
