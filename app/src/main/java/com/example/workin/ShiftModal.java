package com.example.workin;

import android.widget.EditText;

public class ShiftModal {

    // variables for our shiftname,
    // description, tracks and duration, id.
    private int date_day;
    private int date_month;
    private int date_year;
    private int start_time_hour;
    private int start_time_minute;
    private int end_time_hour;
    private int end_time_minute;
    private String reason_t;
    private String company_t;
    private String workplace_t;
    private String project_t;
    private int total_time_hour;
    private int total_time_minute;
    private int id;
    private String end_time;
    private String start_time;
    private String total_time;
    private String full_date;

    // creating getter and setter methods
    public int getDateDay() {
        return date_day;
    }

    public void setDateDay(int date_day) {
        this.date_day = date_day;
    }

    public int getDateMonth() {
        return date_month;
    }

    public void setDateMonth(int date_month) {
        this.date_month = date_month;
    }

    public int getDateYear() {
        return date_year;
    }

    public void setDateYear(int date_year) {
        this.date_year = date_year;
    }

    public int getStartTime_H() {
        return start_time_hour;
    }

    public void setStartTime_H(int start_time_hour) {
        this.start_time_hour = start_time_hour;
    }

    public int getStartTime_M() {
        return start_time_minute;
    }

    public void setStartTime_M(int start_time_minute) { this.start_time_minute = start_time_minute; }

    public int getEndTime_H() {
        return end_time_hour;
    }

    public void setEndTime_H(int end_time_hour) {
        this.end_time_hour = end_time_hour;
    }

    public int getEndTime_M() {
        return end_time_minute;
    }

    public void setEndTime_M(int end_time_minute) {
        this.end_time_minute = end_time_minute;
    }

    public String getReason() {
        return reason_t;
    }

    public void setReason(String reason_t) {
        this.reason_t = reason_t;
    }

    public String getCompany() {return company_t; }

    public void setCompany(String company_t) {
        this.company_t = company_t;
    }

    public String getWorkplace() {
        return workplace_t;
    }

    public void setWorkplace(String workplace_t) {
        this.workplace_t = workplace_t;
    }

    public int getTotalTime_H() {
        return total_time_hour;
    }

    public void setTotalTime_H(int total_time_hour) {
        this.total_time_hour = total_time_hour;
    }

    public int getTotalTime_M() {
        return total_time_minute;
    }

    public void setTotalTime_M(int total_time_minute) {this.total_time_minute = total_time_minute; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_time() { return start_time; }

    public void setStart_time(String start_time) {this.start_time = start_time; }

    public String getEnd_time() { return  end_time; }

    public void  setEnd_time(String end_time) {this.end_time = end_time; }

    public String getTotal_time() { return total_time; }

    public void setTotal_time(String total_time) { this.total_time = total_time; }

    public String getFull_date() { return full_date; }

    public void setFull_date(String full_date) { this.full_date = full_date; }

    public String getProject_t() { return project_t; }

    public void setProject_t(String project_t) { this.project_t = project_t; }

    // constructor
    public ShiftModal(int id, int day , int month , int year , String company, String workplace , int start_hour , int start_minutes, int end_hour , int end_minutes , String reason, int total_hour, int total_minutes, String start_time, String end_time, String total_time, String full_date, String project_t) {
        this.id = id;
        this.date_day = day;
        this.date_month = month;
        this.date_year = year;
        this.company_t = company;
        this.workplace_t = workplace;
        this.start_time_hour = start_hour;
        this.start_time_minute = start_minutes;
        this.end_time_hour = end_hour;
        this.end_time_minute = end_minutes;
        this.total_time_hour = total_hour;
        this.total_time_minute = total_minutes;
        this.reason_t = reason;
        this.start_time = start_time;
        this.end_time = end_time;
        this.total_time = total_time;
        this.full_date = full_date;
        this.project_t = project_t;

    }
}

