package com.example.myproject.context;

import android.app.Application;
import com.example.myproject.DTO.Attendance;
import com.example.myproject.DTO.AttendanceSessionBean;
import com.example.myproject.DTO.RegisteredStudent;
import com.example.myproject.DTO.Student;
import com.example.myproject.activity.RegisterStudent;

import java.util.ArrayList;

public class ApplicationContext extends Application {
	private RegisteredStudent facultyBean;
	private AttendanceSessionBean attendanceSessionBean;
	private ArrayList<Student> studentBeanList;
	private ArrayList<Attendance> attendanceBeanList;

	public RegisteredStudent getFacultyBean() {
		return facultyBean;
	}

	public void setFacultyBean(RegisteredStudent facultyBean) {
		this.facultyBean = facultyBean;
	}

	public AttendanceSessionBean getAttendanceSessionBean() {
		return attendanceSessionBean;
	}
	public void setAttendanceSessionBean(AttendanceSessionBean attendanceSessionBean) {
		this.attendanceSessionBean = attendanceSessionBean;
	}
	public ArrayList<Student> getStudentBeanList() {
		return studentBeanList;
	}
	public void setStudentBeanList(ArrayList<Student> studentBeanList) {
		this.studentBeanList = studentBeanList;
	}
	public ArrayList<Attendance> getAttendanceBeanList() {
		return attendanceBeanList;
	}
	public void setAttendanceBeanList(ArrayList<Attendance> attendanceBeanList) {
		this.attendanceBeanList = attendanceBeanList;
	}
	
	

}
