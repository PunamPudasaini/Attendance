package com.example.myproject.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.DTO.Student;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database name
    static String name = "Attendance";

    //Database verision
    static int version = 1;

    //Database Table Name
    private static final String REGISTERED_STUDENT_TABLE = "registered_student_table";
    private static final String STUDENT_INFO_TABLE = "student_table";
    private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
    private static final String ATTENDANCE_TABLE = "attendance_table";


    //Coloumn Name
    private static final String KEY_REGISTERED_ID = "registered_id";
    private static final String KEY_REGISTERED_FIRSTNAME = "registered_firstname";
    private static final String KEY_REGISTERED_LASTNAME = "registered_lastname";
    private static final String KEY_REGISTERED_ADDRESS = "registered_address";
    private static final String KEY_REGISTERED_LCID = "registered_lcid";
    private static final String KEY_REGISTERED_PASSWORD = "registered_password";


    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
    private static final String KEY_STUDENT_LASTNAME = "student_lastname";
    private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    private static final String KEY_STUDENT_ADDRESS = "student_address";
    private static final String KEY_STUDENT_DEPARTMENT = "student_department";
    private static final String KEY_STUDENT_SEMESTER = "student_semester";


    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryRegistered = "CREATE TABLE " + REGISTERED_STUDENT_TABLE + "(" +
                KEY_REGISTERED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_REGISTERED_FIRSTNAME + "  TEXT, " +
                KEY_REGISTERED_LASTNAME + "  TEXT, " +
                KEY_REGISTERED_ADDRESS + "  TEXT, " +
                KEY_REGISTERED_LCID + "  TEXT, " +
                KEY_REGISTERED_PASSWORD + " TEXT " + ")";
        Log.d("queryRegistered", queryRegistered);

        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT," +
                KEY_STUDENT_SEMESTER + " TEXT " + ")";
        Log.d("queryStudent", queryStudent);
        try {
            db.execSQL(queryRegistered);
            db.execSQL(queryStudent);
           /* db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryRegistered = "CREATE TABLE " + REGISTERED_STUDENT_TABLE + " (" +
                KEY_REGISTERED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_REGISTERED_FIRSTNAME + " TEXT, " +
                KEY_REGISTERED_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_REGISTERED_ADDRESS + " TEXT," +
                KEY_REGISTERED_LCID + " TEXT," +
                KEY_REGISTERED_PASSWORD + " TEXT " + ")";
        Log.d("queryRegistered", queryRegistered);

        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT," +
                KEY_STUDENT_SEMESTER + " TEXT " + ")";
        Log.d("queryStudent", queryStudent);

        try {
            db.execSQL(queryRegistered);
            db.execSQL(queryStudent);
            /*db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }

    }
    //CRUD

    public void RegisteredStudent(RegisteredStudent registeredStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO registered_student_table (registered_firstname,registered_lastname,registered_address,registered_lcid,registered_password) values ('" +
                registeredStudent.getRegistered_firstname() + "', '" +
                registeredStudent.getRegistered_lastname() + "', '" +
                registeredStudent.getRegistered_address() + "', '" +
                registeredStudent.getRegistered_lcid() + "', '" +
                registeredStudent.getRegistered_password() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public RegisteredStudent validateRegisteredStudent(String lcid, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM registered_student_table where registered_lcid='" + lcid + "' and registered_password='" + password + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            RegisteredStudent registeredStudent = new RegisteredStudent();
            registeredStudent.setRegistered_id(Integer.parseInt(cursor.getString(0)));
            registeredStudent.setRegistered_firstname(cursor.getString(1));
            registeredStudent.setRegistered_lastname(cursor.getString(2));
            registeredStudent.setRegistered_address(cursor.getString(3));
            registeredStudent.setRegistered_lcid(cursor.getString(4));
            registeredStudent.setRegistered_password(cursor.getString(5));
            return registeredStudent;
        }
        return null;
    }

    public ArrayList<RegisteredStudent> getAllRegistered() {

        Log.d("in get all", "in get all");
        ArrayList<RegisteredStudent> list = new ArrayList<RegisteredStudent>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM registered_student_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                RegisteredStudent registeredStudent = new RegisteredStudent();
                registeredStudent.setRegistered_id(Integer.parseInt(cursor.getString(0)));
                registeredStudent.setRegistered_firstname(cursor.getString(1));
                registeredStudent.setRegistered_lastname(cursor.getString(2));
                registeredStudent.setRegistered_address(cursor.getString(3));
                registeredStudent.setRegistered_lcid(cursor.getString(4));
                registeredStudent.setRegistered_password(cursor.getString(5));
                list.add(registeredStudent);

            } while (cursor.moveToNext());
        }
        return list;
    }
    public void deleteRegistered(int registered_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM registered_student_table WHERE registered_id="+registered_id ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    //Student CRUD
   public void StudentAdd(Student student) {
       SQLiteDatabase db = this.getWritableDatabase();

       String query = "INSERT INTO student_table (student_firstname,student_lastname,student_mobilenumber,student_address,student_department,student_semester) values ('"+
               student.getStudent_firstname()+"', '"+
               student.getStudent_lastname()+"','"+
               student.getStudent_mobilenumber()+"', '"+
               student.getStudent_address()+"', '"+
               student.getStudent_department()+"', '"+
               student.getStudent_semester()+"')";

       Log.d("query", query);
       db.execSQL(query);
       db.close();
   }


    public ArrayList<Student> getAllStudent()
    {
        ArrayList<Student> list = new ArrayList<Student>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_semester(cursor.getString(6));
                list.add(student);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Student> getAllStudentByBranchYear(String branch,String year)
    {
        ArrayList<Student> list = new ArrayList<Student>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_department='"+branch+"' and student_class='"+year+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_semester(cursor.getString(6));
                list.add(student);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public Student getStudentById(int studentId)
    {
        Student student = new Student();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id="+studentId;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{

                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_semester(cursor.getString(6));

            }while(cursor.moveToNext());
        }
        return student;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM student_table WHERE student_id="+studentId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
}


