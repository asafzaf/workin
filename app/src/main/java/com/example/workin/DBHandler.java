package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.PrimitiveIterator;


public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "activitydb";

    private static final int DB_VERSION = 1;
    public static final String CLASS_NAME_KEY = "CLASS_NAME";


    private static final String TABLE_1 = "myActivities";
    private static final String TABLE_2 = "myCompanies";
    private static final String TABLE_3 = "myProjects";

    private static final String ID_COL = "id";
    private static final String ID_COMPANY = "id_company";
    private static final String ID_PROJECT = "id_project";

    private static final String DAY_COL = "day";
    private static final String MONTH_COL = "month";
    private static final String YEAR_COL = "year";

    private static final String COMPANY_COL = "company";

    private static final String WORKPLACE_COL = "workplace";

    private static final String PROJECT_COL = "project";

    private static final String START_TIME_HOUR_COL = "start_time_hour";
    private static final String START_TIME_MINUTE_COL = "start_time_minute";
    private static final String END_TIME_HOUR_COL = "ending_time_hour";
    private static final String END_TIME_MINUTE_COL = "ending_time_minute";


    private static final String DESCRIPTION_COL = "description";

    private static final String TOTAL_TIME_HOUR_COL = "total_time_hour";
    private static final String TOTAL_TIME_MINUTE_COL = "total_time_minute";
    private static final String TOTAL_TIME_COMBINED_COL = "total_time_combined";

    private static final String FULL_START_TIME_COL = "start_time";
    private static final String FULL_END_TIME_COL = "end_time";
    private static final String FULL_TOTAL_TIME_COL = "total_time";
    private static final String FULL_DATE_COL = "full_date";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String table1 = "CREATE TABLE " + TABLE_1 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DAY_COL + " INTEGER,"
                + MONTH_COL + " INTEGER,"
                + YEAR_COL + " INTEGER,"
                + COMPANY_COL + " TEXT,"
                + ID_COMPANY + " INTEGER,"
                + WORKPLACE_COL + " TEXT,"
                + PROJECT_COL + " TEXT,"
                + ID_PROJECT + " INTEGER,"
                + START_TIME_HOUR_COL + " INTEGER,"
                + START_TIME_MINUTE_COL + " INTEGER,"
                + END_TIME_HOUR_COL + " INTEGER,"
                + END_TIME_MINUTE_COL + " INTEGER,"
                + DESCRIPTION_COL + " TEXT,"
                + TOTAL_TIME_HOUR_COL + " INTEGER,"
                + TOTAL_TIME_MINUTE_COL + " INTEGER,"
                + TOTAL_TIME_COMBINED_COL + " DOUBLE,"
                + FULL_START_TIME_COL + " TEXT,"
                + FULL_END_TIME_COL + " TEXT,"
                + FULL_TOTAL_TIME_COL + " TEXT,"
                + FULL_DATE_COL + " TEXT)";

        String table2 = "CREATE TABLE " + TABLE_2 + " ("
                + ID_COMPANY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COMPANY_COL + " TEXT)";

        String table3 = "CREATE TABLE " + TABLE_3 + " ("
                + ID_PROJECT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PROJECT_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);

    }

    // this method is use to add new course to our sqlite database.
    public void addNewActivity(String company, String workplace, int day, int month, int year, int start_hour, int start_minutes, int end_hour, int end_minutes, int total_hour, int total_minutes, double total_combined, String reason, String project) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        String nikud = (":"), slash = ("/");

        String full_start_time = (String.valueOf(start_hour) + nikud + (start_minutes < 10 ? ("0" + String.valueOf(start_minutes)) : (String.valueOf(start_minutes))));
        String full_end_time = (String.valueOf(end_hour) + nikud + (end_minutes < 10 ? ("0" + String.valueOf(end_minutes)) : (String.valueOf(end_minutes))));
        String full_total_time = (String.valueOf(total_hour) + nikud + (total_minutes < 10 ? ("0" + String.valueOf(total_minutes)) : (String.valueOf(total_minutes))));
        String full_date = ((day < 10 ? ("0" + String.valueOf(day)) : (String.valueOf(day))) + slash + (month < 10 ? ("0" + String.valueOf(month)) : (String.valueOf(month))) + slash + String.valueOf(year));


        values.put(DAY_COL, day);
        values.put(MONTH_COL, month);
        values.put(YEAR_COL, year);
        values.put(COMPANY_COL, company);
        values.put(WORKPLACE_COL, workplace);
        values.put(START_TIME_HOUR_COL, start_hour);
        values.put(START_TIME_MINUTE_COL, start_minutes);
        values.put(END_TIME_HOUR_COL, end_hour);
        values.put(END_TIME_MINUTE_COL, end_minutes);
        values.put(DESCRIPTION_COL, reason);
        values.put(TOTAL_TIME_HOUR_COL, total_hour);
        values.put(TOTAL_TIME_MINUTE_COL, total_minutes);
        values.put(TOTAL_TIME_COMBINED_COL, total_combined);
        values.put(FULL_START_TIME_COL, full_start_time);
        values.put(FULL_END_TIME_COL, full_end_time);
        values.put(FULL_TOTAL_TIME_COL, full_total_time);
        values.put(FULL_DATE_COL, full_date);
        values.put(PROJECT_COL, project);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_1, null, values);

        // at last we are closing our
        // database after adding database.

        db.close();

    }

    public void addCompany(String company) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COMPANY_COL, company);

        db.insert(TABLE_2, null, values);

        db.close();
    }

    public void addProject(String project) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PROJECT_COL, project);

        db.insert(TABLE_3, null, values);

        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<ShiftModal> readShifts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor shiftCourses = db.rawQuery("SELECT * FROM " + TABLE_1, null);

        // on below line we are creating a new array list.
        ArrayList<ShiftModal> shiftModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (shiftCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                shiftModalArrayList.add(new ShiftModal(
                        shiftCourses.getInt(0), // id
                        shiftCourses.getInt(1), //  good - date day
                        shiftCourses.getInt(2), // good - date month
                        shiftCourses.getInt(3), // good - date year
                        shiftCourses.getString(4), // good - company
                        shiftCourses.getString(6), // good - location
                        shiftCourses.getInt(9), // good - start hours
                        shiftCourses.getInt(10), //  good - start mins
                        shiftCourses.getInt(11), // good - end hours
                        shiftCourses.getInt(12), // good  - end mins
                        shiftCourses.getString(13), // good - description
                        shiftCourses.getInt(14), // good - total hour
                        shiftCourses.getInt(15), // good - total min
                        shiftCourses.getString(17), // str - start time
                        shiftCourses.getString(18), // str - end time
                        shiftCourses.getString(19), // str - total time
                        shiftCourses.getString(20), // str - full date
                        shiftCourses.getString(7) // str - project
                ));
            } while (shiftCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        shiftCourses.close();
        return shiftModalArrayList;
    }

    public ArrayList<CompanyModal> readCompanies() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor CompanyCursor = db.rawQuery("SELECT * FROM " + TABLE_2, null);

        ArrayList<CompanyModal> companyModalArrayList = new ArrayList<>();
        if (CompanyCursor.moveToFirst()) {

            companyModalArrayList.add(new CompanyModal(
                    CompanyCursor.getInt(0),
                    CompanyCursor.getString(1)
            ));
        }
        while (CompanyCursor.moveToNext()) ;

        CompanyCursor.close();
        db.close();
        return companyModalArrayList;
    }

    public ArrayList<CompanyModal> readCompaniesToSpinner() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor CompanyCursor = db.rawQuery("SELECT * FROM " + TABLE_2, null);

        ArrayList<CompanyModal> companyModalArrayList = new ArrayList<>();
        if (CompanyCursor.moveToFirst()) {

            companyModalArrayList.add(new CompanyModal(
                    CompanyCursor.getString(1)
            ));
        }
        while (CompanyCursor.moveToNext()) ;

        CompanyCursor.close();
        db.close();
        return companyModalArrayList;
    }

    public ArrayList<ProjectModal> readProjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor ProjectCursor = db.rawQuery("SELECT * FROM " + TABLE_3, null);

        ArrayList<ProjectModal> projectModalArrayList = new ArrayList<>();
        if (ProjectCursor.moveToFirst()) {

            projectModalArrayList.add(new ProjectModal(
                    ProjectCursor.getInt(0),
                    ProjectCursor.getString(1)
            ));
        }
        while (ProjectCursor.moveToNext()) ;

        ProjectCursor.close();
        return projectModalArrayList;
    }

    public ArrayList<ProjectModal> readProjectsToSpinner() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor ProjectCursor = db.rawQuery("SELECT * FROM " + TABLE_3, null);

        ArrayList<ProjectModal> projectModalArrayList = new ArrayList<>();
        if (ProjectCursor.moveToFirst()) {

            projectModalArrayList.add(new ProjectModal(
                    ProjectCursor.getString(1)
            ));
        }
        while (ProjectCursor.moveToNext()) ;

        ProjectCursor.close();
        db.close();
        return projectModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        onCreate(db);
    }
}
