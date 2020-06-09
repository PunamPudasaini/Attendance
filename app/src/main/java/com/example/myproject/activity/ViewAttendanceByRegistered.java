package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;
import com.example.myproject.context.ApplicationContext;
import com.example.myproject.studenthome.ViewAttendanceByRegisteredActivity;

import java.util.ArrayList;

public class ViewAttendanceByRegistered extends AppCompatActivity {
    ArrayList<Attendance> attendanceArrayList;
    ArrayAdapter<String> adapter;
    DatabaseHelper databaseHelper = new DatabaseHelper(ViewAttendanceByRegistered.this);
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_by_registered2);
        listView = findViewById(R.id.listview);
        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Id   | StudentName   |  Status ");

        attendanceArrayList = ((ApplicationContext) ViewAttendanceByRegistered.this.getApplicationContext()).getAttendancelist();

        for(Attendance attendance : attendanceArrayList)
        {
            String users = "";
            if(attendance.getAttendance_session_id() != 0)
            {
                DatabaseHelper databaseHelper = new DatabaseHelper(ViewAttendanceByRegistered.this);
                Student student =databaseHelper.getStudentById(attendance.getAttendance_student_id());
                users = attendance.getAttendance_student_id()+".     "+student.getStudent_firstname()+"  "+student.getStudent_lastname()+"                  "+attendance.getAttendance_status();
            }
            else
            {
                users = attendance.getAttendance_status();
            }

            attendanceList.add(users);
            Log.d("users: ", users);

        }

        adapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list2, R.id.labelAttendance, attendanceList);
        listView.setAdapter( adapter );

    }
}
