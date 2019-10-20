package com.example.greenflag;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class AccountInformation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    EditText tv_name;
    EditText tv_username;
    EditText tv_birthdate;
    EditText tv_address;
    Spinner tv_country;
    RadioGroup tv_gender;
    Button saveAccount;
    Button saveImage;
    String image = "";
    ImageView cameraImage;

    public static  final int CAMERA_REQUEST = 456;

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
        cameraImage = findViewById(R.id.avatar);
        saveImage = findViewById(R.id.save_image);

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

                if(tv_name.getText().toString().isEmpty()){
                    String tv_name = "";
                    contacts.setName(tv_name);
                }
                else{
                    contacts.setName(tv_name.getText().toString());
                }
                if(tv_username.getText().toString().isEmpty()){
                    String tv_username = "";
                    contacts.setUserName(tv_username);
                }
                else{
                    contacts.setUserName(tv_username.getText().toString());
                }

                if(tv_birthdate.getText().toString().isEmpty()){
                    String tv_birthdate = "";
                    contacts.setBirthDate(tv_birthdate);
                }
                else{
                    contacts.setBirthDate(tv_birthdate.getText().toString());
                }

                if(tv_address.getText().toString().isEmpty()){
                    String tv_address = "";
                    contacts.setAddress(tv_address);
                }
                else{
                    contacts.setAddress(tv_address.getText().toString());
                }

                if(tv_country.getSelectedItem().toString().isEmpty()){
                    String tv_country = "";
                    contacts.setCountry(tv_country);
                }
                else{
                    contacts.setName(tv_name.getText().toString());  // spinner to string
                }

                RadioButton radio = findViewById(tv_gender.getCheckedRadioButtonId()); // radio to string

                if(radio == null){
                    Toast.makeText(AccountInformation.this, "Gender is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    contacts.setGender(radio.getText().toString());  // spinner to string
                }

                if(image.isEmpty()){
                    contacts.setAvatar(R.drawable.ic_android_black_24dp);
                }
                else{
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.id.avatar);
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
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null){
            Bundle cameraData = data.getExtras();
            Bitmap bitmap = (Bitmap) cameraData.get("data");
            cameraImage.setImageBitmap(bitmap);
        }
    }
}