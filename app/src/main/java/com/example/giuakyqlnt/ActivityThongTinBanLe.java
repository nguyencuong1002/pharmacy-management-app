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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
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
        setEvent();
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
//        setEvent();
        chartView();
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

    //Chart View
    public void chartView(){
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        List<JSONObject> list;
        List<DataEntry> data = new ArrayList<>();

        try {
            list = getAll();
            for(int i = 0; i < list.size(); i++){
                JSONObject obj =  list.get(i);
                String SoHD = obj.getString("SOHD");
                Integer tongTien  = Integer.parseInt(obj.getString("TongTien"));
                data.add(new ValueDataEntry(SoHD, tongTien));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Cartesian cartesian = AnyChart.column();

//        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry("Rouge", 280540));
//        data.add(new ValueDataEntry("sss", 594190));
//        data.add(new ValueDataEntry("Mascaras", 102610));
//        data.add(new ValueDataEntry("Lip gloss", 80430));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Biểu đồ Thông Tin Bán Lẻ");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Hóa Đơn");
        cartesian.yAxis(0).title("Tổng Tiền");

        anyChartView.setChart(cartesian);
    }

    private void mapping() {
        lvTTBL = findViewById(R.id.lvThongTinBanLe);
        ivBack = findViewById(R.id.ivBackToBanThuoc);
    }
}