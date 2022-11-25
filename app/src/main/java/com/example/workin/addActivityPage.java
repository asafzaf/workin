package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addActivityPage extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {
    private static final int NULL = -1;
    private EditText date_day, date_month, date_year, start_time_hour, start_time_minute, end_time_hour, end_time_minute, reason_t, company_t, workplace_t, project_t;
    private Button done_button, back_button;
    private DBHandler dbHandler;
//    private Spinner spinner_company, spinner_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity_page);

        date_day = findViewById(R.id.input_date_day);

        date_month = findViewById(R.id.input_date_month);

        date_year = findViewById(R.id.input_date_year);

        start_time_hour = findViewById(R.id.input_start_hour);

        start_time_minute = findViewById(R.id.input_start_minute);

        end_time_hour = findViewById(R.id.input_end_hour);

        end_time_minute = findViewById(R.id.input_end_minute);

        reason_t = findViewById(R.id.reason);

        company_t = findViewById(R.id.input_company);

        workplace_t = findViewById(R.id.input_workplace);

        project_t = findViewById(R.id.input_project);

        done_button = findViewById(R.id.done_button);

        back_button = findViewById(R.id.back_button);
//
//        spinner_company = findViewById(R.id.spinner_Company);
//        spinner_project = findViewById(R.id.spinner_Project);
//
//        spinner_company.setOnItemSelectedListener(this);
//
//        loadCompanySpinnerData();
//
//        spinner_project.setOnItemSelectedListener(this);
//
//        loadProjectSpinnerData();

        dbHandler = new DBHandler(addActivityPage.this);


        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int work_date_day = Integer.parseInt(date_day.getText().toString().trim());
                int work_date_month = Integer.parseInt(date_month.getText().toString().trim());
                int work_date_year = Integer.parseInt(date_year.getText().toString().trim());

                int start_hour = Integer.parseInt(start_time_hour.getText().toString().trim());
                int start_minute = Integer.parseInt(start_time_minute.getText().toString().trim());
                int end_hour = Integer.parseInt(end_time_hour.getText().toString().trim());
                int end_minute = Integer.parseInt(end_time_minute.getText().toString().trim());

                int total_minute = (end_minute - start_minute);
                int total_hour = (end_hour - start_hour);

                if (total_minute < 0) {
                    total_minute = total_minute + (60);
                    total_hour--;
                }
                double ext_min = total_minute;
                double total_combined_temp = total_hour + (ext_min / 60);
                double total_combined = Math.round(total_combined_temp * 1000) / 1000.0;

                String company = company_t.getText().toString();
                String workplace = workplace_t.getText().toString();
                String reason = reason_t.getText().toString();
                String project = project_t.getText().toString();

                if ((work_date_day == NULL) || (work_date_month == NULL) || (work_date_year == NULL) || (start_hour == NULL) || (start_minute == NULL) || (end_hour == NULL) || (end_minute == NULL) || (total_minute == NULL) || (total_hour == NULL) || company.isEmpty() || workplace.isEmpty() || reason.isEmpty() || project.isEmpty()) {
                    Toast.makeText(addActivityPage.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addActivityPage.this, MainActivity.class));
                }

                if ((start_hour > 23) || (start_hour < 0) || (start_minute > 59) || (start_minute < 0)
                        || (end_hour > 23) || (end_hour < 0) || (end_minute > 59) || (end_minute < 0)
                        || (work_date_year > 2024) || (work_date_year < 2021) || (work_date_month < 1) || (work_date_month > 12) || (work_date_day < 1) || (work_date_day > 31)) {
                    Toast.makeText(addActivityPage.this, "Please enter real time and date..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addActivityPage.this, MainActivity.class));
                }

                if (total_hour < 0) {
                    Toast.makeText(addActivityPage.this, "The ending time earlier than starting time..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addActivityPage.this, MainActivity.class));
                }

                dbHandler.addNewActivity(company, workplace, work_date_day, work_date_month, work_date_year, start_hour, start_minute, end_hour, end_minute, total_hour, total_minute, total_combined, reason, project);

                Toast.makeText(addActivityPage.this, "activity has been added.", Toast.LENGTH_SHORT).show();
                date_day.setText("");
                date_month.setText("");
                date_year.setText("");
                start_time_hour.setText("");
                start_time_minute.setText("");
                end_time_hour.setText("");
                end_time_minute.setText("");
                reason_t.setText("");
                company_t.setText("");
                workplace_t.setText("");
                project_t.setText("");

                startActivity(new Intent(addActivityPage.this, MainActivity3.class));
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addActivityPage.this, MainActivity.class));
            }
        });

    }


//    //spinners methods//
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//        String Comp = parent.getItemAtPosition(i).toString();
//        Toast.makeText(parent.getContext(), "You selected: " + Comp,
//                Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//        public void onNothingSelected(AdapterView<?> arg0) {
//
//        }
//
//
//    private void loadCompanySpinnerData() {
//        DBHandler dbx = new DBHandler(getApplicationContext());
//        ArrayList<CompanyModal> labels1 = dbx.readCompanies();
//
//        ArrayAdapter<CompanyModal> dataAdapter = new ArrayAdapter<CompanyModal>(this, android.R.layout.simple_spinner_item,labels1);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_company.setAdapter(dataAdapter);
//    }
//
//    private void loadProjectSpinnerData() {
//        DBHandler dbz = new DBHandler(getApplicationContext());
//        ArrayList<ProjectModal> labels2 = dbz.readProjectsToSpinner();
//
//        ArrayAdapter<ProjectModal> dataAdapter = new ArrayAdapter<ProjectModal>(this, android.R.layout.simple_spinner_item,labels2);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_project.setAdapter(dataAdapter);
//    }
}

