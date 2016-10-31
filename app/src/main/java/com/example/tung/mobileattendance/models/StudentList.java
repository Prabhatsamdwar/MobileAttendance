package com.example.tung.mobileattendance.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tung on 10/20/16.
 */

public class StudentList implements Serializable {
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    List<Student> studentList;

}
