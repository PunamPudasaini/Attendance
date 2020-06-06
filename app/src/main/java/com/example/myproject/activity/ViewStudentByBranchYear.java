package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myproject.DTO.Student;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends AppCompatActivity {
    ArrayList<Student> students;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    String branch;
    String year;

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_by_branch_year);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> studentList = new ArrayList<String>();

        branch=getIntent().getExtras().getString("branch");
        year =getIntent().getExtras().getString("year");

        students=databaseHelper.getAllStudentByBranchYear(branch, year);

        for(Student student : students)
        {
            String users = student.getStudent_firstname()+","+student.getStudent_lastname();

            studentList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.viewstudentlist, R.id.label, studentList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        studentList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        databaseHelper.deleteStudent(students.get(position).getStudent_id());
                        students=databaseHelper.getAllStudentByBranchYear(branch, year);

                        for(Student student : students)
                        {
                            String users = " FirstName: " + student.getStudent_firstname()+"  \nLastname:"+student.getStudent_lastname();
                            studentList.add(users);
                            Log.d("users: ", users);

                        }
                    }

                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();

                return false;
            }
        });
    }
}
