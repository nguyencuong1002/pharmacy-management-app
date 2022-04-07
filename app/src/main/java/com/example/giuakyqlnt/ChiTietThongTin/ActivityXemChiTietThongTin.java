package com.example.giuakyqlnt.ChiTietThongTin;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.giuakyqlnt.ActivityThongTinBanLe;
import com.example.giuakyqlnt.MyDatabase;
import com.example.giuakyqlnt.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityXemChiTietThongTin extends AppCompatActivity {
    public static MyDatabase myDatabase;
    ChiTietThongTinAdapter chiTietThongTinAdapter;
    ListView lvChiTietTT;
    ImageView ivBack;
    TextView tvSOHD, tvMaNT, tvTenNT, tvDiaChi, tvNgayHD;
    List<JSONObject> listChiTiet;
    Button btnGenerate;
    Bitmap bmp, scalesbmp;
    int pageWidth = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xem_chi_tiet_thong_tin);
        mapping();
        myDatabase = new MyDatabase(ActivityXemChiTietThongTin.this, MyDatabase.DB_NAME, null, 1);
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
        //Xuất File PDF
        btnGenerate = findViewById(R.id.btnGenerate);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.header);
        scalesbmp = Bitmap.createScaledBitmap(bmp, 1200, 518,false);


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        createPDF();

        setEvent();
    }

    //Tạo File PDF
    private void createPDF() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {
            Intent intent = getIntent();
            String SoHD = intent.getStringExtra("SoHD");
            listChiTiet = getChiTiet(SoHD);
            JSONObject obj = listChiTiet.get(0);
            btnGenerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PdfDocument myPdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();
                    Paint titlePain = new Paint();


                    PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1200,2010,1).create();
                    PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);

                    Canvas canvas = myPage1.getCanvas();
                    canvas.drawBitmap(scalesbmp, 0,0, myPaint);
                    //Tạo header Invoice Quản lý nhà thuốc
                    titlePain.setTextAlign(Paint.Align.CENTER);
                    titlePain.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    titlePain.setTextSize(70);
                    canvas.drawText("Quản Lý Nhà Thuốc", pageWidth/2, 270, titlePain);

                    myPaint.setColor(Color.rgb(0, 113, 188));
                    myPaint.setTextSize(30f);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("SĐT: 0971385487", 1160, 40, myPaint);

                    //Tạo tiêu đề Hóa đơn(Số HD, Mã NT, Tên NT, Địa Chỉ,...)
                    titlePain.setTextAlign(Paint.Align.CENTER);
                    titlePain.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
                    titlePain.setTextSize(70);
                    canvas.drawText("Hóa Đơn", pageWidth/2, 500, titlePain);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setTextSize(35f);
                    myPaint.setColor(Color.BLACK);
                    try {
                        canvas.drawText("Mã Nhà Thuốc:  " + obj.getString("MaNT"), 20, 590, myPaint);
                        canvas.drawText("Tên Nhà Thuốc:  " + obj.getString("TenNT"), 20, 640, myPaint);
                        canvas.drawText("Địa Chỉ:  " + obj.getString("DiaChi"), 21, 690 , myPaint);

                        myPaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText("Số Hóa Đơn:  " + obj.getString("SOHD"), pageWidth-20, 590, myPaint);
                        canvas.drawText("Ngày Lập:  " + obj.getString("NgayHD"), pageWidth-20, 640, myPaint);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Tạo Stroke cho tiêu đề danh sách Thuốc
                    myPaint.setStyle(Paint.Style.STROKE);
                    myPaint.setStrokeWidth(2);
                    canvas.drawRect(20, 780, pageWidth-20, 860, myPaint);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setStyle(Paint.Style.FILL);

                    //In dòng header list Thuốc
                    canvas.drawText("MT", 40, 830, myPaint);
                    canvas.drawText("Tên Thuốc", 200, 830, myPaint);
                    canvas.drawText("Đơn giá", 600, 830, myPaint);
                    canvas.drawText("SL", 800, 830, myPaint);
                    canvas.drawText("Thành Tiền", 920, 830, myPaint);

                    //In line ngăn cách các header
                    canvas.drawLine(180, 790, 180, 840, myPaint);
                    canvas.drawLine(580, 790, 580, 840, myPaint);
                    canvas.drawLine(780, 790, 780, 840, myPaint);
                    canvas.drawLine(900, 790, 900, 840, myPaint);

//                   In ra từng dòng thuốc theo thứ tự
                    int yAxis = 950;
                    int tongTien = 0;
                    for(int i = 0; i < listChiTiet.size(); i++){
                        JSONObject obj =  listChiTiet.get(i);
                        try {
                            canvas.drawText(obj.getString("MATHUOC"), 40, yAxis, myPaint);
                            canvas.drawText(obj.getString("TENTHUOC"), 200, yAxis, myPaint);

                            Integer donGia = Integer.parseInt(obj.getString("DONGIA"));
                            canvas.drawText(String.valueOf(formatter.format(donGia)), 600, yAxis, myPaint);

                            Integer soLuong = Integer.parseInt(obj.getString("SOLUONG"));
                            canvas.drawText(String.valueOf(formatter.format(soLuong)),800, yAxis, myPaint);

                            Integer thanhTien = Integer.parseInt(obj.getString("THANHTIEN"));
                            myPaint.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(String.valueOf(formatter.format(thanhTien)), pageWidth-40, yAxis, myPaint);

                            myPaint.setTextAlign(Paint.Align.LEFT);

                            tongTien = tongTien + (soLuong*donGia);

                            yAxis = yAxis + 100;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
//                    canvas.drawText("A02", 40, 950, myPaint);
//                    canvas.drawText("Acid Alendoric", 200, 950, myPaint);
//                    canvas.drawText("100,000", 600, 950, myPaint);
//                    canvas.drawText("15", 800, 950, myPaint);
//                    myPaint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText("1,500,000", pageWidth-40, 950, myPaint);
//                    myPaint.setTextAlign(Paint.Align.LEFT);
//
//                    canvas.drawText("D01", 40, 1050, myPaint);
//                    canvas.drawText("Dau Nong", 200, 1050, myPaint);
//                    canvas.drawText("200,000", 600, 1050, myPaint);
//                    canvas.drawText("60", 800, 1050, myPaint);
//                    myPaint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText("12,000,000", pageWidth-40, 1050, myPaint);
//                    myPaint.setTextAlign(Paint.Align.LEFT);

                    //In ra tổng tiền
//                    int tongTien = 13500000;
                    canvas.drawLine(680, 1200, pageWidth-20, 1200, myPaint);
                    canvas.drawText("Tổng Tiền", 700, 1250, myPaint);
                    canvas.drawText(":", 900, 1250, myPaint);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(String.valueOf(formatter.format(tongTien)), pageWidth-40, 1250, myPaint);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("VAT (0%)", 700, 1300, myPaint);
                    canvas.drawText(":", 900, 1300, myPaint);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("0", pageWidth-40, 1300, myPaint);
                    myPaint.setTextAlign(Paint.Align.LEFT);

                    myPaint.setColor(Color.rgb(247, 147, 30));
                    canvas.drawRect(680, 1350, pageWidth-20, 1450, myPaint);

                    myPaint.setColor(Color.BLACK);
                    myPaint.setTextSize(35f);
                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("Thanh Toán", 700,1415,myPaint);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    canvas.drawText(String.valueOf(formatter.format(tongTien)), pageWidth-40, 1415, myPaint);

                    //Xuất File PDF
                    myPdfDocument.finishPage(myPage1);//Đóng Page

                    try {
                        myPdfDocument.writeTo(new FileOutputStream(getFilePath()));
                        Toast.makeText(ActivityXemChiTietThongTin.this, "Xuất File PDF thành công !!", Toast.LENGTH_LONG).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    myPdfDocument.close();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Lấy đường dẫn xuất file (External Storage)
    private String getFilePath(){
        Intent intent = getIntent();
        String SoHD = intent.getStringExtra("SoHD");
        ContextWrapper contextWrapper = new ContextWrapper((getApplicationContext()));
        File pdfDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss" , Locale.getDefault()).format(System.currentTimeMillis());
        File file = new File(pdfDirectory, "/" + SoHD + "_" + fileName + ".pdf");
        return file.getPath();
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
        btnGenerate = findViewById(R.id.btnGenerate);
    }
}