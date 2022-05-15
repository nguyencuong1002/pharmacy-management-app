package com.example.giuakyqlnt.Thuoc;

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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThuocAdapter extends BaseAdapter {

    public ActivityThuoc context;
    private int layout;
    List<Thuoc> listThuoc;
    ArrayList<Thuoc> list = new ArrayList<>();

    public ThuocAdapter(ActivityThuoc context, int layout, List<Thuoc> listThuoc) {
        this.context = context;
        this.layout = layout;
        this.listThuoc = listThuoc;
    }

    @Override
    public int getCount() {
        return listThuoc.size();
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
        TextView tvMATHUOC,tvTENTHUOC,tvDVT, tvDONGIA;
        ImageView ivEdit, ivDelete, imgTHUOC;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ThuocAdapter.ViewHolder holder;
        if(view == null){
            holder = new ThuocAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvMATHUOC = (TextView) view.findViewById(R.id.tvMATHUOC);
            holder.tvTENTHUOC = (TextView) view.findViewById(R.id.tvTENTHUOC);
            holder.tvDVT = (TextView) view.findViewById(R.id.tvDVT);
            holder.tvDONGIA = (TextView) view.findViewById(R.id.tvDONGIA);
            holder.imgTHUOC = view.findViewById(R.id.imgTHUOC);
            holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            if(i%2!=0){
                view.setBackgroundColor(Color.parseColor("#D2D7DE"));
            }
            view.setTag(holder);
        }else{
            holder = (ThuocAdapter.ViewHolder) view.getTag();
        }

        Thuoc Thuoc = listThuoc.get(i);
        holder.tvMATHUOC.setText(String.valueOf(Thuoc.getMATHUOC()));
        holder.tvTENTHUOC.setText(Thuoc.getTENTHUOC());
        holder.tvDVT.setText(String.valueOf(Thuoc.getDVT()));
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tvDONGIA.setText(String.valueOf(formatter.format(Thuoc.getDONGIA())));
        //bắt sự kiện
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditThuocActivity.class);
                intent.putExtra("item", Thuoc);
                context.startActivity(intent);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCV(Thuoc.getTENTHUOC(),  Thuoc.getMATHUOC());
            }
        });
        return view;
    }

}
