package com.app.shahrdarirouls.DataBase.Structure;

/**
 * Created by SMH on 05-Aug-18.
 */

public class tb_ShardariRoulsStructure {


    public static final String tableName = "tb_ShahrdariRouls";

    public static final String colPK_ShahrdariRouls="PK_ShahrdariRouls";
    public static final String colroulName = "roulName";
    public static final String colmAde = "mAde";
    public static final String coltAbsare = "tAbsare";
    public static final String colsHarhOne = "sHarhOne";
    public static final String colsHoraMashmol = "sHoraMashmol";
    public static final String coltxt = "txt";

    public static String createTableQuery = "create table " + tableName + "(" +
            colPK_ShahrdariRouls + " integer primary key autoincrement, " +
            colroulName + " text, " +
            colmAde + " text, " +
            coltAbsare + " text, " +
            colsHarhOne + " text, " +
            colsHoraMashmol + " text, " +
            coltxt + " text" +
            ")";
}
