package com.example.myproject.DTO;

public class AttendanceSession {
    private int attendance_session_id;
    private int attendance_session_registered_id;
    private String attendance_session_department;
    private String attendance_session_semester;
    private String attendance_session_date;
    private String attendance_session_subject;

    public int getAttendance_session_id() {
        return attendance_session_id;
    }

    public void setAttendance_session_id(int attendance_session_id) {
        this.attendance_session_id = attendance_session_id;
    }

    public int getAttendance_session_registered_id() {
        return attendance_session_registered_id;
    }

    public void setAttendance_session_registered_id(int attendance_session_registered_id) {
        this.attendance_session_registered_id = attendance_session_registered_id;
    }

    public String getAttendance_session_department() {
        return attendance_session_department;
    }

    public void setAttendance_session_department(String attendance_session_department) {
        this.attendance_session_department = attendance_session_department;
    }

    public String getAttendance_session_semester() {
        return attendance_session_semester;
    }

    public void setAttendance_session_semester(String attendance_session_semester) {
        this.attendance_session_semester = attendance_session_semester;
    }

    public String getAttendance_session_date() {
        return attendance_session_date;
    }

    public void setAttendance_session_date(String attendance_session_date) {
        this.attendance_session_date = attendance_session_date;
    }

    public String getAttendance_session_subject() {
        return attendance_session_subject;
    }

    public void setAttendance_session_subject(String attendance_session_subject) {
        this.attendance_session_subject = attendance_session_subject;
    }
}
