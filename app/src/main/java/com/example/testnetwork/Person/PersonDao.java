package com.example.testnetwork.Person;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("select * from Person")
    public List<Person> getAll();

    @Query("select * from Person where _id = :id")
    public Person getById(long id);

    @Query("select * from Person where login = :login")
    public Person getByLogin(String login);

    @Insert
    void insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);
}
