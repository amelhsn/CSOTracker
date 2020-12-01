package com.example.csotracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CoursesDataSource {
    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NOM };


    public CoursesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Course createCourse(String Nom, String NomCa1) {
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NOM, Nom);
        values1.put(MySQLiteHelper.COLUMN_NOMCA1, NomCa1);
        long insertId = database.insert(MySQLiteHelper.TABLE_COURSES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COURSES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourse(cursor);
        cursor.close();
        return newCourse;
    }

    public void deleteCourse(Course Course) {
        long id = Course.getId();
        System.out.println("Course deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COURSES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Course> getAllCourses() {
        List<Course> Courses = new ArrayList<Course>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COURSES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course Course = cursorToCourse(cursor);
            Courses.add(Course);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return Courses;
    }

    private Course cursorToCourse(Cursor cursor) {
        Course Course = new Course();
        Course.setId(cursor.getLong(0));
        Course.setCourse(cursor.getString(1));
        return Course;
    }
}
