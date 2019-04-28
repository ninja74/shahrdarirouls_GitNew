package com.app.shahrdarirouls;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.shahrdarirouls.DataBase.DataSource.tb_ShahrdariroulsDATASource;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.util.ArrayList;
import java.util.List;

import life.sabujak.roundedbutton.RoundedButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Search_SR extends AppCompatActivity {

    static ProgressBar progressBarSE;

    LinearLayout linerFirstSearch, linerSecondSearch;
    Recycler_View_Adapter adapterSE;
    RecyclerView recyclerviewSE;
    ListView listSharhOne, lstViewShoraMashmol, lstViewShowSearch;
    EditText edtTxtSearchinTxt;
    RoundedButton btnSearch;

    List<Data> dataSERACH;
    List<tb_ShahrdariRouls> data;
    List<tb_ShahrdariRouls> datatxt;
    List<tb_ShahrdariRouls> itemShora;
    List<tb_ShahrdariRouls> itemSharh;


    String STchBoxShar = null, STchBoxShora = null;
    String STedtTxtSearchinTxt = "";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);
        fontSize.setFontSize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sr);


        linerFirstSearch = (LinearLayout) findViewById(R.id.linerFirstSearch);
        linerSecondSearch = (LinearLayout) findViewById(R.id.linerSecondSearch);
        listSharhOne = (ListView) findViewById(R.id.listSharhOne);
        lstViewShoraMashmol = (ListView) findViewById(R.id.lstViewShoraMashmol);
        lstViewShowSearch = (ListView) findViewById(R.id.lstViewShowSearch);
        recyclerviewSE = (RecyclerView) findViewById(R.id.recyclerview_SE);
        edtTxtSearchinTxt = (EditText) findViewById(R.id.edtTxtSearchinTxt);
        btnSearch = findViewById(R.id.btnSearch);

        tb_ShahrdariroulsDATASource tb_shahrdariroulsDATASourc = new tb_ShahrdariroulsDATASource(Activity_Search_SR.this);
        tb_shahrdariroulsDATASourc.Open();
        itemShora = tb_shahrdariroulsDATASourc.GetListShora();
        tb_shahrdariroulsDATASourc.Close();

        tb_ShahrdariroulsDATASource tb_shahrdariroulsDATASource = new tb_ShahrdariroulsDATASource(Activity_Search_SR.this);
        tb_shahrdariroulsDATASource.Open();
        itemSharh = tb_shahrdariroulsDATASource.GetListSharh();
        tb_shahrdariroulsDATASource.Close();


        lstViewShoraMashmol.setDivider(null);
        listSharhOne.setDivider(null);

        ArrayAdapter<tb_ShahrdariRouls> adapter_SHora = new ArrayAdapter<tb_ShahrdariRouls>(this, R.layout.row_listsearch, itemShora) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.row_listsearch, parent, false);
                final CheckBox chboxList = (CheckBox) convertView.findViewById(R.id.chboxList);
                chboxList.setText(itemShora.get(position).ShoraiMashmol);
                chboxList.setChecked(itemShora.get(position).checkBox);

                chboxList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if (chboxList.isChecked()) {
                            STchBoxShora += "' , '" + compoundButton.getText();
                            itemShora.get(position).checkBox = b;
                        } else {
                            STchBoxShora = STchBoxShora.replace("' , '" + compoundButton.getText().toString(), "").trim();
                            itemShora.get(position).checkBox = b;
                        }
                    }
                });
                return convertView;
            }
        };


        ArrayAdapter<tb_ShahrdariRouls> adapter_Sharh = new ArrayAdapter<tb_ShahrdariRouls>(this, R.layout.row_listsearch, itemSharh) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.row_listsearch, parent, false);

                final CheckBox chboxList = (CheckBox) convertView.findViewById(R.id.chboxList);
                chboxList.setChecked(itemSharh.get(position).checkBox);
                chboxList.setText(itemSharh.get(position).SharhOne);

                chboxList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        if (chboxList.isChecked()) {
                            STchBoxShar += "' , '" + compoundButton.getText();
                            itemSharh.get(position).checkBox = b;
                        } else {
                            STchBoxShar = STchBoxShar.replace("' , '" + compoundButton.getText().toString(), "").trim();
                            itemSharh.get(position).checkBox = b;
                        }
                    }
                });
                return convertView;
            }
        };

        lstViewShoraMashmol.setAdapter(adapter_SHora);
        listSharhOne.setAdapter(adapter_Sharh);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForbtnSearch();
            }
        });
    }

    public List<Data> fill_with_data() {
        List<Data> dataSERACH = new ArrayList<>();
        for (int i = data.size() - 1; i >= 0; i--) {
            dataSERACH.add(new Data(data.get(i).RoulsNAme + "", data.get(i).txtRouls + "", data.get(i).PK_Shahrdari));
        }

        return dataSERACH;
    }

    public List<Data> fill_with_data_txt(List<tb_ShahrdariRouls> dataSE) {
        List<Data> dataSERACH = new ArrayList<>();
        for (int i = dataSE.size() - 1; i >= 0; i--) {
            dataSERACH.add(new Data(dataSE.get(i).PK_Shahrdari + "", dataSE.get(i).txtRouls + "", dataSE.get(i).PK_Shahrdari));
        }

        return dataSERACH;
    }


    private void ForbtnSearch() {
        STedtTxtSearchinTxt = edtTxtSearchinTxt.getText().toString();

        tb_ShahrdariroulsDATASource tb_shahrdariroulsDATASource = new tb_ShahrdariroulsDATASource(Activity_Search_SR.this);
        tb_shahrdariroulsDATASource.Open();


        if (STchBoxShar != null || STchBoxShora != null) {

            data = tb_shahrdariroulsDATASource.GetRecordByCompanyName(STchBoxShar, STchBoxShora);

            if (data != null) {
                if (!(STedtTxtSearchinTxt.equals(""))) {
                    datatxt = new ArrayList<>();
                    try {
                        for (tb_ShahrdariRouls dat : data) {
                            if (dat.txtRouls.contains(STedtTxtSearchinTxt)) {
                                datatxt.add(dat);
                            } else if (!(dat.txtRouls.contains(STedtTxtSearchinTxt))) {
                                recyclerviewSE.setAdapter(null);
                            }
                        }
                        dataSERACH = fill_with_data_txt(datatxt);
                        adapterSE = new Recycler_View_Adapter(dataSERACH, Activity_Search_SR.this);
                        recyclerviewSE.setAdapter(adapterSE);
                        recyclerviewSE.setLayoutManager(new LinearLayoutManager(Activity_Search_SR.this));
                    } catch (Exception e) {
                        Toast.makeText(Activity_Search_SR.this, "ارور در جستجو", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        dataSERACH = fill_with_data();
                        adapterSE = new Recycler_View_Adapter(dataSERACH, Activity_Search_SR.this);
                        recyclerviewSE.setAdapter(adapterSE);
                        recyclerviewSE.setLayoutManager(new LinearLayoutManager(Activity_Search_SR.this));
                    } catch (Exception e) {
                        Toast.makeText(Activity_Search_SR.this, "ارور در جستجو", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(Activity_Search_SR.this, "هیچ دیتایی وجود ندارد!", Toast.LENGTH_SHORT).show();
                recyclerviewSE.setAdapter(null);
            }

        } else if (STedtTxtSearchinTxt != null) {

            data = tb_shahrdariroulsDATASource.GetRecordByLike(STedtTxtSearchinTxt.equals("") ? null : STedtTxtSearchinTxt);
            if (data != null) {
                try {
                    dataSERACH = fill_with_data();
                    adapterSE = new Recycler_View_Adapter(dataSERACH, Activity_Search_SR.this);
                    recyclerviewSE.setAdapter(adapterSE);
                    recyclerviewSE.setLayoutManager(new LinearLayoutManager(Activity_Search_SR.this));
                } catch (Exception e) {
                    Toast.makeText(Activity_Search_SR.this, "ارور در جستجو", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Activity_Search_SR.this, "هیچ دیتایی وجود ندارد!", Toast.LENGTH_SHORT).show();
                recyclerviewSE.setAdapter(null);
            }
            tb_shahrdariroulsDATASource.Close();

        }
    }
}


