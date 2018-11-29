package com.example.administrator.androidtest262;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.litepal.tablemanager.Connector;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name, factory,version);
        this.mContext=context;
    }

    public void onCreate(SQLiteDatabase database){
        Connector.getDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
