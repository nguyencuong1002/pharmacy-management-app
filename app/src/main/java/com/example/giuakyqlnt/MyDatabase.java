package com.example.giuakyqlnt;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Create, update, delete
    public void ExecuteSQL(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
    //Read
    public Cursor SelectData(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql, null);
        return c;
    }

    public long insert(String table, String nullColumnHack, ContentValues values){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.insert(table, nullColumnHack,values);
    }

    public long update(String table, ContentValues values, String whereClause, String[] whereArgs){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    public int delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

