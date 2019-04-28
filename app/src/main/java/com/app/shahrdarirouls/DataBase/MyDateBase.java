package com.app.shahrdarirouls.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDateBase extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "MyDbShardari.db";
    private static final int DATABASE_VERSION = 1;
    private static final int NEWDATABASE_VERSION = 2;

    public MyDateBase(Context context) {
        super(context, DATABASE_NAME, null, NEWDATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //super.onUpgrade(db, oldVersion, newVersion);
        Log.d("oldversion" , oldVersion + "");
        Log.d("oldversion" , newVersion + "");


        if (oldVersion == 1){
            db.execSQL(com.app.shahrdarirouls.DataBase.Structure.tb_FavoriteStructure.createTableQuery);
        }
    }
}
