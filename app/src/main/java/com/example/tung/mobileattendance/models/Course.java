package com.example.tung.mobileattendance.models;

import java.io.Serializable;

/**
 * Created by tung on 10/10/16.
 */
public class Course implements Serializable {
    private int id;
    private String title;
    private String className;
    private String section;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
