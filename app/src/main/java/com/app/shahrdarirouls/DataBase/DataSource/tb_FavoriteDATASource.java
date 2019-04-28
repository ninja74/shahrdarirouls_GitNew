package com.app.shahrdarirouls.DataBase.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.app.shahrdarirouls.DataBase.MyDateBase;
import com.app.shahrdarirouls.DataBase.Structure.tb_FavoriteStructure;
import com.app.shahrdarirouls.DataBase.Table.tb_Favorite;

import java.util.ArrayList;
import java.util.List;

//import com.app.shahrdarirouls.DataBase.MyDateBase;

/**
 * Created by SMH on 28-Aug-18.
 */

public class tb_FavoriteDATASource {

    private SQLiteDatabase database;
    private MyDateBase dbManagement;



    private String[] allColumns = {
            tb_FavoriteStructure.colPK_Favorite,
            tb_FavoriteStructure.colIdPost
    };

    public tb_FavoriteDATASource(Context context) {
        dbManagement = new MyDateBase(context);
    }

    public void Open() throws SQLException {
        database = dbManagement.getWritableDatabase();
    }

    public void Close() {
        dbManagement.close();
        database.close();
    }

    public long Add(tb_Favorite data) {
        ContentValues values = new ContentValues();

        values.put(tb_FavoriteStructure.colIdPost, data.IdPost);


        return database.insert(tb_FavoriteStructure.tableName , null , values);
    }


    public long QueryNumEntries() {
        return DatabaseUtils.queryNumEntries(database, tb_FavoriteStructure.tableName);
    }

    public void Delete(int id){
        database.delete(tb_FavoriteStructure.tableName,
                tb_FavoriteStructure.colIdPost + "=" + id,
                null);
    }

    public tb_Favorite GetRecord() {
        Cursor cursor = database.query(tb_FavoriteStructure.tableName, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        tb_Favorite data = ConvertToRecord(cursor);
        cursor.close();
        return data;
    }

    public tb_Favorite GetRecord(int id) {
        Cursor cursor = database.query(tb_FavoriteStructure.tableName, allColumns,
                tb_FavoriteStructure.colPK_Favorite + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        tb_Favorite data = ConvertToRecord(cursor);
        cursor.close();
        return data;
    }

    public  Boolean ExistinFavorite(int IdPost){
        try {
            Cursor cursor=database.query(tb_FavoriteStructure.tableName,allColumns,
                    tb_FavoriteStructure.colIdPost + "= " + IdPost ,
                    null, null, null, null);
            cursor.moveToFirst();

            if (cursor.getCount() == 0){
                return false;
            }

            tb_Favorite data=ConvertToRecord(cursor);
            cursor.close();
            return true;
        }catch (Exception e){
            return null;
        }

    }


    public List<tb_Favorite> GetList() {
        List<tb_Favorite> lstData = new ArrayList<tb_Favorite>();

        Cursor cursor = database.query(tb_FavoriteStructure.tableName,
                allColumns,
                null, null, null, null,
                tb_FavoriteStructure.colPK_Favorite + " DESC");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tb_Favorite tmpInfo = ConvertToRecord(cursor);
            lstData.add(tmpInfo);
            cursor.moveToNext();
        }

        cursor.close();
        return lstData;
    }


    private tb_Favorite ConvertToRecord(Cursor cursor) {
        tb_Favorite data = new tb_Favorite();


        data.PK_Favorite = cursor.getInt(0);
        data.IdPost = cursor.getInt(1);

        return data;
    }


}
