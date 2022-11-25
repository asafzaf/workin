package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addProjectPage extends AppCompatActivity {
    private EditText project_name;
    private Button ButtonDone, ButtonBack;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_page);

        project_name = findViewById(R.id.ProjectEnterNameView);
        ButtonDone = findViewById(R.id.buttonDone);
        ButtonBack = findViewById(R.id.buttonBack);

        dbHandler = new DBHandler(addProjectPage.this);

        ButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String project = project_name.getText().toString();
                dbHandler.addProject(project);
                Toast.makeText(addProjectPage.this, "project has been added.", Toast.LENGTH_SHORT).show();
                project_name.setText("");
                startActivity(new Intent(addProjectPage.this, MainActivity.class));
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addProjectPage.this, MainActivity.class));
            }
        });
    }
}