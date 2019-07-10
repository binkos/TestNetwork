package com.example.testnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.Person.PersonDao;


public class AccountOfPerson extends AppCompatActivity {
    TextView logInField;
    TextView passwordField;
    Person person;
    AppDataBase app;
    PersonDao personDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accont_of_person);
        logInField = findViewById(R.id.loginOfAccount);
        passwordField = findViewById(R.id.passwordOfAccount);
        app = App.getInstance().getDataBase();
        personDao = app.personDao();

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            String login = extras.getString("login");
            person = personDao.getByLogin(login);

            logInField.setText(login);
            passwordField.setText(person.password);
        };

        }

    }
