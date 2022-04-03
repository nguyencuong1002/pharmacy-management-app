package com.example.giuakyqlnt.ChiTietThongTin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.giuakyqlnt.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietThongTinAdapter extends BaseAdapter {

    public ActivityXemChiTietThongTin context;
    private int layout;
    List<JSONObject> listChiTietTT;

    public ChiTietThongTinAdapter(ActivityXemChiTietThongTin context, int layout, List<JSONObject> listChiTietTT) {
        this.context = context;
        this.layout = layout;
        this.listChiTietTT = listChiTietTT;
    }

    @Override
    public int getCount() {
        return listChiTietTT.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView tvMaTHuoc, tvTenThuoc, tvSoLuong, tvDonGia,tvThanhTien;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvMaTHuoc = (TextView) view.findViewById(R.id.tvMaTHuoc);
            holder.tvTenThuoc = (TextView) view.findViewById(R.id.tvTenThuoc);
            holder.tvSoLuong = (TextView) view.findViewById(R.id.tvSoLuong);
            holder.tvDonGia = (TextView) view.findViewById(R.id.tvDonGia);
            holder.tvThanhTien = (TextView) view.findViewById(R.id.tvThanhTien);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
//        Log.d("List", listNhaThuoc + " ok");
        JSONObject obj =  listChiTietTT.get(i);
//        Log.d("NT", listNhaThuoc + " ok");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {
            holder.tvMaTHuoc.setText(obj.getString("MATHUOC"));
            holder.tvTenThuoc.setText(obj.getString("TENTHUOC"));

            Integer soLuong = Integer.parseInt(obj.getString("SOLUONG"));
            holder.tvSoLuong.setText(String.valueOf(formatter.format(soLuong)));

            Integer donGia = Integer.parseInt(obj.getString("DONGIA"));
            holder.tvDonGia.setText(String.valueOf(formatter.format(donGia)));

            Integer thanhTien = Integer.parseInt(obj.getString("THANHTIEN"));
            holder.tvThanhTien.setText(String.valueOf(formatter.format(thanhTien)));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //bắt sự kiện
        return view;
    }
}
