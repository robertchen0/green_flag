package com.example.greenflag;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AccountInformation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    EditText tv_name, tv_username, tv_birthdate, tv_address;
    Spinner tv_country;
    RadioGroup tv_gender;
    Button saveAccount;
    String image = "";
    public final static String USERPAR = "OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        tv_name = findViewById(R.id.editName);
        tv_username = findViewById(R.id.editUserName);
        tv_birthdate = findViewById(R.id.editAge);
        tv_country = findViewById(R.id.editCounty); // Spinner for counties
        tv_gender = findViewById(R.id.editGender);
        tv_address = findViewById(R.id.editAddress);
        tv_birthdate = findViewById(R.id.editAge);
        saveAccount = findViewById(R.id.button_save);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.counties_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tv_country.setAdapter(adapter);
        tv_country.setOnItemSelectedListener(this);


        findViewById(R.id.editDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts contacts = new Contacts();
                contacts.setName(tv_name.getText().toString());
                contacts.setUserName(tv_username.getText().toString());
                contacts.setBirthDate(tv_birthdate.getText().toString());
                contacts.setAddress(tv_address.getText().toString());

                contacts.setCountry(tv_country.getSelectedItem().toString()); // spinner to string

                RadioButton radio = findViewById(tv_gender.getCheckedRadioButtonId()); // radio to string
                contacts.setGender(radio.getText().toString());

                if(image.isEmpty()){
                    contacts.setAvatar(R.drawable.ic_android_black_24dp);
                }

                Intent intent = new Intent();
                intent.putExtra(AccountInformation.USERPAR, contacts);
                intent.setClass(AccountInformation.this, Information.class);
                startActivity(intent);
//                Intent newActivity = new Intent(AccountInformation.this, Information.class);
//                    newActivity.putExtra(USERPAR, contacts);
//                    startActivity(newActivity);
                }
        });
    }



    public void showDatePickerDialog(){ // Date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        tv_birthdate.setText(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}