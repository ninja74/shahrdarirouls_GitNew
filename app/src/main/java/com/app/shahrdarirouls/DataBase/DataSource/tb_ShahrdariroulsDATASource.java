package com.app.shahrdarirouls.DataBase.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.app.shahrdarirouls.DataBase.MyDateBase;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.util.ArrayList;
import java.util.List;

//import com.app.shahrdarirouls.DataBase.MyDateBase;

/**
 * Created by SMH on 05-Aug-18.
 */

public class tb_ShahrdariroulsDATASource {
    private SQLiteDatabase database;
    private MyDateBase dbManagement;


    private String[] allColumns = {

            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colPK_ShahrdariRouls,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colroulName,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colmAde,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.coltAbsare,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHarhOne,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol,
            com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.coltxt


    };

    public tb_ShahrdariroulsDATASource(Context context) {
        dbManagement = new MyDateBase(context);
    }

    public void Open() throws SQLException {
        database = dbManagement.getWritableDatabase();
    }

    public void Close() {
        dbManagement.close();
        database.close();
    }

    public long Add(tb_ShahrdariRouls data) {
        ContentValues values = new ContentValues();

        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colroulName, data.RoulsNAme);
        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colmAde, data.MadeNum);
        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.coltAbsare, data.Tabsare);
        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHarhOne, data.SharhOne);
        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol, data.ShoraiMashmol);
        values.put(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.coltxt, data.txtRouls);


        return database.insert(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, null, values);
    }


    public long QueryNumEntries() {
        return DatabaseUtils.queryNumEntries(database, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName);
    }

    public tb_ShahrdariRouls GetRecord() {
        Cursor cursor = database.query(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        tb_ShahrdariRouls data = ConvertToRecord(cursor);
        cursor.close();
        return data;
    }

    public tb_ShahrdariRouls GetRecord(int id) {
        Cursor cursor = database.query(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns,
                com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colPK_ShahrdariRouls + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        tb_ShahrdariRouls data = ConvertToRecord(cursor);
        cursor.close();
        return data;
    }

    public tb_ShahrdariRouls GetRecordByIdPost(int id) {
        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colPK_ShahrdariRouls + " = " + id, null, null, null, null, null);


        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        tb_ShahrdariRouls data = ConvertToRecord(cursor);
        cursor.close();
        return data;
    }

    public void DeleteAll() {
        database.delete(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, null, null);
    }


    public List<tb_ShahrdariRouls> GetRecordByCompanyName(
            String SESTchBoxShar
            , String SESTchBoxShora
    ) {

        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        try {
            SESTchBoxShar = SESTchBoxShar.substring(9);
            SESTchBoxShora = SESTchBoxShora.substring(9);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns,


                com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHarhOne + " IN " + "('"
                        + SESTchBoxShar +

                        "') OR " + com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol + " IN " + "('"
                        + SESTchBoxShora +
                        "')"
                ,
                null, null, null, null, null);


        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            lstData.add(tmpInfo);
            cursor.moveToNext();
        }

        if (cursor.getCount() == 0) {
            return null;
        }


        cursor.close();
        return lstData;

    }


    public List<tb_ShahrdariRouls> GetRecordByLike(String txt) {

        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.coltxt + " LIKE '%" + txt + "%'",
                null, null, null, null, null);


        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            lstData.add(tmpInfo);
            cursor.moveToNext();
        }

        if (cursor.getCount() == 0) {
            return null;
        }


        cursor.close();
        return lstData;

    }


    public List<tb_ShahrdariRouls> GetRecordBySubPosts(String txt) {

        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol + " IN " + "('" + txt + "')", null, null, null, null, null);

        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            lstData.add(tmpInfo);
            cursor.moveToNext();
        }

        if (cursor.getCount() == 0) {
            return null;
        }


        cursor.close();
        return lstData;

    }


    public List<tb_ShahrdariRouls> GetList() {
        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        Cursor cursor = database.query(com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName,
                allColumns,
                null, null, null, null,
                com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colPK_ShahrdariRouls + " DESC");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            lstData.add(tmpInfo);
            cursor.moveToNext();
        }

        cursor.close();
        return lstData;
    }


    public List<tb_ShahrdariRouls> GetListShora() {
        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns
                , null, null, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol, null, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHoraMashmol, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            if (!tmpInfo.ShoraiMashmol.equals("")) {
                lstData.add(tmpInfo);
            }
            cursor.moveToNext();
        }

        cursor.close();
        return lstData;
    }

    public List<tb_ShahrdariRouls> GetListSharh() {
        List<tb_ShahrdariRouls> lstData = new ArrayList<tb_ShahrdariRouls>();

        Cursor cursor = database.query(false, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.tableName, allColumns
                , null, null, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHarhOne, null, com.app.shahrdarirouls.DataBase.Structure.tb_ShardariRoulsStructure.colsHarhOne, null);


        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tb_ShahrdariRouls tmpInfo = ConvertToRecord(cursor);
            if (!tmpInfo.SharhOne.equals("")) {
                lstData.add(tmpInfo);
            }
            cursor.moveToNext();
        }

        cursor.close();
        return lstData;
    }


    private tb_ShahrdariRouls ConvertToRecord(Cursor cursor) {
        tb_ShahrdariRouls data = new tb_ShahrdariRouls();


        data.PK_Shahrdari = cursor.getInt(0);
        data.RoulsNAme = cursor.getString(1);
        data.MadeNum = cursor.getString(2);
        data.Tabsare = cursor.getString(3);
        data.SharhOne = cursor.getString(4);
        data.ShoraiMashmol = cursor.getString(5);
        data.txtRouls = cursor.getString(6);

        return data;
    }

}
