package com.example.testnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t_v =(TextView)findViewById(R.id.t_v);
        String TextForTextView="";
        Person person = new Person();
        person.id = 1;


        AppDataBase app = App.getInstance().getDataBase();
        app.personDao().delete(person);

        List<Person> people = app.personDao().getAll();

        for(Person person1:people){TextForTextView+=person1.id+"\n";}

        t_v.setText(TextForTextView);

    }
}
