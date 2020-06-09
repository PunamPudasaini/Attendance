package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.myproject.DTO.Student;
import com.example.myproject.R;
import com.example.myproject.studenthome.StudentTakeAttendance;
import com.example.myproject.studenthome.StudentTotalAttendance;
import com.example.myproject.studenthome.StudentViewAttendance;
import com.example.myproject.studenthome.StudentViewCourse;
import com.example.myproject.studenthome.Student_ViewStudent;
import com.example.myproject.studenthome.SubjectList;
import com.google.android.material.navigation.NavigationView;

public class StudentHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    LinearLayout line1,line2,line3,line4,line5,line6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
        setUpToolbar();
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
     /*   line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,StudentViewCourse.class));
            }
        });*/
       /* line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,Student_ViewStudent.class));
            }
        });*/
        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,StudentTakeAttendance.class));
            }
        });
        line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,StudentViewAttendance.class));
            }
        });
        line5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this,StudentTotalAttendance.class));
            }
        });
       /* line6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this, SubjectList.class));
            }
        });*/
    }

    private void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
       /* case R.id.courses:
        startActivity(new Intent(StudentHome.this, StudentViewCourse.class));
        break;*/

        case R.id.home:
        startActivity(new Intent(StudentHome.this,StudentHome.class));
        break;
        /*case R.id.viewSubject:
            startActivity(new Intent(StudentHome.this,SubjectList.class));
            break;

        case R.id.viewstudent:
        startActivity(new Intent(StudentHome.this, Student_ViewStudent.class));
        break;*/

        case R.id.takeattendance:
        startActivity(new Intent(StudentHome.this, StudentTakeAttendance.class));

        break;
        case R.id.viewattendance:
        startActivity(new Intent(StudentHome.this, StudentViewAttendance.class));
        break;
        case R.id.attendanceperstudent:
        startActivity(new Intent(StudentHome.this, StudentTotalAttendance.class));
        break;

        case R.id.logout:
        Intent intent = new Intent(StudentHome.this, LoginActivity.class);
        startActivity(intent);
        break;
    }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
