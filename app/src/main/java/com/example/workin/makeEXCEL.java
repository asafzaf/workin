package com.example.workin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;


public class makeEXCEL extends AppCompatActivity {

    private int month;
    private Button back_button, make_button;
    private Spinner spinner_month;
    private DBHandler dbHandler;
    private Object spinnersActivity;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_excel);

        back_button = findViewById(R.id.back_button);
        make_button = findViewById(R.id.make_button);
        spinner_month = findViewById(R.id.spinnerMONTH);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_month.setAdapter(adapter);

        spinner_month.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) spinnersActivity);



//        make_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/project name/";
//                File file = new File(directory_path);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//
//                SQLiteToEcxel sqLiteToEcxel = new SQLiteToEcxel(getApplicationContext(), DBHandler.CLASS_NAME_KEY);
//                sqLiteToEcxel.exportAllTables("activities.xls", new SQLiteToExcel.ExportListener()){
//
//                @Override
//                public void onStart() {
//
//                }
//
//                @Override
//                public void onCompleted(String filePath) {
//                    Toast.makeText(SheetActivity.this, "Excel Exported", Toast.LENGTH_SHORT).show();;
//                }
//
//                @Override
//                public void onError(Exception e){
//
//                    }
//                }
//
//                startActivity(new Intent(MainActivity4.this , MainActivity.class));
//
//            });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(makeEXCEL.this, MainActivity.class));
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // make Excel file and saving to a path.
                startActivity(new Intent(makeEXCEL.this, MainActivity.class));
            }
        });
    }

    private void ExportData() {

        //CHECK IF YOU HAVE WRITE PERMISSIONS OR RETURN
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(makeEXCEL.this, "Storage permissions not granted", Toast.LENGTH_SHORT).show();
            return;
        }

        //get database object
        DBHandler myDbhelper = new DBHandler(this);
        SQLiteDatabase database = myDbhelper.getWritableDatabase();

        //delete all entries in the second table
        database.delete("Table2",null,null);

        //Create a cursor of the main database with your filters and sort order applied
        Cursor cursor = getActivity().getContentResolver().query(
                uri,
                projections,
                selection,
                args,
                sortOrder);

        //loop through cursor and add entries from first table to second table
        try {
            while (cursor.moveToNext()) {
                final String ColumnOneIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_ONE"));
                final String ColumnTwoIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_TWO"));
                final String ColumnThreeIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_THREE"));

                //add entries from table one into the table two
                ContentValues values = new ContentValues();
                values.put("TABLE2_COLUMN_1", ColumnOneIndex);
                values.put("TABLE2_COLUMN_2", ColumnTwoIndex );
                values.put("TABLE2_COLUMN_3", ColumnThreeIndex);

                database.insert("table2", null, values);
            }
        } finally {
            //close cursor after looping is complete
            cursor.close();
        }

        //create a string for where you want to save the excel file
        final String savePath = Environment.getExternalStorageDirectory() + "/excelfileTemp";
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //create the sqLiteToExcel object
        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(getContext(), "databasefile.db",savePath);

        //use sqLiteToExcel object to create the excel file
        sqLiteToExcel.exportSingleTable("table2","excelfilename.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }
            @Override
            public void onCompleted(String filePath) {
                //now attach the excel file created and be directed to email activity
                Uri newPath = Uri.parse("file://" + savePath + "/" +"excelfilename.xls");

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                Intent emailintent = new Intent(Intent.ACTION_SEND);

                emailintent.setType("application/vnd.ms-excel");
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailintent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                emailintent.putExtra(Intent.EXTRA_STREAM,newPath);

                startActivity(Intent.createChooser(emailintent, "Send Email"));
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error msg: " + e);
                Toast.makeText(makeEXCEL.this, "Failed to Export data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class SQLiteToExcel {

        private static Handler mHandler = new Handler(Looper.getMainLooper());

        /**
         * SQLite数据库
         */
        private SQLiteDatabase database;
        /**
         * 输出的根目录
         */
        private String mExportPath;
        /**
         * Excel操作对象
         */
        private HSSFWorkbook mWorkbook;

        /**
         * 默认输出的根目录
         */
        public final static String DEFAULT_EXPORTPATH = Environment.getExternalStorageDirectory().toString() + File.separator;

        public SQLiteToExcel(Context context, String dbPath) {
            this(context, dbPath, DEFAULT_EXPORTPATH);
        }

        /**
         * 构造方法
         *
         * @param context
         * @param dbPath
         *            数据库的路径
         * @param exportPath
         *            导出数据库的根目录
         */
        public SQLiteToExcel(Context context, String dbPath, String exportPath) {
            mExportPath = exportPath;
            try {
                database = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
            } catch (Exception e) {
                LogHelper.saveExceptionStackInfo(e);
            }
        }
}