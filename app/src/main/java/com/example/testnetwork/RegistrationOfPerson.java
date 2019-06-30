package com.example.testnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.Person.PersonDao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationOfPerson extends Activity {
    EditText loginRegistration;
    EditText passwordRegistration;
    EditText repeatPasswordReggistration;
    Button registrationButton;
    AppDataBase app;
    PersonDao personDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_of_person);

        loginRegistration = findViewById(R.id.registration_login);
        passwordRegistration = findViewById(R.id.registration_password);
        repeatPasswordReggistration = findViewById(R.id.registration_repeat_password);
        registrationButton = findViewById(R.id.registration_button);

        app = App.getInstance().getDataBase();
        personDao = app.personDao();
    }

    void registratePerson(View view){
        String login = loginRegistration.getText().toString().trim();
        String pas1 = passwordRegistration.getText().toString().trim();
        String pas2 = repeatPasswordReggistration.getText().toString().trim();
        if (!login.equals("")){
            compareLogin()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o->{
                        if (o){
                            if(comparePasswords(pas1,pas2)) {
                                addToDatabase(login,pas1);
                                Intent intent = new Intent(this,AccountOfPerson.class);
                                intent.putExtra("login",login);
                                intent.putExtra("password",pas1);
                                startActivity(intent);
                            }
                        }else loginRegistration.setText("FAIL");
                            },
                            e->{},
                            ()->{},
                            s->{}
                    );
        }else loginRegistration.setText("it is empty");
    }

    boolean comparePasswords(String pas1,String pas2){
        return pas1.equals(pas2);
    }


    public Observable<Boolean> compareLogin(){
        return Observable.create(
                o->{
                    String login = loginRegistration.getText().toString();
                    List<Person> people = personDao.getAll();
                    for (Person person:people){
                        if (!person.login.equals(login)
                        ) {
                            o.onNext(true);
                            o.onComplete();
                            login="";
                            break;
                        }
                    }
                    if (login.equals("")){o.onNext(false);o.onComplete();}
                }
        );
    }

    public void addToDatabase(String login,String password){
        Person person = new Person();
        person.login = login;
        person.password = password;
        personDao.insert(person);
    }



}
