package com.example.giuakyqlnt.ChiTietThongTin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.ActivityThongTinBanLe;
import com.example.giuakyqlnt.MyDatabase;
import com.example.giuakyqlnt.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityXemChiTietThongTin extends AppCompatActivity {
    public static MyDatabase myDatabase;
    ChiTietThongTinAdapter chiTietThongTinAdapter;
    ListView lvChiTietTT;
    ImageView ivBack;
    TextView tvSOHD, tvMaNT, tvTenNT, tvDiaChi, tvNgayHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xem_chi_tiet_thong_tin);
        mapping();
        myDatabase = new MyDatabase(ActivityXemChiTietThongTin.this, MyDatabase.DB_NAME, null, 1);
        List<JSONObject> listChiTiet;
        try {
            Intent intent = getIntent();
            String SoHD = intent.getStringExtra("SoHD");
            listChiTiet = getChiTiet(SoHD);
            Log.d("ListChiTietTT", listChiTiet +"");
            //Lấy dữ liệu để print trên Chi tiết thông tin bán lẻ
            JSONObject obj = listChiTiet.get(0);
            //tvSOHD.setText(obj.getString("SOHD")); cách 1
            tvSOHD.setText(SoHD); //cách 2
            tvMaNT.setText(obj.getString("MaNT"));
            tvTenNT.setText(obj.getString("TenNT"));
            tvDiaChi.setText(obj.getString("DiaChi"));
            tvNgayHD.setText(obj.getString("NgayHD"));
            //show in listview ChiTietTT
            chiTietThongTinAdapter = new ChiTietThongTinAdapter(ActivityXemChiTietThongTin.this, R.layout.dong_xem_chi_tiet_thong_tin, listChiTiet);
            lvChiTietTT.setAdapter(chiTietThongTinAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        setEvent();

    }



    public List<JSONObject> getChiTiet(String SoHD) throws JSONException {
        List<JSONObject> list = new ArrayList<>();
        String sql_select = "SELECT CT.SOHD, NT.MaNT, NT.TenNT, \n" +
                "NT.DiaChi, HD.NgayHD, T.MATHUOC, T.TENTHUOC, CT.SOLUONG, T.DONGIA, (CT.SOLUONG * T.DONGIA) as THANHTIEN\n" +
                "from (((tbl_ChiTietBanLe as CT \n" +
                "\t\tINNER JOIN tbl_Thuoc as T on CT.MATHUOC = T.MATHUOC)\n" +
                "\t\tINNER JOIN tbl_HoaDon as HD on CT.SOHD = HD.SoHD)\n" +
                "\t\tINNER JOIN tbl_NhaThuoc as NT on HD.MaNT = NT.MaNT)\n" +
                "WHERE CT.SOHD = '"+SoHD+"'";
        Cursor c = myDatabase.SelectData(sql_select);
        while (c.moveToNext()) {
            String SOHD = c.getString(0);
            String MaNT = c.getString(1);
            String TenNT = c.getString(2);
            String DiaChi = c.getString(3);
            String NgayHD = c.getString(4);
            String MaThuoc = c.getString(5);
            String TenThuoc = c.getString(6);
            Integer SoLuong = c.getInt(7);
            Integer DonGia = c.getInt(8);
            Integer ThanhTien = c.getInt(9);


            JSONObject obj = new JSONObject();
            obj.put("SOHD", SOHD);
            obj.put("MaNT", MaNT);
            obj.put("TenNT", TenNT);
            obj.put("DiaChi", DiaChi);
            obj.put("NgayHD", NgayHD);
            obj.put("MATHUOC", MaThuoc);
            obj.put("TENTHUOC", TenThuoc);
            obj.put("SOLUONG", SoLuong);
            obj.put("DONGIA", DonGia);
            obj.put("THANHTIEN", ThanhTien);

            list.add(obj);
        }
        return list;
    }

    public void setEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityXemChiTietThongTin.this, ActivityThongTinBanLe.class));
            }
        });
    }

    private void mapping() {
        lvChiTietTT = findViewById(R.id.lvXemChiTietTT);
        tvSOHD = findViewById(R.id.txtSOHD);
        tvMaNT = findViewById(R.id.txtMaNT);
        tvTenNT = findViewById(R.id.txtTenNT);
        tvDiaChi = findViewById(R.id.txtDiaChi);
        tvNgayHD = findViewById(R.id.txtNgayHD);
        ivBack = findViewById(R.id.ivBack);
    }
}