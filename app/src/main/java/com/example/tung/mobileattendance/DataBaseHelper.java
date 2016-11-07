package com.example.tung.mobileattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tung.mobileattendance.models.Course;
import com.example.tung.mobileattendance.models.Login;
import com.example.tung.mobileattendance.models.Student;
import com.example.tung.mobileattendance.utils.Utils;

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
    private static final String PRESENCE_TABLE = "presence";

    private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "studentName";
    private static final String ROLL_NO = "rollno";
    private static final String CONTACT = "contact";
    public static final String IS_PRESENT = "is_present";
    public static final String IS_LEAVE = "is_leave";
    public static final String IS_ABSENT = "is_absent";


    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " +
            LOGIN_TABLE + " ( " + LOGIN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + LOGIN_USERNAME + " TEXT, " + LOGIN_EMAIL + " TEXT, " + LOGIN_PASSWORD + " TEXT)";

    private static final String CREATE_COURSE_TABLE = "CREATE TABLE " +
            COURSE_TABLE + "( " + COURSE_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + COURSE_TITLE + " TEXT, " + COURSE_CLASS + " TEXT, " + COURSE_SECTION + " TEXT)";

    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + STUDENT_TABLE + " ( " + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " + ROLL_NO + " TEXT, " + CONTACT + " TEXT" + ", " +
            "course_id" + " INTEGER)";

    private static final String CREATE_PRESENCE_TABLE = "CREATE TABLE " + PRESENCE_TABLE + " ( " + STUDENT_ID + " INTEGER , " + " date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            IS_ABSENT + " INTEGER DEFAULT 0, " + IS_PRESENT + " INTEGER DEFAULT 0, " + IS_LEAVE + " INTEGER DEFAULT 0, PRIMARY KEY (id, date))";


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
        sqLiteDatabase.execSQL(CREATE_PRESENCE_TABLE);
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

    public long createStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, student.getStudentName());
        contentValues.put(ROLL_NO, student.getRollNo());
        contentValues.put(CONTACT, student.getContact());
        contentValues.put("course_id", student.getCourseId());
        /*contentValues.put("attendance_date", Utils.getDateTime());*/
        long id = sqLiteDatabase.insert(STUDENT_TABLE, null, contentValues);

        ContentValues values = new ContentValues();

        values.put("id", String.valueOf(id));
        values.put(IS_ABSENT, (student.isAbsent() ? 1 : 0));
        values.put(IS_PRESENT, (student.isPresent() ? 1 : 0));
        values.put(IS_LEAVE, (student.isOnLeave() ? 1 : 0));
        values.put("date", Utils.getDateTime());
        sqLiteDatabase.insertWithOnConflict(PRESENCE_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        return id;
    }

    public Course getCourse(long courseId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + COURSE_TABLE + " WHERE id = " + courseId;
        Log.d("DBQuery", selectQuery);
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) cursor.moveToFirst();
        Course course = new Course();
        course.setId(cursor.getInt(cursor.getColumnIndex(COURSE_ID)));
        course.setTitle(cursor.getString(cursor.getColumnIndex(COURSE_TITLE)));
        course.setClassName(cursor.getString(cursor.getColumnIndex(COURSE_CLASS)));
        course.setSection(cursor.getString(cursor.getColumnIndex(COURSE_SECTION)));
        return course;
    }

    public Student getStudent(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
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

    public List<Student> getStudentByCourse(int courseId, String currentDate) {
        List<Student> studentList = new ArrayList<Student>();

        String presenceQuery = "select * from presence p join student s on p.id=s.id and p.date=" + Utils.getDateTime();


        String selectQuery = "select * from presence p join student s on p.id=s.id and s.course_id = " + courseId;
        if (currentDate != null) {
            presenceQuery = "select * from presence p join student s on p.id=s.id and date=" + currentDate;
            selectQuery += " and date = " + currentDate;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Log.d("Query", "presenceQuery: " + presenceQuery);
        Log.d("Query", "selectQuery: " + selectQuery);
        Cursor presenceCursor = sqLiteDatabase.rawQuery(presenceQuery, null);
        if (presenceCursor.getCount() > 0) {
            /*Read from presence table then create student object*/
            Log.d("Query", "Read from presence table then create student object");
            if (presenceCursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setId(presenceCursor.getInt((presenceCursor.getColumnIndex(STUDENT_ID))));
                    student.setStudentName(presenceCursor.getString(presenceCursor.getColumnIndex(STUDENT_NAME)));
                    student.setRollNo(presenceCursor.getString(presenceCursor.getColumnIndex(ROLL_NO)));
                    student.setContact(presenceCursor.getString(presenceCursor.getColumnIndex(CONTACT)));
                    student.setOnLeave(presenceCursor.getInt(presenceCursor.getColumnIndex(IS_LEAVE)) != 0);
                    student.setAbsent(presenceCursor.getInt(presenceCursor.getColumnIndex(IS_ABSENT)) != 0);
                    student.setPresent(presenceCursor.getInt(presenceCursor.getColumnIndex(IS_PRESENT)) != 0);
                    student.setDate(presenceCursor.getString(presenceCursor.getColumnIndex("date")));
                    // adding to notesArrayList list
//                Log.d("test", presenceCursor.getString(presenceCursor.getColumnIndex(IS_PRESENT)));
                    studentList.add(student);
                } while (presenceCursor.moveToNext());
            }
        } else {
            /*Read from student table then create student object*/
            Log.d("Query", "Read from student table then create student object");
            Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
            Log.d("Query", selectQuery);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setId(cursor.getInt((cursor.getColumnIndex(STUDENT_ID))));
                    student.setStudentName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                    student.setRollNo(cursor.getString(cursor.getColumnIndex(ROLL_NO)));
                    student.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
                    studentList.add(student);
                } while (cursor.moveToNext());
            }
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
        String selectQuery = "SELECT * FROM " + LOGIN_TABLE + " WHERE  " + LOGIN_USERNAME + " = '" + userName + "' AND  " + LOGIN_PASSWORD + "  =  '" + password + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }

    public int updateAttendanceForTheDay(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", String.valueOf(student.getId()));
        values.put(IS_ABSENT, (student.isAbsent() ? 1 : 0));
        values.put(IS_PRESENT, (student.isPresent() ? 1 : 0));
        values.put(IS_LEAVE, (student.isOnLeave() ? 1 : 0));
        values.put("date", Utils.getDateTime());
        int id = (int) sqLiteDatabase.insertWithOnConflict(PRESENCE_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
//            sqLiteDatabase.update(PRESENCE_TABLE, values, STUDENT_ID + " = ?", new String[]{String.valueOf(student.getId())});
            sqLiteDatabase.update(PRESENCE_TABLE, values, STUDENT_ID + " = ? " + " and date = ? ", new String[]{String.valueOf(student.getId()), Utils.getDateTime()});
        }

        return id;
    }

    public void deleteCourse(int courseId) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(COURSE_TABLE, COURSE_ID + " = ?",
                new String[]{String.valueOf(courseId)});
        sqLiteDatabase.delete(STUDENT_TABLE, "course_id" + " = ?",
                new String[]{String.valueOf(courseId)});
        sqLiteDatabase.close();
    }
}
