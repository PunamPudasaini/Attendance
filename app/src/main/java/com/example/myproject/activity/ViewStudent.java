package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

public class ViewStudent extends AppCompatActivity {
    Spinner spinnerbranch,spinneryear;
    String userrole,branch,year;
    private String[] branchString = new String[] { "BIT","BCS"};
    private String[] semesterString = new String[] {"1st","2nd","3rd","4th","5th","6th"};

    Button submit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        spinnerbranch = findViewById(R.id.spinnerbranchView);
        spinneryear=findViewById(R.id.spinnersemesterView);
        submit = findViewById(R.id.submitButton);

        databaseHelper = new DatabaseHelper(ViewStudent.this);

       spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
               branch = parent.getItemAtPosition(position).toString();
               // Toast.makeText(AddStudent.this, "Selected Item "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
               System.out.println("branch/department selected = "+parent.getItemAtPosition(position).toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);

       spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
               year = parent.getItemAtPosition(position).toString();
               //Toast.makeText(AddStudent.this, "Selected Item "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
               System.out.println("Semester selected = "+parent.getItemAtPosition(position).toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, semesterString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStudent.this,ViewStudentByBranchYear.class);
                intent.putExtra("branch", branch);
                intent.putExtra("year", year);

                startActivity(intent);
            }
        });
    }
}
