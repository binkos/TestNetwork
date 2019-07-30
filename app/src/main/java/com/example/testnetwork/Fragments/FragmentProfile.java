package com.example.testnetwork.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testnetwork.AccountOfPerson;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.R;
public class FragmentProfile extends Fragment {
    Person person;
    TextView loginField;
    TextView passField;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        person = AccountOfPerson.getUserInfo();

        return inflater.inflate(R.layout.profile_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        loginField = getView().findViewById(R.id.loginOfAccount);
        passField = getView().findViewById(R.id.passwordOfAccount);
        loginField.setText(person.login);
    }
}
