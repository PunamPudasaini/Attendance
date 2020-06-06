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

import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.Database.DatabaseHelper;
import com.example.myproject.R;

import java.util.ArrayList;

public class ViewRegisteredStudent extends AppCompatActivity {
    ArrayList<RegisteredStudent> registeredStudents;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

   DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_student);

        listView = findViewById(R.id.listview);
        final ArrayList<String> registeredList = new ArrayList<String>();
        registeredStudents = databaseHelper.getAllRegistered();

        for(RegisteredStudent registeredStudent : registeredStudents)
        {
            String users = " FirstName: " + registeredStudent.getRegistered_firstname()+"\nLastname:"+registeredStudent.getRegistered_lastname();

            registeredList.add(users);
            Log.d("users: ", users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_registered_list, R.id.labelF, registeredList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewRegisteredStudent.this);

                alertDialogBuilder.setTitle("Delete Registered Student");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        registeredList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        databaseHelper.deleteRegistered(registeredStudents.get(position).getRegistered_id());
                        registeredStudents=databaseHelper.getAllRegistered();

                        for(RegisteredStudent registeredStudent : registeredStudents)
                        {
                            String users = " FirstName: " + registeredStudent.getRegistered_firstname()+ "\n  Lastname: "+registeredStudent.getRegistered_lastname() + "\n LCID: "+registeredStudent.getRegistered_lcid() +"\n Password: "+registeredStudent.getRegistered_password();
                            registeredList.add(users);
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
