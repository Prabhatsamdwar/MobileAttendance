package com.example.tung.mobileattendance;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, LoginFragment.OnFragmentInteractionListener,StudentListFragment.OnFragmentInteractionListener, AddStudentFragment.OnFragmentInteractionListener, EnrollStudentFragment.OnFragmentInteractionListener, SignupFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    LoginFragment loginFragment;
    HomeFragment homeFragment;
    AddCourseFragment addCourseFragment;
    SignupFragment singupFragment;
    StudentListFragment studentListFragment;
    EnrollStudentFragment enrollStudentFragment;
    AddStudentFragment addStudentFragment;
    private DataBaseHelper dataBaseHelper;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mobile Attendance");

        //openLoginFragment();
//        openAddCourseFragment();
//        openSignupFragment();
//          openEnrollStudentFragment();
        openAddStudentFragment();
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

//         List<Course> courseList = dataBaseHelper.getAllCourse();

//         openHomeFragment(courseList);
 // openAddStudentFragment();
  //  openAddStudentScreen();

  //      List<Student> studentList = dataBaseHelper.getAllStudent();
//openStudentListFragment(studentList);

    }


    private void openEnrollStudentFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        enrollStudentFragment = new EnrollStudentFragment();

        fragmentTransaction.replace(R.id.fragment, enrollStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void openAddStudentFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        addStudentFragment = new AddStudentFragment();

        fragmentTransaction.replace(R.id.fragment, addStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void openSignupFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        singupFragment = new SignupFragment();

        fragmentTransaction.replace(R.id.fragment, singupFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void openLoginFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        loginFragment = new LoginFragment();

        fragmentTransaction.replace(R.id.fragment, loginFragment);
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
        supportFragmentManager.popBackStack();

        fragmentTransaction.commit();

    }

    private void openAddCourseFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        addCourseFragment = new AddCourseFragment();

        fragmentTransaction.replace(R.id.fragment, addCourseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void openAddStudentScreen() {

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        List<Student> studentList = dataBaseHelper.getAllStudent();

        openStudentListFragment(studentList);

    }


    private void openStudentListFragment(List<Student> studentList) {

        /*Get all data from DB and pass to HomeFragment*/

        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Bundle bundle1 = new Bundle();
        StudentList listOfStudent = new StudentList();
        listOfStudent.setStudentList(studentList);
        bundle1.putSerializable("List_of_Student", listOfStudent);
        studentListFragment = new StudentListFragment();
        studentListFragment.setArguments(bundle1);
        fragmentTransaction.replace(R.id.fragment, studentListFragment);
        supportFragmentManager.popBackStack();

        fragmentTransaction.commit();

    }



    @Override
    public void studentAddDataToDatabase(String studentName, String rollNo, String contact) {
        Student student = new Student();
        student.setStudentName(studentName);
        student.setRollNo(rollNo);
        student.setContact(contact);
        dataBaseHelper.createStudent(student);
        Log.d("MainActivity", "Save Data in DB");
        List<Student> studentList = dataBaseHelper.getAllStudent();
        shouldDisplayHomeUp();
        StudentListFragment.refresh(studentList);

    }

    @Override
    public void onSuccessOpenHomescreen() {

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        List<Course> courseList = dataBaseHelper.getAllCourse();

        openHomeFragment(courseList);
    }

    @Override
    public void openSignUpScreen() {
        openSignupFragment();
    }

    @Override
    public void addNewUserToDataBase(String userName, String email, String password) {
        Login login = new Login();
        login.setUsername(userName);
        login.setEmail(email);
        login.setPassword(password);
        dataBaseHelper.createLogin(login);
        openLoginFragment();

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
        shouldDisplayHomeUp();
        homeFragment.refresh(courseList);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
        shouldDisplayHomeUp();
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    private void shouldDisplayHomeUp() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setTitle("Mobile Attendance");
    }

    @Override
    public boolean onSupportNavigateUp() {
        shouldDisplayHomeUp();
        getSupportFragmentManager().popBackStack();
        return true;
    }
}

