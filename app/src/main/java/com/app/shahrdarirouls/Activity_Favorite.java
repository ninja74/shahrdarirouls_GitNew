package com.app.shahrdarirouls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shahrdarirouls.DataBase.DataSource.tb_FavoriteDATASource;
import com.app.shahrdarirouls.DataBase.DataSource.tb_ShahrdariroulsDATASource;
import com.app.shahrdarirouls.DataBase.Table.tb_Favorite;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.util.ArrayList;
import java.util.List;

public class Activity_Favorite extends AppCompatActivity {

    List<tb_Favorite> data;
    ListView lstFavorite;
    List<tb_ShahrdariRouls> dataShar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        final tb_FavoriteDATASource tb_favoriteDATASource = new tb_FavoriteDATASource(Activity_Favorite.this);
        tb_favoriteDATASource.Open();
        data = tb_favoriteDATASource.GetList();
        tb_favoriteDATASource.Close();

        tb_ShahrdariroulsDATASource tb_shahrdariroulsDATASource = new tb_ShahrdariroulsDATASource(Activity_Favorite.this);
        tb_shahrdariroulsDATASource.Open();


        dataShar = new ArrayList<tb_ShahrdariRouls>();

        for (tb_Favorite dat : data){
            dataShar.add(tb_shahrdariroulsDATASource.GetRecordByIdPost(dat.IdPost));
        }

        tb_shahrdariroulsDATASource.Close();




        lstFavorite = (ListView) findViewById(R.id.lstFavorite);
        lstFavorite.setDivider(null);


        final ArrayAdapter adapterFavorite = new ArrayAdapter(Activity_Favorite.this, R.layout.activity_favorite, dataShar) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.row_lst_favorite, parent, false);
//                TextView txtLstFav = (TextView) convertView.findViewById(R.id.txtLstFav);
                TextView description = (TextView) convertView.findViewById(R.id.description);
                TextView title = (TextView) convertView.findViewById(R.id.title);
                Button btnLstFav = (Button) convertView.findViewById(R.id.btnLstFav);



                title.setText(dataShar.get(position).RoulsNAme + "\n");
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Activity_Favorite.this, Activity_Show.class);
                        intent.putExtra("KEY", dataShar.get(position).PK_Shahrdari + "");
                        startActivity(intent);
                    }
                });

                btnLstFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tb_FavoriteDATASource tb_favoriteDATASource1 = new tb_FavoriteDATASource(Activity_Favorite.this);
                        tb_favoriteDATASource1.Open();
                        tb_favoriteDATASource1.Delete(dataShar.get(position).PK_Shahrdari);
                        tb_favoriteDATASource1.Close();
                        dataShar.remove(position);
                        notifyDataSetChanged();
                    }
                });

                return convertView;
            }
        };
        lstFavorite.setAdapter(adapterFavorite);
    }

}