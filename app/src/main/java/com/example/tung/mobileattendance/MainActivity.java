package com.example.tung.mobileattendance;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.tung.mobileattendance.fragments.AddCourseFragment;
import com.example.tung.mobileattendance.fragments.AddStudentFragment;
import com.example.tung.mobileattendance.fragments.EnrollStudentFragment;
import com.example.tung.mobileattendance.fragments.HomeFragment;
import com.example.tung.mobileattendance.fragments.LoginFragment;
import com.example.tung.mobileattendance.fragments.SignUpFragment;
import com.example.tung.mobileattendance.fragments.StudentListFragment;
import com.example.tung.mobileattendance.models.Course;
import com.example.tung.mobileattendance.models.CourseList;
import com.example.tung.mobileattendance.models.Login;
import com.example.tung.mobileattendance.models.Student;
import com.example.tung.mobileattendance.models.StudentList;
import com.example.tung.mobileattendance.utils.Utils;

import java.util.List;

import static com.example.tung.mobileattendance.constants.Constant.COURSE_ID;
import static com.example.tung.mobileattendance.constants.Constant.COURSE_NAME;
import static com.example.tung.mobileattendance.constants.Constant.LIST_OF_STUDENTS;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, LoginFragment.OnFragmentInteractionListener, StudentListFragment.OnFragmentInteractionListener, AddStudentFragment.OnFragmentInteractionListener, EnrollStudentFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    LoginFragment loginFragment;
    HomeFragment homeFragment;
    AddCourseFragment addCourseFragment;
    SignUpFragment singUpFragment;
    StudentListFragment studentListFragment;
    EnrollStudentFragment enrollStudentFragment;
    AddStudentFragment addStudentFragment;
    private DataBaseHelper dataBaseHelper;
    private Toolbar toolBar;
    private ProgressDialog progressDialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Mobile Attendance");
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        progressDialog = new ProgressDialog(this);
        context = this;
//        openLoginFragment();
//        openAddCourseFragment();
//        openSignUpFragment();
//          openEnrollStudentFragment();
//        openAddStudentFragment();

        List<Course> courseList = dataBaseHelper.getAllCourse();

        openHomeFragment(courseList);
        // openAddStudentFragment();
        //  openAddStudentScreen();

//        List<Student> studentList = dataBaseHelper.getAllStudent();
//        openStudentListFragment(studentList);

    }


    private void openEnrollStudentFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        enrollStudentFragment = new EnrollStudentFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment, enrollStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    private void openSignUpFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        singUpFragment = new SignUpFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment, singUpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void openLoginFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment, loginFragment);
        fragmentTransaction.commit();

    }

    private void openHomeFragment(List<Course> courseList) {

        /*Get all data from DB and pass to HomeFragment*/

        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        Bundle bundle = new Bundle();
        CourseList listOfCourse = new CourseList();
        listOfCourse.setCourseList(courseList);
        bundle.putSerializable("List_of_Course", listOfCourse);
        homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, homeFragment);
        fragmentTransaction.addToBackStack(null);

//        supportFragmentManager.popBackStack();

        fragmentTransaction.commit();

    }

    private void openAddStudentFragment(int courseId, String title) {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        addStudentFragment = new AddStudentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(COURSE_ID, courseId);
        bundle.putString(COURSE_NAME, title);
        addStudentFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, addStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void openAddCourseFragment() {
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        addCourseFragment = new AddCourseFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment, addCourseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void openAddStudentScreen(int courseId, String title) {
        Log.d("MainActivity", "openAddStudentScreen()");
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        List<Student> studentList = dataBaseHelper.getAllStudent();
        openAddStudentFragment(courseId, title);

    }

    @Override
    public void getAllStudentByDate(int day, int month, int year, int courseId) {
        String currentDate = Utils.getDateTime(year, month, day);
        List<Student> studentList = dataBaseHelper.getStudentByCourse(courseId, currentDate);
        Course course = dataBaseHelper.getCourse(courseId);
        studentListFragment.refresh(studentList);
//        openStudentListFragment(studentList, course);
    }

    @Override
    public void setAttendanceForTheDay(List<Student> studentList) {
        progressDialog.setMessage("Updating Records ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        for (Student student : studentList) {
            dataBaseHelper.updateAttendanceForTheDay(student);
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(context, "Records Updated Successfully !", Toast.LENGTH_SHORT).show();
            }
        }, 700);
    }


    private void openStudentListFragment(List<Student> studentList, Course course) {

        /*Get all data from DB and pass to HomeFragment*/
        android.support.v4.app.FragmentManager supportFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        Bundle bundle = new Bundle();
        StudentList listOfStudent = new StudentList();
        listOfStudent.setStudentList(studentList);
        bundle.putSerializable(LIST_OF_STUDENTS, listOfStudent);
        bundle.putString(COURSE_NAME, course.getTitle());
        bundle.putInt(COURSE_ID, course.getId());
        studentListFragment = new StudentListFragment();
        studentListFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment, studentListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void studentAddDataToDatabase(String studentName, String rollNo, String contact, int courseId) {
        Student student = new Student();
        student.setStudentName(studentName);
        student.setRollNo(rollNo);
        student.setContact(contact);
        student.setCourseId(courseId);
        dataBaseHelper.createStudent(student);
        Log.d("MainActivity", "Save Data in DB");
        List<Student> studentList = dataBaseHelper.getAllStudent();
//        shouldDisplayHomeUp();
        studentListFragment.refresh(studentList);

    }

    @Override
    public void onSuccessOpenHomescreen() {

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        List<Course> courseList = dataBaseHelper.getAllCourse();

        openHomeFragment(courseList);
    }

    @Override
    public void openSignUpScreen() {
        openSignUpFragment();
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
    public void onCourseItemClick(int courseId) {
        Log.d("HomeFragment", "CourseId = " + courseId);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        String currentDate = Utils.getDateTime();
        List<Student> studentList = dataBaseHelper.getStudentByCourse(courseId, currentDate);
        Course course = dataBaseHelper.getCourse(courseId);
        openStudentListFragment(studentList, course);
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

