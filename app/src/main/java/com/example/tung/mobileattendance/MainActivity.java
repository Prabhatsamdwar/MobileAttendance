package com.example.tung.mobileattendance;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
// openHomeFragment();
        openAddCourseFragment();

    }

    private void openLoginFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        loginFragment = new LoginFragment();

        fragmentTransaction.add(R.id.fragment, loginFragment);
        fragmentTransaction.commit();

    }

    private void openHomeFragment() {

        /*Get all data from DB and pass to HomeFragment*/
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        homeFragment = new HomeFragment();

        fragmentTransaction.add(R.id.fragment, homeFragment);
        fragmentTransaction.commit();

    }

    private void openAddCourseFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        addCourseFragment = new AddCourseFragment();

        fragmentTransaction.add(R.id.fragment, addCourseFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void addDataToDataBase(String title, String className,String section) {
        Course course =  new Course();
        course.setTitle(title);
        course.setClassName(className);
        course.setSection(section);
//        dataBaseHelper.createCourse(course);
        Log.d("MainActivity", "Save Data in DB");
        openHomeFragment();
    }
}

