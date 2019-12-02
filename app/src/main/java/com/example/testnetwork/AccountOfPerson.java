package com.example.testnetwork;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnetwork.Fragments.Alarm.AlarmReceiver;
import com.example.testnetwork.Fragments.FragmentGallery;
import com.example.testnetwork.Fragments.FragmentGeolocation;
import com.example.testnetwork.Fragments.FragmentMail;
import com.example.testnetwork.Fragments.FragmentMessage;
import com.example.testnetwork.Fragments.FragmentProfile;
import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.AppDataBase;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.Person.PersonDao;

import java.util.Calendar;


public class AccountOfPerson extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    AppDataBase app;
    PersonDao personDao;
    static Person person;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    SharedPreferences pref = null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accont_of_person);
        drawerLayout = findViewById(R.id.drawer_layout);
        app = App.getInstance().getDataBase();
        personDao = app.personDao();



        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getHeaderView(0).setOnClickListener((v)->{
            getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentProfile()).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        Bundle extras = getIntent().getExtras();
        if (extras!=null){

            person = personDao.getByLogin(extras.getString("login"));
        }else {
            person = personDao.getByLogin(getApplicationContext().getSharedPreferences("USER INFO",MODE_PRIVATE).getString("User Login",null));
        };
        getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentProfile()).commit();
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentMessage()).commit();
                break;
            case R.id.nav_mail:
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentMail()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentProfile()).commit();
                break;
            case R.id.nav_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentGallery()).commit();
                break;
            case R.id.nav_geolocation:
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container,new FragmentGeolocation()).commit();
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            case R.id.nav_exit:
                pref = getApplicationContext().getSharedPreferences("USER INFO",MODE_PRIVATE);
                editor = pref.edit();
                editor.remove("User signed in");
                editor.remove("User Login");
                editor.remove("User Password");
                editor.apply();
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Person getUserInfo(){
        return person;
    }
        @Override
        public void onBackPressed() {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
    }

}
