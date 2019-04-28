package com.app.shahrdarirouls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.shahrdarirouls.DataBase.DataSource.tb_ShahrdariroulsDATASource;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.util.ArrayList;
import java.util.List;

public class Activity_SubPosts extends AppCompatActivity {

    String[] lstSubPostsArr = {"تهران و ری و تجریش", "شوراهای اسلامیشهرستان، استان و شورای عالی استانها", "شورای استان", "شورای اسلامی استان", "شورای بخش", "شورای تهران", "شورای روستا", "شورای روستا و بخش", "شورای روستای عشایری", "شورای شهر", "شورای شهر و روستا", "شورای شهر وروستا", "شورای شهرستان", "شورای شهرستان،استان و عالی استانها", "شورای عالی استان", "شورای عالی استانها", "شوراي عالي استانها", "کلیه شوراها", "مالی", "مالی سایر شهرداری ها", "مالی کلانشهر و مراکز استان"};
    List<tb_ShahrdariRouls> dataSubPostDB;
    Recycler_View_Adapter adapterSubPost;
    RecyclerView recyclerviewSubPost;
    List<Data> dataSubPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);fontSize.setFontSize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subposts);

        String KeySub = getIntent().getStringExtra("KeyPost");


        tb_ShahrdariroulsDATASource tb_shahrdariroulsDATASource = new tb_ShahrdariroulsDATASource(Activity_SubPosts.this);
        tb_shahrdariroulsDATASource.Open();

        dataSubPostDB = tb_shahrdariroulsDATASource.GetRecordBySubPosts(KeySub);
        recyclerviewSubPost= (RecyclerView) findViewById(R.id.recyclerviewSubPost);
        dataSubPost = fill_with_data();
        adapterSubPost = new Recycler_View_Adapter(dataSubPost, Activity_SubPosts.this);
        recyclerviewSubPost.setAdapter(adapterSubPost);
        recyclerviewSubPost.setLayoutManager(new LinearLayoutManager(Activity_SubPosts.this));

        tb_shahrdariroulsDATASource.Close();



/*

        ListView lstSubPosts = (ListView) findViewById(R.id.lstSubPosts);
        ArrayAdapter adapterSubPosts = new ArrayAdapter(Activity_SubPosts.this, R.layout.row_lst_sub, lstSubPostsArr) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.row_lst_sub, parent, false);
                TextView txtlstSubPosts = (TextView) convertView.findViewById(R.id.txtlstSubPosts);
                txtlstSubPosts.setText(lstSubPostsArr[position]);
                return convertView;
            }
        };

        lstSubPosts.setAdapter(adapterSubPosts);

        lstSubPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Activity_SubPosts.this, Activity_Show.class);
                //    intent.putExtra("KEY", IDPost);
                startActivity(intent);
            }
        });

*/

    }

    public List<Data> fill_with_data() {
        List<Data> dataSubPost = new ArrayList<>();
        for (int i = dataSubPostDB.size() - 1; i >= 0; i--) {
            dataSubPost.add(new Data(
                    dataSubPostDB.get(i).RoulsNAme == null ? "بدون عنوان" : dataSubPostDB.get(i).RoulsNAme +"",
                    dataSubPostDB.get(i).txtRouls == null ? "بدون متن" : dataSubPostDB.get(i).txtRouls +"",
                    dataSubPostDB.get(i).PK_Shahrdari));
        }

        return dataSubPost;
    }


}
