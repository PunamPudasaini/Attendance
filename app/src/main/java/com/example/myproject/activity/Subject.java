package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.DTO.Course;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

public class Subject extends AppCompatActivity {
    EditText subjectcode,subjectname;
    Button addsubject;
    DatabaseHelper databaseHelper = new DatabaseHelper(Subject.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        subjectcode = findViewById(R.id.subjectcode);
        subjectname = findViewById(R.id.subjectname);
        addsubject = findViewById(R.id.addsubject);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubject();
            }
        });
    }
    public void AddSubject(){
        String SubjectCode = subjectcode.getText().toString();
        String SubjectName = subjectname.getText().toString();

        if (TextUtils.isEmpty(SubjectCode)) {
            subjectcode.setError("please enter subject code");
        }

        else if (TextUtils.isEmpty(SubjectName)) {
            subjectname.setError("please enter subject name");
        }
        else {
            com.example.myproject.DTO.Subject subject = new com.example.myproject.DTO.Subject();
            subject.setSubject_code(SubjectCode);
            subject.setSubject_name(SubjectName);

            System.out.println("Subject complete data = "+subject);
            databaseHelper.AddSubject(subject);
            startActivity(new Intent(Subject.this, HomeActivity.class));
            Toast.makeText(Subject.this, "Subject Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
