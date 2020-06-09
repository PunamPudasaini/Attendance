package com.example.myproject.context;

import android.app.Application;
import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.AttendanceSession;
import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.DTO.Student;

import java.util.ArrayList;

public class ApplicationContext extends Application {
	private RegisteredStudent registeredStudent;
	private AttendanceSession attendanceSessionBean;
	private ArrayList<Student> studentlist;
	private ArrayList<Attendance> attendancelist;

	public RegisteredStudent getRegisteredStudent() {
		return registeredStudent;
	}

	public void setRegisteredStudent(RegisteredStudent registeredStudent) {
		this.registeredStudent = registeredStudent;
	}

	public AttendanceSession getAttendanceSessionBean() {
		return attendanceSessionBean;
	}
	public void setAttendanceSessionBean(AttendanceSession attendanceSessionBean) {
		this.attendanceSessionBean = attendanceSessionBean;
	}
	public ArrayList<Student> getStudentlist() {
		return studentlist;
	}
	public void setStudentlist(ArrayList<Student> studentlist) {
		this.studentlist = studentlist;
	}
	public ArrayList<Attendance> getAttendancelist() {
		return attendancelist;
	}
	public void setAttendanceBeanList(ArrayList<Attendance> attendancelist) {
		this.attendancelist = attendancelist;
	}
	
	

}
