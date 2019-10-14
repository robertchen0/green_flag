package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        createAccount = findViewById(R.id.create_account_btn);

        createAccount.setOnClickListener(v->{
            Intent openNewActivity = new Intent();
            openNewActivity.setClass(
                    MainActivity.this,
                    CreateAccount.class);
            startActivityForResult(openNewActivity,
                    1);
        });

    }
}
