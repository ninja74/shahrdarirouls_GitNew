package com.app.shahrdarirouls;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shahrdarirouls.DataBase.DataSource.tb_FavoriteDATASource;
import com.app.shahrdarirouls.DataBase.DataSource.tb_ShahrdariroulsDATASource;
import com.app.shahrdarirouls.DataBase.MyDateBase;
import com.app.shahrdarirouls.DataBase.Table.tb_Favorite;
import com.app.shahrdarirouls.DataBase.Table.tb_ShahrdariRouls;

import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Show extends AppCompatActivity {

    String[] pdfNames = {"پی دی اف یک", "پی دی اف دو", "پی دی اف سه", "پی دی اف چهار", "پی دی اف پنج", "پی دی اف شش"};

    tb_ShahrdariRouls lstPI_SH_showPage;
    TextView RoulName, Made, Tabsare, SharhZero, SharhOne, SharhTwo, ShoraMashmol, txt;
    ImageView bookmark, share;

    AlertDialog pdf_selector, about_connect_us;
    DrawerLayout drawerLayout_sr1;
    NavigationView navigationview_sr1;
    int idpost;


    tb_FavoriteDATASource tb_favoriteDATASource;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);
        fontSize.setFontSize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_show);


        int NumID = 1;

        String a = getIntent().getStringExtra("KEY");
        NumID = Integer.parseInt(a);


        bookmark = (ImageView) findViewById(R.id.bookmark);
        share = (ImageView) findViewById(R.id.share);

        RoulName = (TextView) findViewById(R.id.RoulName);
        Made = (TextView) findViewById(R.id.Made);
        Tabsare = (TextView) findViewById(R.id.Tabsare);
        SharhOne = (TextView) findViewById(R.id.SharhOne);
        ShoraMashmol = (TextView) findViewById(R.id.ShoraMashmol);
        txt = (TextView) findViewById(R.id.txt);

        tb_ShahrdariroulsDATASource dataSource = new tb_ShahrdariroulsDATASource(this);
        dataSource.Open();
        lstPI_SH_showPage = dataSource.GetRecord(NumID);
        dataSource.Close();

        String replaced_MadeNum = lstPI_SH_showPage.MadeNum.replace(".", "_");
        String replaced_Tabsare = lstPI_SH_showPage.Tabsare.replace(".", "_");
        String[] splited_MadeNum = replaced_MadeNum.split("_");
        String[] splited_Tabsare = replaced_Tabsare.split("_");

        RoulName.setText("نام قانون: " + lstPI_SH_showPage.RoulsNAme + "");
        Made.setText("شماره ی ماده: " + splited_MadeNum[0] + "");
        Tabsare.setText("تبصره: " + splited_Tabsare[0] + "");
        SharhOne.setText("شرح یک: " + lstPI_SH_showPage.SharhOne + "");
        ShoraMashmol.setText("شورای مشمول: " + lstPI_SH_showPage.ShoraiMashmol + "");
        txt.setText("متن:" + "\n" + lstPI_SH_showPage.txtRouls + "");


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        RoulName.getText().toString() + "\n" +
                                Made.getText().toString() + "\n" +
                                Tabsare.getText().toString() + "\n" +
                                SharhOne.getText().toString() + "\n" +
                                ShoraMashmol.getText().toString() + "\n" +
                                txt.getText().toString()
                );
                startActivity(Intent.createChooser(shareIntent, "اشتراک گذاری در ..."));
            }
        });

        Toolbar toolbar_sr = (Toolbar) findViewById(R.id.toolbar_sr);
        drawerLayout_sr1 = (DrawerLayout) findViewById(R.id.drawerLayout_sr1);
        navigationview_sr1 = (NavigationView) findViewById(R.id.navigationview_sr1);

        setSupportActionBar(toolbar_sr);

        navigationview_sr1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Switch_NV_Items(id);
                drawerLayout_sr1.closeDrawer(Gravity.RIGHT);
                return true;
            }
        });


        tb_favoriteDATASource = new tb_FavoriteDATASource(Activity_Show.this);
        tb_favoriteDATASource.Open();
        if (tb_favoriteDATASource.ExistinFavorite(NumID)) {
            bookmark.setVisibility(View.GONE);
        } else {
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    tb_Favorite data = new tb_Favorite();
                    data.IdPost = lstPI_SH_showPage.PK_Shahrdari;

                    tb_FavoriteDATASource dataSource = new tb_FavoriteDATASource(Activity_Show.this);
                    dataSource.Open();
                    long id = dataSource.Add(data);

                    if (id == -1) {
                        Toast.makeText(Activity_Show.this, "با مشکل روبه رو شد!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Activity_Show.this, "به علاقه مندی ها اضافه شد", Toast.LENGTH_SHORT).show();
                        bookmark.setVisibility(View.GONE);
                    }
                    dataSource.Close();
                }
            });
        }
        tb_favoriteDATASource.Close();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_dr) {
            drawerLayout_sr1.openDrawer(Gravity.RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void AlertDialogPDF() {
//
//        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Show.this);
//        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
//        final ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
//        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Show.this, android.R.layout.simple_list_item_1, pdfNames);
//        listPDFSelector.setAdapter(arrayAdapterPDF);
//        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intentPDF = new Intent(Activity_Show.this, Activity_PDF_View.class);
//                switch (pdfNames[i]) {
//                    case "پی دی اف یک":
//                        intentPDF.putExtra("KEY", "first");
//                        break;
//                    case "پی دی اف دو":
//                        intentPDF.putExtra("KEY", "second");
//                        break;
//                    case "پی دی اف سه":
//                        intentPDF.putExtra("KEY", "third");
//                        break;
//                    case "پی دی اف چهار":
//                        intentPDF.putExtra("KEY", "fourth");
//                        break;
//                    case "پی دی اف پنج":
//                        intentPDF.putExtra("KEY", "fifth");
//                        break;
//                    case "پی دی اف شش":
//                        intentPDF.putExtra("KEY", "sixth");
//                        break;
//                }
//                startActivity(intentPDF);
//                pdf_selector.dismiss();
//            }
//        });
//
//        builder_pdfSelector.setView(linearLayout_pdfSelector);
//        pdf_selector = builder_pdfSelector.create();
//        pdf_selector.show();
//
//    }

    private void AlertDialogConnectUS() {
        AlertDialog.Builder builder_َACUS2 = new AlertDialog.Builder(Activity_Show.this);
        LinearLayout linearLayout_ACUS2 = (LinearLayout) getLayoutInflater().inflate(R.layout.about_connect_us, null, false);
        TextView txtView_ACUS2 = (TextView) linearLayout_ACUS2.findViewById(R.id.txtView_ACUS);
//        txtView_ACUS2.setText("این ارتباط با ماست");
        txtView_ACUS2.setText(Html.fromHtml(getString(R.string.StringAboutUs)));
        builder_َACUS2.setView(linearLayout_ACUS2);
        about_connect_us = builder_َACUS2.create();
        about_connect_us.getWindow().setBackgroundDrawableResource(R.color.transparent);
        about_connect_us.show();

    }

    private void AlertDialogAboutUs() {

        AlertDialog.Builder builder_َACUS1 = new AlertDialog.Builder(Activity_Show.this);
        LinearLayout linearLayout_ACUS1 = (LinearLayout) getLayoutInflater().inflate(R.layout.about_connect_us, null, false);
        TextView txtView_ACUS1 = (TextView) linearLayout_ACUS1.findViewById(R.id.txtView_ACUS);
        txtView_ACUS1.setText("این درباره ی ماست");
        builder_َACUS1.setView(linearLayout_ACUS1);
        about_connect_us = builder_َACUS1.create();
        about_connect_us.getWindow().setBackgroundDrawableResource(R.color.transparent);
        about_connect_us.show();


    }

    private void ImportExelData() {

        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Shahrdari.xls";

        File file = new File(directory_path);
        if (!file.exists()) {
            return;
        }


        tb_ShahrdariroulsDATASource tbJafarDataSource = new tb_ShahrdariroulsDATASource(Activity_Show.this);
        tbJafarDataSource.Open();

        ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), MyDateBase.DATABASE_NAME, true);
        // Import EXCEL FILE to SQLite
        excelToSQLite.importFromFile(directory_path, new ExcelToSQLite.ImportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String dbName) {
                Toast.makeText(Activity_Show.this, "کامل شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(Activity_Show.this, "با مشکل روبه رو شد!", Toast.LENGTH_SHORT).show();

            }
        });
        tbJafarDataSource.Close();

    }

    private void Switch_NV_Items(int id) {
        switch (id) {


            case R.id.Search:
                Intent intentSerach = new Intent(Activity_Show.this, Activity_Search_SR.class);
                startActivity(intentSerach);
                break;
//            case R.id.PDFs:
//                AlertDialogPDF();

//                break;
            case R.id.AddXLS:
                ImportExelData();

                break;
            case R.id.Setting:
                Intent intentSetting = new Intent(Activity_Show.this, Activity_Setting.class);
                startActivity(intentSetting);

                break;
            case R.id.Favorit:
                Intent intent = new Intent(Activity_Show.this, Activity_Favorite.class);
                startActivity(intent);

                break;
            case R.id.AboutUs:
                AlertDialogAboutUs();

                break;
            case R.id.ConnectUS:
                AlertDialogConnectUS();

                break;
            case R.id.Exit:
                finish();
                break;


        }
    }

}
