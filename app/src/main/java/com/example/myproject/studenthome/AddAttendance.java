package com.example.myproject.studenthome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;
import com.example.myproject.context.ApplicationContext;

import java.util.ArrayList;

public class AddAttendance extends AppCompatActivity {
    ArrayList<Student> studentArrayList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    int sessionId=0;
    String status="P";
    Button attendanceSubmit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

        listView = findViewById(R.id.listview);

        sessionId = getIntent().getExtras().getInt("sessionId");

        final ArrayList<String> studentList = new ArrayList<String>();

        studentArrayList=((ApplicationContext)AddAttendance.this.getApplicationContext()).getStudentlist();

        for(Student student : studentArrayList)
        {
            String users = "FirstName: "  + student.getStudent_firstname()+"\nLastName: "+student.getStudent_lastname();
            studentList.add(users);
            Log.d("users: ", users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentList);
        listView.setAdapter( listAdapter );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                parent.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                //arg0.setBackgroundColor(234567);
                view.setBackgroundColor(334455);
                final Student student = studentArrayList.get(position);
                final Dialog dialog = new Dialog(AddAttendance.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
                dialog.setContentView(R.layout.test_layout);
                // set title and cancelable
                RadioGroup radioGroup;
                RadioButton present;
                RadioButton absent;
                radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                present=(RadioButton)dialog.findViewById(R.id.PresentradioButton);
                absent=(RadioButton)dialog.findViewById(R.id.AbsentradioButton);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.PresentradioButton) {

                            status = "P";
                        } else if(checkedId == R.id.AbsentradioButton) {

                            status = "A";
                        } else {
                        }
                    }
                });
                attendanceSubmit = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
                attendanceSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Attendance attendance = new Attendance();

                        attendance.setAttendance_session_id(sessionId);
                        attendance.setAttendance_student_id(student.getStudent_id());
                        attendance.setAttendance_status(status);

                         databaseHelper = new DatabaseHelper(AddAttendance.this);
                        databaseHelper.addnewAttendance(attendance);
                        Toast.makeText(AddAttendance.this, "Attendance Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddAttendance.this,StudentTakeAttendance.class));

                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });

    }
}
