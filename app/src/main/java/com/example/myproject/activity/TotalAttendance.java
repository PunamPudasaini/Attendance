package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;
import com.example.myproject.context.ApplicationContext;

import java.util.ArrayList;

public class TotalAttendance extends AppCompatActivity {
    ArrayList<Attendance> attendanceArrayList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);
        listView = findViewById(R.id.listview);

        DatabaseHelper databaseHelper = new DatabaseHelper(TotalAttendance.this);
        ArrayList<Attendance> attendanceArrayList=databaseHelper.getAllAttendanceByStudent();
        ((ApplicationContext)TotalAttendance.this.getApplicationContext()).setAttendanceBeanList(attendanceArrayList);

        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Present Count Per Student");
        attendanceArrayList=((ApplicationContext)TotalAttendance.this.getApplicationContext()).getAttendancelist();

        for(Attendance attendance : attendanceArrayList)
        {
            String users = "";
            Student student =databaseHelper.getStudentById(attendance.getAttendance_student_id());
            users = attendance.getAttendance_student_id()+".     "+student.getStudent_firstname()+","+student.getStudent_lastname()+"                  "+attendance.getAttendance_session_id();
            attendanceList.add(users);
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendanceList);
        listView.setAdapter( listAdapter );


       /* Intent intent = new Intent(TotalAttendance.this,ViewAttendancePerStudentActivity.class);
        startActivity(intent);*/


    }
}
