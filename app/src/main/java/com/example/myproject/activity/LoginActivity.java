package com.example.myproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;
import com.example.myproject.context.ApplicationContext;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText lcid,password;
    Spinner spinnerloginas;
    String userrole;
    private String[] userRoleString = new String[] { "Admin", "Student"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i("lifecycle","onCreate");
        login = findViewById(R.id.login);
        lcid = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinnerloginas = findViewById(R.id.spinnerloginas);

        spinnerloginas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                userrole = (String) spinnerloginas.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloginas.setAdapter(adapter_role);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (userrole.equals("Admin")) {

                    String Lcid = lcid.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(Lcid)) {
                        lcid.setError("please enter email");
                      
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("please enter password");

                    }
                    else {
                        if (Lcid.equals("admin") & pass_word.equals("admin")) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed \n please recheck email & password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String Lcid = lcid.getText().toString();
                    String Password = password.getText().toString();

                    if (TextUtils.isEmpty(Lcid)) {
                        lcid.setError("please enter LCID");
                    } else if (TextUtils.isEmpty(Password)) {
                        password.setError("please enter password");
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                    RegisteredStudent facultyBean = databaseHelper.validateRegisteredStudent(Lcid, Password);

                    if (facultyBean != null) {
                        Intent intent = new Intent(LoginActivity.this, StudentHome.class);
                        startActivity(intent);
                        ((ApplicationContext) LoginActivity.this.getApplicationContext()).setRegisteredStudent(facultyBean);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed \n Recheck LCID and password", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle","onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();  Log.i("lifecycle","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lifecycle","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","onDestroy");
    }

    }

