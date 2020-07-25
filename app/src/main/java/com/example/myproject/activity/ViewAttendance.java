package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.AttendanceSession;
import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;
import com.example.myproject.context.ApplicationContext;
import com.example.myproject.studenthome.StudentViewAttendance;
import com.example.myproject.studenthome.ViewAttendanceByRegisteredActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewAttendance extends AppCompatActivity {
    Spinner spinnerdepartment,spinnersemester,spinnersubject;
    Button submit;

    String branch = "cse";
    String year = "SE";
    String subject = "SC";
    private String[] branchString = new String[] { "BIT","BCS"};
    private String[] semesterString = new String[] {"1st","2nd","3rd","4th","5th","6th"};
    private String[] subjectString = new String[] {"FA","C","C++","database","JAVA","RDBMS","AI","RTS"};
    DatabaseHelper databaseHelper;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        spinnerdepartment = findViewById(R.id.spinnerdepartment);
        spinnersemester = findViewById(R.id.spinnersemester);
        spinnersubject = findViewById(R.id.spinnersubject);
        submit = findViewById(R.id.submitButton);
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

        databaseHelper = new DatabaseHelper(ViewAttendance.this);
        //department spinner
       /* spinnerdepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerdepartment.setAdapter(adapter_branch);

        //semester spinner
        spinnersemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnersemester.setAdapter(adapter_year);

        //subject spinner
        spinnersubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                subject = parent.getItemAtPosition(position).toString();
                //Toast.makeText(AddStudent.this, "Selected Item "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                System.out.println("Semester selected = "+parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subjectString);
        adapter_subject
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersubject.setAdapter(adapter_subject);

        final EditText dateedittext = findViewById(R.id.dateedittext);
        final Button datebutton = findViewById(R.id.datebtn);
        Calendar calendar = Calendar.getInstance();
        final int dyear = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ViewAttendance.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, dyear,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int dyear, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" +month+ "/" +dyear;
                dateedittext.setText(date);

            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttendanceSession attendanceSession = new AttendanceSession();
                RegisteredStudent registeredStudent=((ApplicationContext)ViewAttendance.this.getApplicationContext()).getRegisteredStudent();

                attendanceSession.setAttendance_session_registered_id(registeredStudent.getRegistered_id());
                attendanceSession.setAttendance_session_department(branch);
                attendanceSession.setAttendance_session_semester(year);
                attendanceSession.setAttendance_session_date(dateedittext.getText().toString());
                attendanceSession.setAttendance_session_subject(subject);

                DatabaseHelper databaseHelper = new DatabaseHelper(ViewAttendance.this);

                ArrayList<Attendance> attendanceBeanList = databaseHelper.getAttendancebysessionid(attendanceSession);
                ((ApplicationContext)ViewAttendance.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

                Intent intent = new Intent(ViewAttendance.this,ViewAttendanceByRegistered.class);
                startActivity(intent);

            }
        });*/
    }
}
