package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

public class AddStudent extends AppCompatActivity {
    EditText firstname,lastname,contact,address;
    Spinner department,Semester;
    Button submitbtn;

   public String branch,semester;
    private String[] branchString = new String[] { "BIT","BCS"};
    private String[] semesterString = new String[] {"1st","2nd","3rd","4th","5th","6th"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstname = findViewById(R.id.editTextFirstName);
        lastname = findViewById(R.id.editTextLastName);
        contact = findViewById(R.id.editTextPhone);
        address = findViewById(R.id.editTextaddr);
        department = findViewById(R.id.spinnerdept);
        Semester = findViewById(R.id.spinnersemester);
        submitbtn = findViewById(R.id.RegisterButton);


        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                Toast.makeText(AddStudent.this, "Selected Item "+branch, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter_branch);



        //spinner 2

       Semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
               Toast.makeText(AddStudent.this, "Selected Item "+semester, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

       ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,semesterString);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       Semester.setAdapter(adapter);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudent();
            }
        });
    }

    public void AddStudent(){
            String FirstName = firstname.getText().toString();
            String LastName = lastname.getText().toString();
            String Contact = contact.getText().toString();
            String Address = address.getText().toString();
            branch = department.getSelectedItem().toString();
            semester = Semester.getSelectedItem().toString();

            if (TextUtils.isEmpty(FirstName)) {
                firstname.setError("please enter firstname");
            }

            else if (TextUtils.isEmpty(LastName)) {
                lastname.setError("please enter lastname");
            }
            else if (TextUtils.isEmpty(Contact)) {
                contact.setError("please enter phoneno");
            }
            else if (TextUtils.isEmpty(Address)) {
                address.setError("enter address");
            }
            else {
                Student student = new Student();
                student.setStudent_firstname(FirstName);
                student.setStudent_lastname(LastName);
                student.setStudent_mobilenumber(Contact);
                student.setStudent_address(Address);
                student.setStudent_department(branch);
                student.setStudent_semester(semester);
                DatabaseHelper databaseHelper = new DatabaseHelper(AddStudent.this);
                databaseHelper.StudentAdd(student);
                startActivity(new Intent(AddStudent.this, HomeActivity.class));
                Toast.makeText(AddStudent.this, "Student Added Successfully" +student, Toast.LENGTH_SHORT).show();

            }
        }
        }
