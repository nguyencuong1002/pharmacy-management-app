package com.example.giuakyqlnt.HoaDon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.MainActivity;
import com.example.giuakyqlnt.MyDatabase;
import com.example.giuakyqlnt.NhaThuoc.ActivityNhaThuoc;
import com.example.giuakyqlnt.NhaThuoc.AddNhaThuocActivity;
import com.example.giuakyqlnt.NhaThuoc.NhaThuoc;
import com.example.giuakyqlnt.NhaThuoc.NhaThuocAdapter;
import com.example.giuakyqlnt.R;

import java.util.ArrayList;

public class ActivityHoaDon extends AppCompatActivity {
    public static MyDatabase myDatabase;
//    static final String DB_NAME = "qlnhathuoc.sqlite";
    public static final String TABLE_NAME = "tbl_HoaDon";
    static final String SoHD_FIELD = "SoHD";
    static final String NgayHD_FIELD = "NgayHD";
    static final String MaNT_FIELD = "MaNT";//khóa ngoại

    HoaDonAdapter hoaDonAdapter;
    ListView lvHoaDon;
    ImageView ivAdd, ivBack;
    ArrayList<HoaDon> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        mapping();

        myDatabase = new MyDatabase(ActivityHoaDon.this, MyDatabase.DB_NAME, null, 1);
        String sql_create_table = "create table if not exists "+TABLE_NAME+" ("+SoHD_FIELD+" varchar(10) primary key " +
                ", "+NgayHD_FIELD+" date " +
                ", "+MaNT_FIELD +" varchar(10)" +
                ", foreign key ("+MaNT_FIELD+") references "+ActivityNhaThuoc.TABLE_NAME+"("+MaNT_FIELD+"))";
        //Tạo bảng
        myDatabase.ExecuteSQL(sql_create_table);
        loadData();
        setEvent();
    }

    public void loadData() {
        list = getAll();
        hoaDonAdapter = new HoaDonAdapter(ActivityHoaDon.this, R.layout.dong_hoa_don, list);
        lvHoaDon.setAdapter(hoaDonAdapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menubar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.menuAdd) {
//            startActivity(new Intent(ActivityHoaDon.this, AddHoaDonActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void setEvent(){
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHoaDon.this, AddHoaDonActivity.class));

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHoaDon.this, MainActivity.class));
            }
        });
    }

    //Lấy danh sách
    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql_select = "select * from " + TABLE_NAME;
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String soHD = c.getString(0);
            String ngayHD = c.getString(1);
            String maNT = c.getString(2);

            HoaDon hoaDon = new HoaDon(soHD, ngayHD, maNT);
            list.add(hoaDon);
        }
        return list;
    }

    //Show dialog Xóa Dữ liệu
    public void DialogXoaCV(String soHD) {
        String whereClause = ""+SoHD_FIELD+" = ?";
        String[] whereArgs = {soHD};
        Log.d("AAAD", whereArgs + " ok");
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa hóa đơn " + soHD + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDatabase.delete(TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(ActivityHoaDon.this, "Đã xóa " + soHD, Toast.LENGTH_SHORT).show();
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
        lvHoaDon = findViewById(R.id.lvHoaDon);
        ivAdd = findViewById(R.id.ivAdd);
        ivBack = findViewById(R.id.ivBack);
    }
}
