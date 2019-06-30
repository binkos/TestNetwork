package com.example.testnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AccountOfPerson extends Activity {
    TextView logInField;
    TextView passwordField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accont_of_person);
        logInField = findViewById(R.id.loginOfAccount);
        passwordField = findViewById(R.id.passwordOfAccount);

        Bundle extras = (Bundle) getIntent().getExtras();
        if(extras!= null){
        logInField.setText(extras.getString("login"));
        passwordField.setText(extras.getString("password"));
        }

    }
}
