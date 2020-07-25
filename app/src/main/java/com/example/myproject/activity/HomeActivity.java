package com.example.myproject.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myproject.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    LinearLayout line1,line2,line3,line4,line5,line6,line7,line8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
        setUpToolbar();
        //line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        //line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);

       /* line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity( new Intent(HomeActivity.this,CourseActivity.class));
            }
        });*/
        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity( new Intent(HomeActivity.this,AddStudent.class));
            }
        });
        line3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(HomeActivity.this,ViewStudent.class));
            }
        });
      /*  line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TakeAttendance.class));
            }
        });*/
        line5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity( new Intent(HomeActivity.this,ViewAttendance.class));
            }
        });
        line6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(HomeActivity.this,TotalAttendance.class));
            }
        });
        line7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,RegisterStudent.class));
            }
        });
        line8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ViewRegisteredStudent.class));
            }
        });




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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.courses:
                startActivity(new Intent(HomeActivity.this,CourseActivity.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                break;
            case R.id.home:
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                break;
            case R.id.addSubject:
                startActivity(new Intent(HomeActivity.this,Subject.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSubject()).commit();
                break;
            case R.id.addstudent:
                startActivity(new Intent(HomeActivity.this,AddStudent.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddSubject()).commit();
                break;
            case R.id.viewstudent:
                startActivity(new Intent(HomeActivity.this,ViewStudent.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ViewStudent()).commit();
                break;
           /* case R.id.takeattendance:
                startActivity(new Intent(HomeActivity.this,TakeAttendance.class));
                //startActivity(new Intent(HomeActivity.this,Take_Attendance.class));
                break;*/
            case R.id.viewattendance:
                startActivity(new Intent(HomeActivity.this,ViewStudent.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TotalAttendancePerStudent()).commit();
                break;
            case R.id.attendanceperstudent:
                startActivity(new Intent(HomeActivity.this,TotalAttendance.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TotalAttendancePerStudent()).commit();
                break;
            case R.id.studentregistration:
                startActivity(new Intent(HomeActivity.this,RegisterStudent.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudentRegistration()).commit();
                break;

            case R.id.viewregisteredstudent:
                startActivity(new Intent(HomeActivity.this,ViewRegisteredStudent.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ViewRegisteredStudent()).commit();
                break;
            case R.id.logout:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LogoutFragment()).commit();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "you have successfully logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
