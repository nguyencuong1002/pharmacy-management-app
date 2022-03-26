package com.example.giuakyqlnt.NhaThuoc;

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

public class NhaThuocAdapter extends BaseAdapter {

    public ActivityNhaThuoc context;
    private int layout;
    List<NhaThuoc> listNhaThuoc;

    public NhaThuocAdapter(ActivityNhaThuoc context, int layout, List<NhaThuoc> listNhaThuoc) {
        this.context = context;
        this.layout = layout;
        this.listNhaThuoc = listNhaThuoc;
    }

    @Override
    public int getCount() {
        return listNhaThuoc.size();
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
        TextView tvMaNT,tvTenNT,tvDiaChi;
        ImageView ivEdit, ivDelete;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvMaNT = (TextView) view.findViewById(R.id.tvMaNT);
            holder.tvTenNT = (TextView) view.findViewById(R.id.tvTenNT);
            holder.tvDiaChi = (TextView) view.findViewById(R.id.tvDiaChi);
            holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        NhaThuoc nhaThuoc = listNhaThuoc.get(i);
        holder.tvMaNT.setText(String.valueOf(nhaThuoc.getMaNT()));
        holder.tvTenNT.setText(nhaThuoc.getTenNT());
        holder.tvDiaChi.setText(String.valueOf(nhaThuoc.getDiaChi()));
        //bắt sự kiện
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNhaThuocActivity.class);
                intent.putExtra("item", nhaThuoc);
                context.startActivity(intent);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCV(nhaThuoc.getTenNT(),  nhaThuoc.getMaNT());
            }
        });
        return view;
    }
}
