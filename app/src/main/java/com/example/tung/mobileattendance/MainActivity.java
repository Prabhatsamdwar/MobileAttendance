package com.example.tung.mobileattendance;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import layout.HomeFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    LoginFragment loginFragment;
    HomeFragment homeFragment;
    AddCourseFragment addCourseFragment;
    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        openLoginFragment();
        //openHomeFragment();
//        openAddCourseFragment();

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        /*Course course = new Course();
        course.setTitle("Title");
        course.setClassName("Class Name");
        course.setSection("Section");
        dataBaseHelper.createCourse(course);*/
        List<Course> courseList = dataBaseHelper.getAllCourse();

        openHomeFragment(courseList);
    }

    private void openLoginFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        loginFragment = new LoginFragment();

        fragmentTransaction.add(R.id.fragment, loginFragment);
        fragmentTransaction.commit();

    }

    private void openHomeFragment(List<Course> courseList) {

        /*Get all data from DB and pass to HomeFragment*/

        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        CourseList listOfCourse = new CourseList();
        listOfCourse.setCourseList(courseList);
        bundle.putSerializable("List_of_Course", listOfCourse);
        homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, homeFragment);
        fragmentTransaction.commit();

    }

    private void openAddCourseFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        addCourseFragment = new AddCourseFragment();

        fragmentTransaction.replace(R.id.fragment, addCourseFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void openAddCourseScreen() {
        openAddCourseFragment();
    }

    @Override
    public void addDataToDataBase(String title, String className, String section) {
        Course course = new Course();
        course.setTitle(title);
        course.setClassName(className);
        course.setSection(section);
        dataBaseHelper.createCourse(course);
        Log.d("MainActivity", "Save Data in DB");
        List<Course> courseList = dataBaseHelper.getAllCourse();
        openHomeFragment(courseList);
    }
}

