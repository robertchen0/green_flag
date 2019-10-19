package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static com.example.greenflag.AccountInformation.USERPAR;

public class Information extends AppCompatActivity {
    RecyclerView recyclerView;
    static List<Contacts> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        if(data == null){
            data = new ArrayList<>();
        }
        if (data.isEmpty()) {
            createContacts();
        }
        Intent intent = getIntent();
        Contacts contact = intent.getParcelableExtra(USERPAR);
        data.add(contact);

        recyclerView = findViewById(R.id.account_information);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    public void createContacts(){
        Contacts contacts1 = new Contacts();
        Contacts contacts2 = new Contacts();

        contacts1.setName("Robert Chen");
        contacts1.setUserName("username");
        contacts1.setAddress("123 Somewhere");
        contacts1.setBirthDate("123456789");
        contacts1.setCountry("country");
        contacts1.setGender("male");
        contacts1.setAvatar((R.drawable.ic_android_black_24dp));

        contacts2.setName("name");
        contacts2.setUserName("username");
        contacts2.setAddress("456 Somewhere");
        contacts2.setBirthDate("1234534536789");
        contacts2.setCountry("new country");
        contacts2.setGender("female");
        contacts2.setAvatar((R.drawable.ic_android_black_24dp));

        data = new ArrayList<>();
        data.add(contacts1);
        data.add(contacts2);

    }

}
