package com.example.giuakyqlnt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

public class ThongTinBanLeAdapter extends BaseAdapter {

    public ActivityThongTinBanLe context;
    private int layout;
    List<JSONObject> listTTBL;

    public ThongTinBanLeAdapter(ActivityThongTinBanLe context, int layout, List<JSONObject> listTTBL) {
        this.context = context;
        this.layout = layout;
        this.listTTBL = listTTBL;
    }

    @Override
    public int getCount() {
        return listTTBL.size();
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
        TextView tvSoHD, tvNgayHD, tvTenNT, tvTongTien;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvSoHD = (TextView) view.findViewById(R.id.tvSoHD);
            holder.tvNgayHD = (TextView) view.findViewById(R.id.tvNgayHD);
            holder.tvTenNT = (TextView) view.findViewById(R.id.tvTenNT);
            holder.tvTongTien = (TextView) view.findViewById(R.id.tvTongTien);
            if(i%2==0){
                view.setBackgroundColor(Color.parseColor("#D2D7DE"));
            }
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
//        Log.d("List", listNhaThuoc + " ok");
        JSONObject obj =  listTTBL.get(i);
//        Log.d("NT", listNhaThuoc + " ok");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {
            holder.tvSoHD.setText(obj.getString("SOHD"));
            holder.tvNgayHD.setText(obj.getString("NgayHD"));
            holder.tvTenNT.setText(obj.getString("TenNT"));
            Integer tongTien = Integer.parseInt(obj.getString("TongTien"));
            holder.tvTongTien.setText(String.valueOf(formatter.format(tongTien)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //bắt sự kiện
        return view;
    }
}
