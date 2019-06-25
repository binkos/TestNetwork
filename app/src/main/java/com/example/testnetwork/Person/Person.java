package com.example.testnetwork.Person;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int age;
    public int salary;
}
