package com.example.giuakyqlnt.HoaDon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giuakyqlnt.R;

import java.util.List;

//import com.example.giuakyqlnt.HoaDon.EditHoaDonActivity;

public class HoaDonAdapter extends BaseAdapter {

    private ActivityHoaDon context;
    private int layout;
    List<HoaDon> listHoaDon;

    public HoaDonAdapter(ActivityHoaDon context, int layout, List<HoaDon> listHoaDon) {
        this.context = context;
        this.layout = layout;
        this.listHoaDon = listHoaDon;
    }

    @Override
    public int getCount() {
        return listHoaDon.size();
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
        TextView tvSoHD,tvNgayHD,tvMaNT;
        ImageView ivEdit, ivDelete;
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
            holder.tvMaNT = (TextView) view.findViewById(R.id.tvMaNT);
            holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            if(i%2!=0){
                view.setBackgroundColor(Color.parseColor("#D2D7DE"));
            }
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        HoaDon hoaDon = listHoaDon.get(i);
        holder.tvSoHD.setText(hoaDon.getSoHD());
        holder.tvNgayHD.setText(hoaDon.getNgayHD());
        holder.tvMaNT.setText(hoaDon.getMaNT());
        //bắt sự kiện
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditHoaDonActivity.class);
                intent.putExtra("hoadon", hoaDon);
                ((ActivityHoaDon)context).startActivity(intent);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCV(hoaDon.getSoHD());
            }
        });
        return view;
    }
}
