package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

public class RegisterStudent extends AppCompatActivity {
    Button register,cancel;
    EditText firstname,lastname,address,lcid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        register = findViewById(R.id.registered);
        cancel = findViewById(R.id.cancel);
        firstname = findViewById(R.id.firstname);
        lastname= findViewById(R.id.lastname);
        address = findViewById(R.id.address);
        lcid = findViewById(R.id.lcid);
        password = findViewById(R.id.password);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = firstname.getText().toString();
                String last_name = lastname.getText().toString();
                String Address = address.getText().toString();
                String Lcid = lcid.getText().toString();
                String passWord = password.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    firstname.setError("please enter firstname");
                }
                else if (TextUtils.isEmpty(last_name)) {
                    lastname.setError("please enter lastname");
                }

                else if (TextUtils.isEmpty(Address)) {
                    address.setError("enter address");
                }
                else if (TextUtils.isEmpty(Lcid)) {
                    lcid.setError("please enter lcid");
                }
                else if (TextUtils.isEmpty(passWord)) {
                    password.setError("enter password");
                }
                else {
                    RegisteredStudent registeredStudent = new RegisteredStudent();
                    registeredStudent.setRegistered_firstname(first_name);
                    registeredStudent.setRegistered_lastname(last_name);
                    registeredStudent.setRegistered_address(Address);
                    registeredStudent.setRegistered_lcid(Lcid);
                    registeredStudent.setRegistered_password(passWord);

                    DatabaseHelper databaseHelper = new DatabaseHelper(RegisterStudent.this);
                    databaseHelper.RegisteredStudent(registeredStudent);
                    Intent intent =new Intent(RegisterStudent.this,ViewRegisteredStudent.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
