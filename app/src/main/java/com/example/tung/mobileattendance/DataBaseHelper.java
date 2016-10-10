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
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "attendance";
    private static final String TABLE = "course";

    //Column Names
    private static final String ID = "id";
    private static final String  TITLE= "title";
    private static final String CLASS = "class";
    private static final String SECTION= "section";

    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + CLASS + " TEXT, " + SECTION + " TEXT)";

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
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }

    public long createCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, course.getTitle());
        contentValues.put(CLASS, course.getClassName());
        contentValues.put(SECTION, course.getSection());
        long id = sqLiteDatabase.insert(TABLE, null, contentValues);
        return id;
    }

    public Course getCourse(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE + " WHERE id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) cursor.moveToFirst();
        Course course = new Course();
        course.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        course.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        course.setClassName(cursor.getString(cursor.getColumnIndex(CLASS)));
        course.setSection(cursor.getString(cursor.getColumnIndex(SECTION)));
        return course;
    }

    /*public int updateCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(, course.getCourse());

        return sqLiteDatabase.update(TABLE, values, ID + " = ?",
                new String[]{String.valueOf(notes.getId())});
    }
*/
    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + TABLE;


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(cursor.getInt((cursor.getColumnIndex(ID))));
                course.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                course.setClassName(cursor.getString(cursor.getColumnIndex(CLASS)));
                course.setSection(cursor.getString(cursor.getColumnIndex(SECTION)));


                // adding to notesArrayList list
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        return courseList;
    }

    /*public void deleteNote(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE, ID + " = ?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
*/}
