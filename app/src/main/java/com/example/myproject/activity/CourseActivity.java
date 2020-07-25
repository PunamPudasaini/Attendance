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
import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

public class CourseActivity extends AppCompatActivity {
    EditText coursecode,coursename;
    Button addcourse;
    DatabaseHelper databaseHelper = new DatabaseHelper(CourseActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_activity);

        coursecode = findViewById(R.id.coursecode);
        coursename = findViewById(R.id.coursename);
        addcourse = findViewById(R.id.addcourse);
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

        //DatabaseHelper databaseHelper = new DatabaseHelper(CourseActivity.this);

        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCourse();
            }
        });
    }
    public void AddCourse(){
        String CourseCode = coursecode.getText().toString();
        String CourseName = coursename.getText().toString();

        if (TextUtils.isEmpty(CourseCode)) {
            coursecode.setError("please enter coursecode");
        }

        else if (TextUtils.isEmpty(CourseName)) {
            coursename.setError("please enter coursename");
        }
        else {
            Course course = new Course();
            course.setCourse_code(CourseCode);
            course.setCourse_name(CourseName);

            System.out.println("Course complete data = "+course);
            databaseHelper.AddCourse(course);
            startActivity(new Intent(CourseActivity.this, HomeActivity.class));
            Toast.makeText(CourseActivity.this, "Course Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
