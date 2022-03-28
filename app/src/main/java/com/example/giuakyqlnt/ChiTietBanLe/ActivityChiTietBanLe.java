package com.example.giuakyqlnt.ChiTietBanLe;

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

public class ActivityChiTietBanLe extends AppCompatActivity {
    public static MyDatabase myDatabase;
    static final String DB_NAME = "qlnhathuoc.sqlite";
    static final String TABLE_NAME = "tbl_ChiTietBanLe";
    static final String SOHD_FIELD = "SOHD";
    static final String MATHUOC_FIELD = "MATHUOC";
    static final String SOLUONG_FIELD = "SOLUONG";

    ChiTietBanLeAdapter chiTietBanLeAdapter;
    ListView lvChiTietBanLe;
    ArrayList<ChiTietBanLe> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ban_le);
        mapping();

        myDatabase = new MyDatabase(ActivityChiTietBanLe.this, DB_NAME, null, 1);
        String sql_create_table = "create table if not exists "+TABLE_NAME+" ("+SOHD_FIELD+" varchar(10) , "+MATHUOC_FIELD+" varchar(10), "+SOLUONG_FIELD+" varchar(50),primary key("+SOHD_FIELD+","+MATHUOC_FIELD+"))";
        //Tạo bảng
        myDatabase.ExecuteSQL(sql_create_table);
        loadData();
    }

    public void loadData() {
        list = getAll();
        chiTietBanLeAdapter = new ChiTietBanLeAdapter(ActivityChiTietBanLe.this, R.layout.dong_chi_tiet_ban_le, list);
        lvChiTietBanLe.setAdapter(chiTietBanLeAdapter);
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
            startActivity(new Intent(com.example.giuakyqlnt.ChiTietBanLe.ActivityChiTietBanLe.this, AddChiTietBanLeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Lấy danh sách
    public ArrayList<ChiTietBanLe> getAll() {
        ArrayList<ChiTietBanLe> list = new ArrayList<>();
        String sql_select = "select * from " + TABLE_NAME;
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String SOHD = c.getString(0);
            String MATHUOC = c.getString(1);
            String SOLUONG = c.getString(2);

            ChiTietBanLe chiTietBanLe = new ChiTietBanLe(SOHD, MATHUOC, SOLUONG);
            list.add(chiTietBanLe);
        }
        return list;
    }

    //Show dialog Xóa Dữ liệu
    public void DialogXoaCV(String SOHD, String MATHUOC) {
        String whereClause = ""+SOHD_FIELD+" = ? AND "+MATHUOC_FIELD+" = ?";
        String[] whereArgs = {SOHD,MATHUOC};
        Log.d("AAAD", whereArgs + " ok");
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa chi tiết hóa đơn có số hóa đơn:  " + SOHD + " và mã thuốc: "+ MATHUOC + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDatabase.delete(TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(com.example.giuakyqlnt.ChiTietBanLe.ActivityChiTietBanLe.this, "Đã xóa " + SOHD +" "+ MATHUOC, Toast.LENGTH_SHORT).show();
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
        lvChiTietBanLe = findViewById(R.id.lvChiTietBanLe);
    }
}
