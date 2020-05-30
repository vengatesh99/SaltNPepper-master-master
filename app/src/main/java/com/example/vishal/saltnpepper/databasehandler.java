package com.example.vishal.saltnpepper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class databasehandler extends SQLiteOpenHelper {

    public static final String dbname="snp.db";
    public static final String tablename="restaurant";


    public databasehandler(Context context) {
        super(context, dbname, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurant(restid INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDR VARCHAR(60),CITY TEXT,PINCODE INT,TYPE TEXT,AVG TEXT,RATING REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS restaurant;");
         onCreate(db);
    }

    public boolean insert(String name,String addr,String city,String pin,String type,String avg,String rat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("addr",addr);
        contentValues.put("city",city);
        contentValues.put("pincode",pin);
        contentValues.put("type",type);
        contentValues.put("avg",avg);
        contentValues.put("rating",rat);

        long result=db.insert("restaurant",null,contentValues);

        if(result==-1)
            return false;
        else return true;

    }

    public Cursor getdata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant",null);
        return res;
    }

    public Cursor visc(String area)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant where addr like \'%"+area+"%\';",null);
        Log.i("Hello",res.getCount()+"");
        return res;

    }

    public Cursor restname(String area)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from restaurant where name like \'%"+area+"%\';",null);
        return res;

    }

    public void drop()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS restaurant;");
        onCreate(db);
    }
}
