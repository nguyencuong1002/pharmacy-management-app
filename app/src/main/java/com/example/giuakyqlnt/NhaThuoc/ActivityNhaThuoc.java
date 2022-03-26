package com.example.giuakyqlnt.NhaThuoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giuakyqlnt.MyDatabase;
import com.example.giuakyqlnt.R;

import java.util.ArrayList;

public class ActivityNhaThuoc extends AppCompatActivity {
    public static MyDatabase myDatabase;
    static final String DB_NAME = "qlnhathuoc.sqlite";
    static final String TABLE_NAME = "tbl_NhaThuoc";
    static final String MaNT_FIELD = "MaNT";
    static final String TenNT_FIELD = "TenNT";
    static final String DiaChi_FIELD = "DiaChi";

    NhaThuocAdapter nhaThuocAdapter;
    ListView lvNhaThuoc;
    ArrayList<NhaThuoc> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_thuoc);
        mapping();

        myDatabase = new MyDatabase(ActivityNhaThuoc.this, DB_NAME, null, 1);
        String sql_create_table = "create table if not exists "+TABLE_NAME+" ("+MaNT_FIELD+" varchar(10) primary key , "+TenNT_FIELD+" varchar(50), "+DiaChi_FIELD+" varchar(50))";
        //Tạo bảng
        myDatabase.ExecuteSQL(sql_create_table);
        loadData();
    }

    public void loadData() {
        list = getAll();
        nhaThuocAdapter = new NhaThuocAdapter(ActivityNhaThuoc.this, R.layout.dong_nha_thuoc, list);
        lvNhaThuoc.setAdapter(nhaThuocAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            startActivity(new Intent(ActivityNhaThuoc.this, AddNhaThuocActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Lấy danh sách
    public ArrayList<NhaThuoc> getAll() {
        ArrayList<NhaThuoc> list = new ArrayList<>();
        String sql_select = "select * from " + TABLE_NAME;
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String maNT = c.getString(0);
            String tenNT = c.getString(1);
            String diaChi = c.getString(2);

            NhaThuoc nhaThuoc = new NhaThuoc(maNT, tenNT, diaChi);
            list.add(nhaThuoc);
        }
        return list;
    }

    //Show dialog Xóa Dữ liệu
    public void DialogXoaCV(String tenNT, String maNT) {
        String whereClause = ""+MaNT_FIELD+" = ?";
        String[] whereArgs = {maNT};
        Log.d("AAAD", whereArgs + " ok");
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa nhà thuốc " + tenNT + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDatabase.delete(TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(ActivityNhaThuoc.this, "Đã xóa " + tenNT +" "+ maNT, Toast.LENGTH_SHORT).show();
                loadData();
            }

        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //bookAdapter.notifyDataSetChanged();
        dialogXoa.show();
    }


    private void mapping() {
        lvNhaThuoc = findViewById(R.id.lvNhaThuoc);
    }
}