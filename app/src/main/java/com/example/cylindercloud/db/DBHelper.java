package com.example.cylindercloud.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.cylindercloud.utils.LogUtils;

/**
 * Created by Administrator on 2015/10/20 0020.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String dbName) {
        super(context, dbName, null, 1);
    }

    public final static String TABLE_TOKEN = "token";

    /**
     * token 会有时效性 24小时
     */
    private final String SQL_TABLE_TOKEN = "CREATE TABLE " + TABLE_TOKEN + " (_id integer primary key autoincrement," +//
            "token varchar(200)," +//
            "create_time long," +//
            "end_time long);";//

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_TOKEN);
        LogUtils.d("%s",SQL_TABLE_TOKEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
