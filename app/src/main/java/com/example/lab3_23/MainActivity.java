package com.example.lab3_23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    EditText editMSSV, editHoTen, editLop;
    Button btnInsert, btnDelete, btnUpdate, btnQuery;

    List<SinhVien> listSV;
    SinhVienAdapter adapter;

    SQLiteDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMSSV = findViewById(R.id.editMSSV);
        editHoTen = findViewById(R.id.editHoTen);
        editLop = findViewById(R.id.editLop);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);

        RecyclerView recyclerSV = findViewById(R.id.recycleSV);

        listSV = new ArrayList<>();
        adapter = new SinhVienAdapter(listSV, this);

        recyclerSV.setAdapter(adapter);
        recyclerSV.setLayoutManager(new LinearLayoutManager(this));

        myDatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);

        try{
            String sql = "CREATE TABLE SINHVIEN(mssv TEXT primary key, hoten TEXT, lop TEXT)";
            myDatabase.execSQL(sql);
        }catch (Exception e){
            Log.e("Error", "Table đã tồn tại");

        }




        Cursor cursor = myDatabase.rawQuery("SELECT * FROM SINHVIEN", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int colunm1 = cursor.getColumnIndex("mssv");
                String  mssv = cursor.getString(colunm1);

                int colunm2 = cursor.getColumnIndex("hoten");
                String  hoten = cursor.getString(colunm2);

                int colunm3 = cursor.getColumnIndex("lop");
                String  lop = cursor.getString(colunm3);


                SinhVien student = new SinhVien(mssv, hoten, lop);

                listSV.add(student);
                adapter.notifyDataSetChanged();

                Log.d("MSSV", student.getMssv());

            } while (cursor.moveToNext());
        }
        cursor.close();



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = editMSSV.getText().toString();
                String hoten = editHoTen.getText().toString();
                String lop = editLop.getText().toString();

                ContentValues value = new ContentValues();
                value.put("mssv", mssv);
                value.put("hoten", hoten);
                value.put("lop", lop);

                String message = "";

                if (myDatabase.insert("SINHVIEN", null, value) == -1){
                    message = "Fail to Insert";
                }else {
                    message = "Insert successfully";


                }

                SinhVien student = new SinhVien(mssv, hoten, lop);
                adapter.addStudent(student);


                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }

        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = editMSSV.getText().toString();
                int n = myDatabase.delete("SINHVIEN", "mssv = ?", new String[]{mssv});

                String message = "";

                if(n== 0){
                    message = "Fail to Delete";
                }else  {
                    message = "Delete successfully";
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = editMSSV.getText().toString();
                String hoten = editHoTen.getText().toString();
                String lop = editLop.getText().toString();

                ContentValues value = new ContentValues();
                value.put("hoten", hoten);
                value.put("lop", lop);

                int n = myDatabase.update("SINHVIEN", value, "mssv = ?", new String[]{mssv});

                String message = "";

                if(n== 0){
                    message = "Fail to Update";
                }else  {
                    message = "Update successfully";
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearStudent();
                Cursor cursor = myDatabase.rawQuery("SELECT * FROM SINHVIEN", null);
                if (cursor.moveToFirst()) {
                    do {
                        int colunm1 = cursor.getColumnIndex("mssv");
                        String  mssv = cursor.getString(colunm1);

                        int colunm2 = cursor.getColumnIndex("hoten");
                        String  hoten = cursor.getString(colunm2);

                        int colunm3 = cursor.getColumnIndex("lop");
                        String  lop = cursor.getString(colunm3);


                        SinhVien student = new SinhVien(mssv, hoten, lop);
                        listSV.add(student);
                        adapter.notifyDataSetChanged();

                        Log.d("MSSV", student.getMssv());

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

    @Override
    public void onItemClick(SinhVien student) {



        editMSSV.setText(student.getMssv());
        editHoTen.setText(student.getHoten());
        editLop.setText(student.getLop());
    }
}