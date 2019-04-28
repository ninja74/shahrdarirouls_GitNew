package com.app.shahrdarirouls;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.shahrdarirouls.DataBase.DataSource.tb_ShahrdariroulsDATASource;
import com.app.shahrdarirouls.DataBase.DatabaseManagement;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.app.shahrdarirouls.DataBase.MyDateBase;

public class Activity_Main_SR extends AppCompatActivity {


    private DatabaseManagement myDateBase;



    List<tb_ShahrdariRouls> lstPI_SH;
    List<Data> Data_MainAC;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);fontSize.setFontSize();super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sr);
        myDateBase = new DatabaseManagement(Activity_Main_SR.this);


        tb_ShahrdariroulsDATASource dataSource = new tb_ShahrdariroulsDATASource(this);
        dataSource.Open();
        lstPI_SH = dataSource.GetList();
        dataSource.Close();
        Data_MainAC = fill_with_data();
        Recycler_View_Adapter adapterRecycler = new Recycler_View_Adapter(Data_MainAC, getApplication());


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }

    public List<Data> fill_with_data() {
        List<Data> Data_MainAC = new ArrayList<>();
        for (int i = lstPI_SH.size() - 1; i > 0; i--) {
            Data_MainAC.add(new Data(lstPI_SH.get(i).RoulsNAme + "", lstPI_SH.get(i).txtRouls + "", lstPI_SH.get(i).PK_Shahrdari));
        }
        return Data_MainAC;
    }



}
