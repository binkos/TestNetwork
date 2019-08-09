package com.example.testnetwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.Person.PersonDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    EditText logField;
    EditText pasField;
    TextView registration;
    Button LogIn;
    AppDataBase app;
    PersonDao personDao;
    SharedPreferences settings = null;
    SharedPreferences.Editor editor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logField = findViewById(R.id.logFiled);
        pasField = findViewById(R.id.pasField);
        LogIn =  findViewById(R.id.LogIn);
        app = App.getInstance().getDataBase();
        personDao = app.personDao();
        registration = findViewById(R.id.reg);

        settings = getApplicationContext().getSharedPreferences("USER INFO", Context.MODE_PRIVATE);
        editor = settings.edit();

        if (settings!=null){
            if (settings.getBoolean("User signed in",false)){
                Intent intent = new Intent(this,AccountOfPerson.class);
                intent.putExtra("login",settings.getString("User Login",null));
                intent.putExtra("User Password",settings.getString("User Password",null));
                startActivity(intent);
            }
        }

        registration.setOnClickListener(e->{
            Intent intent = new Intent(this,RegistrationOfPerson.class);
            startActivity(intent);
        });

    }

    public void Login(View view){
        if (!logField.getText().toString().trim().equals("")){
            LoggedIn().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                o->{
                                    if (o[0]!=null){
                                        Intent intent = new Intent(this,AccountOfPerson.class);
                                        intent.putExtra("login",o[0]);
                                        intent.putExtra("password",o[1]);
                                        editor.putBoolean("User signed in",true);
                                        editor.putString("User Login",o[0]);
                                        editor.putString("User Password",o[1]);
                                        editor.apply();
                                        startActivity(intent);
                                    }else {
                                        logField.setText("you didn't log in");
                                        pasField.setText("you didn't log in");
                                    }
                                    },
                                e->{},
                                ()->{},
                                d->{}
                        );
        }else {
            logField.setText("fields are empty");
            pasField.setText("fields are empty");
        }
       }

    public Observable<String[]> LoggedIn(){
        return Observable.create(
                o->{
                    String log = logField.getText().toString();
                    String pas = pasField.getText().toString();
                    List<Person> people = personDao.getAll();
                        for (Person person:people){
                            if (person.login.equals(log)
                                    &&person.password.equals(pas)
                            ) {
                                o.onNext(new String[]{log,pas});
                                o.onComplete();
                                log="";
                                break;
                            }
                        }
                        if (!log.equals("")){
                            o.onNext(new String[]{null,null});
                        }
                }
        );
    }

    @Override
    protected void onRestart() {

        if (settings!=null){
            if (settings.getBoolean("User signed in",false)){
                Intent intent = new Intent(this,AccountOfPerson.class);
                intent.putExtra("login",settings.getString("User Login",null));
                intent.putExtra("User Password",settings.getString("User Password",null));
                startActivity(intent);
            }
        }

        super.onRestart();
    }
}



