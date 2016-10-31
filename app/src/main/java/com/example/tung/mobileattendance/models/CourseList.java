package com.example.tung.mobileattendance.models;

/**
 * Created by tung on 10/10/16.
 */


import java.io.Serializable;
import java.util.List;

/**
 * Created by sam on 3/8/16.
 */
public class CourseList implements Serializable {
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    List<Course> courseList;
}

