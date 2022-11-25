package com.example.workin;

import android.widget.EditText;

public class Activity {

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


        // constructor
        public Activity(int day , int month , int year , String company, String workplace , int start_hour , int start_minutes, int end_hour , int end_minutes , String reason, int total_hour, int total_minutes, String project_t) {

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
            this.project_t = project_t;

        }
    }