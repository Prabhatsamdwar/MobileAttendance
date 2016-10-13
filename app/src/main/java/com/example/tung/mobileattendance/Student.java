package com.example.tung.mobileattendance;

import java.io.Serializable;

/**
 * Created by tung on 10/12/16.
 */

public class Student implements Serializable {
    private int id;
    private String studentName;
    private  String rollNo;
    private String contact;


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
}
