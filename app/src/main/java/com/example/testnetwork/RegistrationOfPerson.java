package com.example.testnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.Person.PersonDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegistrationOfPerson extends AppCompatActivity {
    EditText loginRegistration;
    EditText passwordRegistration;
    EditText repeatPasswordRegistration;
    Button registrationButton;
    Person person;
    PersonDao personDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_of_person);

        loginRegistration = findViewById(R.id.registration_login);
        passwordRegistration = findViewById(R.id.registration_password);
        repeatPasswordRegistration = findViewById(R.id.registration_repeat_password);
        registrationButton = findViewById(R.id.registration_button);


        personDao = App.getInstance().getDataBase().personDao();
    }

    void registratePerson(View view){
        Log.d("LOG","Hello from Reg");
        String login = loginRegistration.getText().toString().trim();
        String pas1 = passwordRegistration.getText().toString().trim();
        String pas2 = repeatPasswordRegistration.getText().toString().trim();
        if (!login.equals("")&&!pas1.equals("")){
            Log.d("LOG","Hello from if");
            compareLogin().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o->{
                        if (o){
                            if(comparePasswords(pas1,pas2)) {
                                addToDatabase(login,pas1);
                                startActivity(createPersonAndIntent(login));
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
        Log.d("LOG","Hello from comparePasswords");
        return pas1.equals(pas2);
    }


    public Observable<Boolean> compareLogin(){
        return Observable.create(
                o->{
                    String login = loginRegistration.getText().toString().trim();
                    List<Person> people = personDao.getAll();
                    if (people!=null) {
                        for (Person person : people) {
                            if (person.login.equals(login)
                            ) {
                                o.onNext(false);
                                o.onComplete();
                                login = "  ";
                                break;
                            }
                        }
                    }
                    if (!login.equals("  ")){o.onNext(true);o.onComplete();}
                }
        );
    }

    public void addToDatabase(String login,String password){

        Log.d("LOG","Hello from addToDatabase");
        person = new Person();
        person.login = login;
        person.password = password;
        personDao.insert(person);
    }

    Intent createPersonAndIntent(String login){
        Log.d("LOG","Hello from createPerson");
        Intent intent = new Intent(RegistrationOfPerson.this,AccountOfPerson.class);

        intent.putExtra("login",login);
        return intent;
    }

}
