package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enter = new Button(this);
        enter = findViewById(R.id.b1);

        Button view = new Button(this);
        view = findViewById(R.id.b2);

        Button make_excel = new Button(this);
        make_excel = findViewById(R.id.b3);

        Button addCompany = new Button(this);
        addCompany = findViewById(R.id.addCompanyButton);

        Button addProject = new Button(this);
        addProject = findViewById(R.id.addProjectButton);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , addActivityPage.class));
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , ViewShifts.class));
            }
        });

        make_excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, makeEXCEL.class));
            }
        });

        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addCompanyPage.class));
            }
        });

        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addProjectPage.class));
            }
        });
    }
}