package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addCompanyPage extends AppCompatActivity {
    private EditText company_name;
    private Button ButtonDone, ButtonBack;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company_page);

        company_name = findViewById(R.id.CompanyEnterNameView);
        ButtonDone = findViewById(R.id.buttonDone);
        ButtonBack = findViewById(R.id.buttonBack);

        dbHandler = new DBHandler(addCompanyPage.this);

        ButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company = company_name.getText().toString();
                dbHandler.addCompany(company);
                Toast.makeText(addCompanyPage.this, "company has been added.", Toast.LENGTH_SHORT).show();
                company_name.setText("");
                startActivity(new Intent(addCompanyPage.this, MainActivity.class));
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addCompanyPage.this, MainActivity.class));
            }
        });
    }
}