package com.app.shahrdarirouls.DataBase.Structure;

/**
 * Created by SMH on 28-Aug-18.
 */

public class tb_FavoriteStructure {

    public static final String tableName = "tb_Favorite";

    public static final String colPK_Favorite="PK_Favorite";
    public static final String colIdPost = "IdPost";


    public static String createTableQuery = "create table " + tableName + "(" +
            colPK_Favorite + " integer primary key autoincrement, " +
            colIdPost + " integer" +
            ")";
}
