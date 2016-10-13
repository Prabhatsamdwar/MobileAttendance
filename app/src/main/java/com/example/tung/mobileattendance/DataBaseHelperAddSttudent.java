package com.example.tung.mobileattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by sam on 3/8/16.
 */
class DataBaseHelperAddStudent extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "attendance";
    private static final String TABLE = "student";

    //Column Names
    private static final String ID = "id";
    private static final String  NAME= "name";
    private static final String ROLLNO= "rollno";
    private static final String CONTACT= "contact";

    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + ROLLNO + " TEXT, " + CONTACT + " TEXT)";

    public DataBaseHelperAddStudent(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DataBaseHelperAddStudent(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    public DataBaseHelperAddStudent(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }

    public long createStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getStudentName());
        contentValues.put(ROLLNO, student.getRollNo());
        contentValues.put(CONTACT, student.getContact());
        long id = sqLiteDatabase.insert(TABLE, null, contentValues);
        return id;
    }

   /* public Course getStudent(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE + " WHERE id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) cursor.moveToFirst();
        Student student = new Student();
        student.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        student.setStudentName(cursor.getString(cursor.getColumnIndex(NAME)));
        student.setRollNo(cursor.getString(cursor.getColumnIndex(ROLLNO)));
        student.setContact(cursor.getString(cursor.getColumnIndex(CONTACT)));
        return Student;
    }
*/




    /*public int updateCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(, course.getCourse());

        return sqLiteDatabase.update(TABLE, values, ID + " = ?",
                new String[]{String.valueOf(notes.getId())});
    }
*/
  /*  public List<Student> getAllCourse() {
        List<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + TABLE;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cou course = new Course();
                course.setId(cursor.getInt((cursor.getColumnIndex(ID))));
                course.se(cursor.getString(cursor.getColumnIndex(NAME)));
                course.setClassName(cursor.getString(cursor.getColumnIndex(ROLLNO)));
                course.setSection(cursor.getString(cursor.getColumnIndex(CONTACT)));


                // adding to notesArrayList list
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        return courseList;
    }


*/
    /*public void deleteNote(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE, ID + " = ?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
*/}
