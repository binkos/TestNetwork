package com.example.testnetwork.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testnetwork.AccountOfPerson;
import com.example.testnetwork.Fragments.Friends.FriendsAdapter;
import com.example.testnetwork.Person.App;
import com.example.testnetwork.Person.Person;
import com.example.testnetwork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentMessage extends Fragment implements FriendsAdapter.onItemClickListener {
    RecyclerView recyclerView;
    List<Person> people;
    Person person;
    int[] ids;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_view_layout,container,false);

    recyclerView = view.findViewById(R.id.recycler_friend_view);
    people = App.getInstance().getDataBase().personDao().getAll();
    people.remove((int)AccountOfPerson.getUserInfo().id-1);

    if (AccountOfPerson.getUserInfo().friendsRequestIDs!=null)
    ids = fromStringToArray(AccountOfPerson.getUserInfo().friendsRequestIDs.toCharArray());

    if (ids!=null) people=removePeopleWhoHasRequest(people,ids);


    FriendsAdapter friendsAdapter = new FriendsAdapter(getContext(),people);
    friendsAdapter.setOnItemClickListener(FragmentMessage.this);
    recyclerView.setAdapter(friendsAdapter);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onItemClick(int position, int type) {
        switch (type){
            case 0:
                Toast.makeText(getContext(),"Button of "+ people.get(position).login+" is clicked",Toast.LENGTH_SHORT).show();
                person = people.get(position);
                people.remove(position);


                if (person.friendsRequestIDs==null){
                    person.friendsRequestIDs = ""+(AccountOfPerson.getUserInfo().id);
                }
                else {
                    person.friendsRequestIDs = AccountOfPerson.getUserInfo().friendsRequestIDs+" "+(AccountOfPerson.getUserInfo().id);
                    App.getInstance().getDataBase().personDao().update(person);
                }

                break;
            case 1:
                Toast.makeText(getContext(),"Profile of "+ people.get(position).login+" is clicked",Toast.LENGTH_SHORT).show();
                break;
            case 2:break;
            default:break;
        }
    }


    int[] fromStringToArray(char[] IDs){
        if (IDs.length!=0) {
            String ID;
            for (int i = 0, b = 0; i < IDs.length; i++) {
                ID = "" + IDs[i];
                if (' ' == IDs[i + 1]) {
                    ids[b] = Integer.parseInt(ID);
                    i += 2;
                    b++;
                    ID = null;
                }
            }
            return ids;
        }
        return null;
    }

    List<Person> removePeopleWhoHasRequest(List<Person> people,int[] ids){
        if (people.size()!=0){
            for (Person person:people)
                for (int i:ids)
                    if ((int)person.id==i)
                        people.remove(i);
        }
        return people;
    }
}
