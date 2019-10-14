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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateAccount extends AppCompatActivity {
    EditText reg_email;
    EditText reg_password;
    EditText confirm_password;
    Button create_account;
    TextView error_email;
    TextView error_pw;

    private static final String TAG = "CreateAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        if(getSupportActionBar()!=null){
            setTitle("Create Account");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        
        create_account = findViewById(R.id.reg_account_btn);
        error_email = findViewById(R.id.error_email);
        error_pw = findViewById(R.id.error_pw);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                error_email.setVisibility(View.GONE);
                error_pw.setVisibility(View.GONE);

                reg_email = findViewById(R.id.email);
                reg_password = findViewById(R.id.password);
                confirm_password = findViewById(R.id.repeat_password);

                String emailS = reg_email.getText().toString();
                String pwS = reg_password.getText().toString();
                String cpwS = confirm_password.getText().toString();
                String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";


                if(emailS.isEmpty()){
                    Toast.makeText(CreateAccount.this,
                            "Email cannot be Empty!",
                            Toast.LENGTH_LONG).show();
                    error_email.setText("Email cannot be Blank!");
                    error_email.setVisibility(View.VISIBLE);
                }

                if(pwS.isEmpty()){
                    Toast.makeText(CreateAccount.this,
                            "Password cannot be Empty!",
                            Toast.LENGTH_LONG).show();
                    error_pw.setText("Password cannot be blank!");
                    error_pw.setVisibility(View.VISIBLE);
                    return;
                }

                if (emailS.trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();

                    if(pwS.length() < 8 ){
                        Toast.makeText(getApplicationContext(),"Password must be greater than 8 chars", Toast.LENGTH_SHORT).show();
                        error_pw.setText("Password must be greater than 8 chars");
                        error_pw.setVisibility(View.VISIBLE);
                        return;
                    }

                    if(pwS.equals(cpwS)) {
                        Log.d(TAG, "onCreate://////////////// email " + emailS + " Password " + pwS + " Confirm PW " + cpwS);
                        create_account.setOnClickListener(w->{
                            Intent openNewActivity = new Intent();
                            openNewActivity.setClass(
                                    CreateAccount.this,
                                    AccountInformation.class);
                            startActivityForResult(openNewActivity,
                                    1);
                        });
                    }
                    else{
                        Toast.makeText(CreateAccount.this,
                                    "Passwords do not match!",
                                    Toast.LENGTH_LONG).show();
                            error_pw.setText("Passwords do not match!");
                            error_pw.setVisibility(View.VISIBLE);
                            return;
                    }


//                    if (isValidPassword(reg_password.getText().toString())) {
//                        Toast.makeText(CreateAccount.this, "Valid", Toast.LENGTH_SHORT).show();
//                        if(pwS.equals((cpwS))){
//                            Log.d(TAG, "onCreate://////////////// email " + emailS + " Password " + pwS + " Confirm PW " + cpwS);
//                            create_account.setOnClickListener(w->{
//                                Intent openNewActivity = new Intent();
//                                openNewActivity.setClass(
//                                        CreateAccount.this,
//                                        AccountInformation.class);
//                                startActivityForResult(openNewActivity,
//                                        1);
//                            });
//                        }
//
//                        if (!cpwS.equals(pwS))
//                        {
//                            Toast.makeText(CreateAccount.this,
//                                    "Passwords do not match!",
//                                    Toast.LENGTH_LONG).show();
//                            error_pw.setText("Passwords do not match!");
//                            error_pw.setVisibility(View.VISIBLE);
//                            return;
//                        }
//                    }

//                    if(!isValidPassword(reg_password.getText().toString())){
//                        error_pw.setText("Passwords does not meet requirements!");
//                        error_pw.setVisibility(View.VISIBLE);
//                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    error_email.setText("Invalid email address");
                    error_email.setVisibility(View.VISIBLE);
                }
            }});

    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;

//        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]))";
        final String PASSWORD_PATTERN = "(?=.*[0-9])$";
        pattern = Pattern.compile(PASSWORD_PATTERN);

        return pattern.matcher(password).matches();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
