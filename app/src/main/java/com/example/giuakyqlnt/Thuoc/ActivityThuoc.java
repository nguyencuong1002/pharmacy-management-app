package com.example.giuakyqlnt.Thuoc;

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
import com.example.giuakyqlnt.R;

import java.util.ArrayList;

public class ActivityThuoc extends AppCompatActivity {
    public static MyDatabase myDatabase;
    public static final String DB_NAME = "qlnhathuoc.sqlite";
    static final String TABLE_NAME = "tbl_Thuoc";
    static final String MATHUOC_FIELD = "MATHUOC";
    static final String TENTHUOC_FIELD = "TENTHUOC";
    static final String DVT_FIELD = "DVT";
    static final String DONGIA_FIELD = "DONGIA";
    ThuocAdapter ThuocAdapter;
    ListView lvThuoc;
    ImageView ivAdd, ivBack;
    ArrayList<Thuoc> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuoc);
        mapping();

        myDatabase = new MyDatabase(ActivityThuoc.this, DB_NAME, null, 1);
        String sql_create_table = "create table if not exists "+TABLE_NAME+" ("+MATHUOC_FIELD+" varchar(10) primary key , "+TENTHUOC_FIELD+" nvarchar(50), "+DVT_FIELD+" nvarchar(50), "+DONGIA_FIELD+" float)";
        //Tạo bảng
        myDatabase.ExecuteSQL(sql_create_table);
        loadData();
        setEvent();
    }

    public void loadData() {
        list = getAll();
        ThuocAdapter = new ThuocAdapter(ActivityThuoc.this, R.layout.dong_thuoc, list);
        lvThuoc.setAdapter(ThuocAdapter);
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
//            startActivity(new Intent(ActivityThuoc.this, AddThuocActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void setEvent(){
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityThuoc.this, AddThuocActivity.class));

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityThuoc.this, MainActivity.class));
            }
        });
    }



    //Lấy danh sách
    public ArrayList<Thuoc> getAll() {
        ArrayList<Thuoc> list = new ArrayList<>();
        String sql_select = "select * from " + TABLE_NAME;
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String MATHUOC = c.getString(0);
            String TENTHUOC = c.getString(1);
            String DVT = c.getString(2);
            Float DONGIA = c.getFloat(3);

            Thuoc Thuoc = new Thuoc(MATHUOC, TENTHUOC, DVT, DONGIA);
            list.add(Thuoc);
        }
        return list;
    }

    //Show dialog Xóa Dữ liệu
    public void DialogXoaCV(String TENTHUOC, String MATHUOC) {
        String whereClause = ""+MATHUOC_FIELD+" = ?";
        String[] whereArgs = {MATHUOC};
        Log.d("AAAD", whereArgs + " ok");
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa thuốc " + TENTHUOC + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDatabase.delete(TABLE_NAME, whereClause, whereArgs);
                Toast.makeText(ActivityThuoc.this, "Đã xóa " + TENTHUOC +" "+ MATHUOC, Toast.LENGTH_SHORT).show();
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
        lvThuoc = findViewById(R.id.lvThuoc);
        ivAdd = findViewById(R.id.ivAddThuoc);
        ivBack = findViewById(R.id.ivBack);
    }
}
