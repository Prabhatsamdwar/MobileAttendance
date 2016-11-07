package com.example.tung.mobileattendance.models;

import java.io.Serializable;

/**
 * Created by tung on 10/12/16.
 */

public class Student implements Serializable {
    private int id;
    private String studentName;
    private String rollNo;
    private String contact;
    private int courseId;
    private boolean isOnLeave;
    private boolean isPresent;
    private boolean isAbsent;
    private String date;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public boolean isOnLeave() {
        return isOnLeave;
    }

    public void setOnLeave(boolean onLeave) {
        isOnLeave = onLeave;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isAbsent() {
        return isAbsent;
    }

    public void setAbsent(boolean absent) {
        isAbsent = absent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
