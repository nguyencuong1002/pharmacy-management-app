package com.example.giuakyqlnt.ChiTietBanLe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.giuakyqlnt.R;

import java.util.List;

public class ChiTietBanLeAdapter extends BaseAdapter {

    public ActivityChiTietBanLe context;
    private int layout;
    List<ChiTietBanLe> listChiTietBanLe;

    public ChiTietBanLeAdapter(ActivityChiTietBanLe context, int layout, List<ChiTietBanLe> listChiTietBanLe) {
        this.context = context;
        this.layout = layout;
        this.listChiTietBanLe = listChiTietBanLe;
    }

    @Override
    public int getCount() {
        return listChiTietBanLe.size();
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
        TextView tvSOHD,tvMATHUOC,tvSOLUONG;
        ImageView ivEdit, ivDelete;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        com.example.giuakyqlnt.ChiTietBanLe.ChiTietBanLeAdapter.ViewHolder holder;
        if(view == null){
            holder = new com.example.giuakyqlnt.ChiTietBanLe.ChiTietBanLeAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvSOHD = (TextView) view.findViewById(R.id.tvSOHD);
            holder.tvMATHUOC = (TextView) view.findViewById(R.id.tvMATHUOC);
            holder.tvSOLUONG = (TextView) view.findViewById(R.id.tvSOLUONG);
            holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            view.setTag(holder);
        }else{
            holder = (com.example.giuakyqlnt.ChiTietBanLe.ChiTietBanLeAdapter.ViewHolder) view.getTag();
        }

        ChiTietBanLe chiTietBanLe = listChiTietBanLe.get(i);
        holder.tvSOHD.setText(String.valueOf(chiTietBanLe.getSOHD()));
        holder.tvMATHUOC.setText(chiTietBanLe.getMATHUOC());
        holder.tvSOLUONG.setText(String.valueOf(chiTietBanLe.getSOLUONG()));
        //bắt sự kiện
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditChiTietBanLeActivity.class);
                intent.putExtra("item", chiTietBanLe);
                context.startActivity(intent);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCV(chiTietBanLe.getMATHUOC(),  chiTietBanLe.getSOHD());
            }
        });
        return view;
    }
}

