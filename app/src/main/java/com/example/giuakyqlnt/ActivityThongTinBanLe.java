package com.example.giuakyqlnt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.ChiTietBanLe.ActivityChiTietBanLe;
import com.example.giuakyqlnt.ChiTietThongTin.ActivityXemChiTietThongTin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityThongTinBanLe extends AppCompatActivity {
    public static MyDatabase myDatabase;
    ThongTinBanLeAdapter thongTinBanLeAdapter;
    ListView lvTTBL;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thong_tin_ban_le);
        mapping();
        myDatabase = new MyDatabase(ActivityThongTinBanLe.this, MyDatabase.DB_NAME, null, 1);
        loadData();

        List<JSONObject> list;
        try {
            list = getAll();
            lvTTBL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    JSONObject obj = list.get(i);
                    try {
                        Intent intent = new Intent();
                        intent.setClass(ActivityThongTinBanLe.this, ActivityXemChiTietThongTin.class);
                        intent.putExtra("SoHD", obj.getString("SOHD"));
                        startActivity(intent);
//                        Toast.makeText(ActivityThongTinBanLe.this, obj.getString("SOHD"),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setEvent();

    }

    public void loadData(){
        List<JSONObject> list = null;
        try {
            list = getAll();
            Log.d("ListNew", list +"");
            thongTinBanLeAdapter = new ThongTinBanLeAdapter(ActivityThongTinBanLe.this, R.layout.dong_thong_tin_ban_le, list);
            lvTTBL.setAdapter(thongTinBanLeAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<JSONObject> getAll() throws JSONException {
        List<JSONObject> list = new ArrayList<>();
//        JSONObject obj = new JSONObject();
        String sql_select = "SELECT CT.SOHD, HD.NgayHD, NT.TenNT, sum(CT.SOLUONG * T.DONGIA) as TONGTIEN\n" +
                "from (((tbl_ChiTietBanLe as CT \n" +
                "\t\tINNER JOIN tbl_Thuoc as T on CT.MATHUOC = T.MATHUOC)\n" +
                "\t\tINNER JOIN tbl_HoaDon as HD on CT.SOHD = HD.SoHD)\n" +
                "\t\tINNER JOIN tbl_NhaThuoc as NT on HD.MaNT = NT.MaNT)\n" +
                " GROUP BY CT.SOHD";
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String SOHD = c.getString(0);
            String NgayHD = c.getString(1);
            String TenNT = c.getString(2);
            Integer TongTien = c.getInt(3);

            JSONObject obj = new JSONObject();
            obj.put("SOHD", SOHD);
            obj.put("NgayHD", NgayHD);
            obj.put("TenNT", TenNT);
            obj.put("TongTien", TongTien);

            list.add(obj);
        }
        return list;
    }

    public void setEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityThongTinBanLe.this, ActivityChiTietBanLe.class));
            }
        });
    }

    //Lấy danh sách
//    public static List<?> convertObjectToList(Object obj) {
//        List<?> list = new ArrayList<>();
//        if (obj.getClass().isArray()) {
//            list = Arrays.asList((Object[])obj);
//        } else if (obj instanceof Collection) {
//            list = new ArrayList<>((Collection<?>)obj);
//        }
//        return list;
//    }

    private void mapping() {
        lvTTBL = findViewById(R.id.lvThongTinBanLe);
        ivBack = findViewById(R.id.ivBackToBanThuoc);
    }
}