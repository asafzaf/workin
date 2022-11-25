package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.os.Bundle;

public class ViewShifts extends AppCompatActivity {

    private ArrayList<ShiftModal> shiftModalArrayList;
    private DBHandler dbHandler;
    private ShiftRVAdapter shiftRVAdapter;
    private RecyclerView shiftRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewshifts);


        shiftModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewShifts.this);

        // getting our course array
        // list from db handler class.
        shiftModalArrayList = dbHandler.readShifts();

        // on below line passing our array lost to our adapter class.
        shiftRVAdapter = new ShiftRVAdapter(shiftModalArrayList, ViewShifts.this);
        shiftRV = findViewById(R.id.idRVShifts);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewShifts.this, RecyclerView.VERTICAL, false);
        shiftRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        shiftRV.setAdapter(shiftRVAdapter);
    }
}