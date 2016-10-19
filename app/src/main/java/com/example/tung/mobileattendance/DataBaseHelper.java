package com.example.tung.mobileattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "attendance";
    private static final String COURSE_TABLE = "course";

    //Column Names
    private static final String COURSE_ID = "id";
    private static final String COURSE_TITLE = "title";
    private static final String COURSE_CLASS = "class";
    private static final String COURSE_SECTION = "section";


    private static final String LOGIN_TABLE = "user_login";

    //Column Names
    private static final String LOGIN_ID = "id";
    private static final String LOGIN_USERNAME = "username";
    private static final String LOGIN_EMAIL = "email";
    private static final String LOGIN_PASSWORD = "password";

    //Column Name
    private static final String STUDENT_TABLE = "student";

    private static final String STUDENT_ID="id";
    private static final String STUDENT_NAME="studentName";
    private static final String ROLL_NO="rollno";
    private static final String CONTACT="contact";


    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " +
            LOGIN_TABLE + " ( " + LOGIN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + LOGIN_USERNAME + " TEXT, " + LOGIN_EMAIL + " TEXT, " + LOGIN_PASSWORD + " TEXT)";

    private static final String CREATE_COURSE_TABLE = "CREATE TABLE " +
            COURSE_TABLE + "( " + COURSE_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + COURSE_TITLE + " TEXT, " + COURSE_CLASS + " TEXT, " + COURSE_SECTION + " TEXT)";

    private static final String CREATE_STUDENT_TABLE="CREATE TABLE" + STUDENT_TABLE + "( " + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " + ROLL_NO + " TEXT, " + CONTACT + " TEXT)";




    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_COURSE_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP COURSE_TABLE IF EXISTS " + COURSE_TABLE);
    }

    public long createCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_TITLE, course.getTitle());
        contentValues.put(COURSE_CLASS, course.getClassName());
        contentValues.put(COURSE_SECTION, course.getSection());
        long id = sqLiteDatabase.insert(COURSE_TABLE, null, contentValues);
        return id;
    }

    public long createStudent(Student student)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STUDENT_NAME,student.getStudentName());
        contentValues.put(ROLL_NO,student.getRollNo());
        contentValues.put(CONTACT, student.getContact());

        long id = sqLiteDatabase.insert(STUDENT_TABLE,null,contentValues);
        return  id;
    }

    public Course getCourse(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + COURSE_TABLE + " WHERE id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) cursor.moveToFirst();
        Course course = new Course();
        course.setId(cursor.getInt(cursor.getColumnIndex(COURSE_ID)));
        course.setTitle(cursor.getString(cursor.getColumnIndex(COURSE_TITLE)));
        course.setClassName(cursor.getString(cursor.getColumnIndex(COURSE_CLASS)));
        course.setSection(cursor.getString(cursor.getColumnIndex(COURSE_SECTION)));
        return course;
    }

    public Student getStudent(long id)
    {
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + COURSE_TABLE + " WHERE id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) cursor.moveToFirst();

        Student student = new Student();
        student.setId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
        student.setStudentName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
        student.setRollNo(cursor.getString(cursor.getColumnIndex(ROLL_NO)));
        student.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));

        return student;

    }

    /*public int updateCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(, course.getCourse());

        return sqLiteDatabase.update(COURSE_TABLE, values, COURSE_ID + " = ?",
                new String[]{String.valueOf(notes.getId())});
    }
*/
    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + COURSE_TABLE;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(cursor.getInt((cursor.getColumnIndex(COURSE_ID))));
                course.setTitle(cursor.getString(cursor.getColumnIndex(COURSE_TITLE)));
                course.setClassName(cursor.getString(cursor.getColumnIndex(COURSE_CLASS)));
                course.setSection(cursor.getString(cursor.getColumnIndex(COURSE_SECTION)));


                // adding to notesArrayList list
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        return courseList;
    }


    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + STUDENT_TABLE;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt((cursor.getColumnIndex(STUDENT_ID))));
                student.setStudentName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                student.setRollNo(cursor.getString(cursor.getColumnIndex(ROLL_NO)));
                student.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));


                // adding to notesArrayList list
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }


    /*public void deleteNote(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(COURSE_TABLE, COURSE_ID + " = ?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
*/

    public long createLogin(Login login) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN_USERNAME, login.getUsername());
        contentValues.put(LOGIN_EMAIL, login.getEmail());
        contentValues.put(LOGIN_PASSWORD, login.getPassword());
        long id = sqLiteDatabase.insert(LOGIN_TABLE, null, contentValues);
        return id;
    }

    public boolean getLogin(String userName, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + LOGIN_TABLE + " WHERE  " + LOGIN_USERNAME + " = '" + userName + "' AND  " + LOGIN_PASSWORD + "  =  '" + password+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

}
