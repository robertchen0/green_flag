package com.example.greenflag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class CreateAccount extends AppCompatActivity {
    EditText reg_email;
    EditText reg_password;
    EditText confirm_password;
    Button create_account;
    TextView error_email;
    TextView error_pw;
    TextView error_cpw;

    private static final String TAG = "CreateAccount";
    public boolean email = false;
    public boolean pw = false;
    public boolean cpw = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = false;
        pw = false;
        cpw = false;

        if (getSupportActionBar() != null) {
            setTitle("Create Account");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        create_account = findViewById(R.id.reg_account_btn); //button to create account
        error_email = findViewById(R.id.error_email); // error editText for email
        error_pw = findViewById(R.id.error_pw); // error editText for password
        error_cpw = findViewById(R.id.repeat_pw); //error editText for confirm password

        reg_email = findViewById(R.id.email);
        reg_password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.repeat_password);

        reg_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                email = false;
                if (hasFocus) {
                    error_email.setVisibility(View.GONE);
                } else {
                    String emailS = reg_email.getText().toString();
                    if (emailS.isEmpty()) {
                        error_email.setText("Email cannot be Blank!");
                        error_email.setVisibility(View.VISIBLE);
                        error_email.setBackgroundResource(R.drawable.error_border);
                        reg_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        reg_email.setBackgroundResource(android.R.drawable.editbox_background);
                        return;
                    }
                    if (isValid(emailS)) {
                        email = true;
                        reg_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.tick2x,0);
                        reg_email.setBackgroundResource(R.drawable.correct_border);
                    } else {
                        error_email.setText("Email not valid");
                        error_email.setVisibility(View.VISIBLE);
                        error_email.setBackgroundResource(R.drawable.error_border);
                        reg_email.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        reg_email.setBackgroundResource(android.R.drawable.editbox_background);
                    }
                }
            }
        });

        reg_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                pw = false;
                if (hasFocus) {
                    error_pw.setVisibility(View.GONE);
                } else {
                    String pwS = reg_password.getText().toString();
                    if (pwS.isEmpty()) {
                        error_pw.setText("Password cannot be blank!");
                        error_pw.setVisibility(View.VISIBLE);
                        error_pw.setBackgroundResource(R.drawable.error_border);
                        reg_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        reg_password.setBackgroundResource(android.R.drawable.editbox_background);
                        return;
                    }
                    if (pwS.length() < 8) {
                        error_pw.setText("Password must be greater than 8 chars");
                        error_pw.setVisibility(View.VISIBLE);
                        error_pw.setBackgroundResource(R.drawable.error_border);
                        reg_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        reg_password.setBackgroundResource(android.R.drawable.editbox_background);
                        return;
                    }
                    if (isValidPassword(pwS)) {
                        reg_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.tick2x,0);
                        reg_password.setBackgroundResource(R.drawable.correct_border);
                        pw = true;
                    } else {
                        error_pw.setText("Password invalid");
                        error_pw.setVisibility(View.VISIBLE);
                        error_pw.setBackgroundResource(R.drawable.error_border);
                        reg_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        reg_password.setBackgroundResource(android.R.drawable.editbox_background);
                    }
                }
            }
        });

        confirm_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                cpw = false;
                if (hasFocus) {
                    error_cpw.setVisibility(View.GONE);
                } else {
                    String pwS = reg_password.getText().toString();
                    String cpwS = confirm_password.getText().toString();
                    if (!pwS.equals(cpwS) || cpwS.isEmpty()) {
                        error_cpw.setText("Password does not Match!");
                        error_cpw.setVisibility(View.VISIBLE);
                        error_cpw.setBackgroundResource(R.drawable.error_border);
                        confirm_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                        confirm_password.setBackgroundResource(android.R.drawable.editbox_background);
                        return;
                    } else {
                        confirm_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.tick2x,0);
                        confirm_password.setBackgroundResource(R.drawable.correct_border);
                        cpw = true;
                    }
                }
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailS = reg_email.getText().toString();
                String pwS = reg_password.getText().toString();
                String cpwS = confirm_password.getText().toString();

                if (isValid(emailS) && (isValidPassword(pwS)) && pwS.equals(cpwS)){
                    email = true;
                    pw = true;
                    cpw = true;
                        Intent newActivity = new Intent(getApplicationContext(), AccountInformation.class);
                        startActivity(newActivity);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean isValid(String email) // Email validation
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) { // Password validation

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}$";

        Pattern pat = Pattern.compile(PASSWORD_PATTERN);
        if (password == null)
            return false;

        return pat.matcher(password).matches();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
