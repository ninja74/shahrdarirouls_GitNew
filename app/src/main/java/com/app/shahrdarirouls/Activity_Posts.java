package com.app.shahrdarirouls;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.shahrdarirouls.DataBase.DatabaseManagement;
import com.app.shahrdarirouls.DataBase.MyDateBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Posts extends AppCompatActivity {
    private static final int Time_Between_Two_Back = 2000;
    private long TimeBackPressed;
    //    private MyDateBase db;
    private DatabaseManagement db;

    String[] lstNameMain = {"مالی", "شهرداری و املاک", "شورا", "سایر قوانین", "استخدامی", "جستجو"};

    String[] subListMali;
    String[] subListAmlak;
    String[] subListShora;
    String[] subListAnotherRules;

    String[] pdfNameMaliBodje;
    String[] pdfNameMaliDastor;
    String[] pdfNameMaliRules;
    String[] pdfNameMaliNasab;

    String[] pdfNamesAmlakDastor;
    String[] pdfNamesAmlakRules;

    String[] pdfNamesShoraDastor;
    String[] pdfNamesShoraRules;

    String[] pdfNamesAnotherRules;

    AlertDialog catSubList, pdf_selector, about_connect_us, alertlogin, alertAnotherRules;
    DrawerLayout drawerLayout_sr;
    NavigationView navigationview_sr;

    String cod = "";
    SharedPreferences preferences;


    AlertDialog adOpenPDF;


    private static final String TAG = MainActivityTest.class.getSimpleName();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);
        fontSize.setFontSize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


        String url = "http://www.azmoonshahri.ir/meysam/shahrDari.php";

        StringRequest requestCoupons = new StringRequest(Request.Method.GET, url, new OkResListener(), new ErrListener());
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Posts.this);
        requestQueue.add(requestCoupons);


        if (!(Build.VERSION.SDK_INT < 23)) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        db = new DatabaseManagement(Activity_Posts.this);

        Toolbar toolbar_sr = (Toolbar) findViewById(R.id.toolbar_sr);
        drawerLayout_sr = (DrawerLayout) findViewById(R.id.drawerLayout_sr);
        navigationview_sr = (NavigationView) findViewById(R.id.navigationview_sr);


        setSupportActionBar(toolbar_sr);

        final GridView lstGVMain = (GridView) findViewById(R.id.lstGVMain);
        CustomGridView customGridView = new CustomGridView(Activity_Posts.this, lstNameMain);
        lstGVMain.setAdapter(customGridView);


        lstGVMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AlertDialogSubListMali();
                        break;

                    case 1:
                        AlertDialogSubListAmlak();
                        break;

                    case 2:
                        AlertDialogSubListShora();
                        break;

                    case 3:
                        AlertDialogSubListAnotherRuls();
                        break;

                    case 4:
                        Toast.makeText(Activity_Posts.this, "درحال حاضر کتابی برای این مجموعه ارائه نشده است", Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
                        Intent intentSerach = new Intent(Activity_Posts.this, Activity_Search_SR.class);
                        startActivity(intentSerach);
                        break;

                }
            }
        });


        navigationview_sr.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Switch_NV_Items(id);
                drawerLayout_sr.closeDrawer(Gravity.RIGHT);
                return true;
            }
        });

        preferences = getSharedPreferences("shahrdariroulS", 0);
        boolean login = preferences.getBoolean("logIn", false);
        if (!login)
            AlertDialogLogIn();


    }

    private void AlertDialogLogIn() {

        AlertDialog.Builder builder_login = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_login = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_login, null, false);
        final EditText edtCod = linearLayout_login.findViewById(R.id.edtCod);
        Button btnCod = linearLayout_login.findViewById(R.id.btnCod);

        btnCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCod = edtCod.getText().toString();
                if (userCod.equals(cod)) {
                    Toast.makeText(Activity_Posts.this, "خوش آمدید", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("logIn", true);
                    editor.apply();
                    alertlogin.dismiss();
                } else {
                    Toast.makeText(Activity_Posts.this, "کد وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder_login.setView(linearLayout_login);
        alertlogin = builder_login.create();
        alertlogin.show();
        alertlogin.setCancelable(false);
        alertlogin.setCanceledOnTouchOutside(false);

    }

    // get String[]
    private class OkResListener implements Response.Listener {
        @Override
        public void onResponse(Object response) {


            try {
                JSONObject object = new JSONObject((String) response);
                cod = object.getString("cod");
                subListMali = object.getString("subListMali").split(",");
                subListAmlak = object.getString("subListAmlak").split(",");
                subListAnotherRules = object.getString("subListAnotherRules").split(",");
                subListShora = object.getString("subListShora").split(",");
                pdfNameMaliBodje = object.getString("pdfNameMaliBodje").split(",");
                pdfNameMaliDastor = object.getString("pdfNameMaliDastor").split(",");
                pdfNameMaliRules = object.getString("pdfNameMaliRules").split(",");
                pdfNameMaliNasab = object.getString("pdfNameMaliNasab").split(",");
                pdfNamesAmlakDastor = object.getString("pdfNamesAmlakDastor").split(",");
                pdfNamesAmlakRules = object.getString("pdfNamesAmlakRules").split(",");
                pdfNamesShoraDastor = object.getString("pdfNamesShoraDastor").split(",");
                pdfNamesShoraRules = object.getString("pdfNamesShoraRules").split(",");
                pdfNamesAnotherRules = object.getString("pdfNamesAnotherRules").split(",");

            } catch (JSONException e) {
                e.printStackTrace();


            }
        }
    }

    private class ErrListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {


        }
        if (id == R.id.menu_dr) {
            drawerLayout_sr.openDrawer(Gravity.RIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alertOpenPDF(final String[] stringArr) {

        AlertDialog.Builder builderOPDF = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayoutOPDF = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_open_pdf, null, false);
        ListView lstOpenPDF = linearLayoutOPDF.findViewById(R.id.lstOpenPDF);
        ArrayAdapter arrayAdapterOPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, stringArr);
        lstOpenPDF.setAdapter(arrayAdapterOPDF);
        lstOpenPDF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkPDF(stringArr[i]);
                adOpenPDF.dismiss();
            }
        });

        builderOPDF.setView(linearLayoutOPDF);
        adOpenPDF = builderOPDF.create();
        adOpenPDF.show();


    }

    //sub lists
    private void AlertDialogSubListMali() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, subListMali);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (subListMali[i]) {
                    case "بخشنامه های بودجه":
                        alertOpenPDF(pdfNameMaliBodje);
                        break;
                    case "دستورالعملهای مالی":
                        alertOpenPDF(pdfNameMaliDastor);
                        break;
                    case "قوانین مالی":
                        alertOpenPDF(pdfNameMaliRules);
                        break;
                    case "نصاب معاملات":
                        alertOpenPDF(pdfNameMaliNasab);
                        break;
                }
                catSubList.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        catSubList = builder_pdfSelector.create();
        catSubList.show();

    }

    private void AlertDialogSubListAmlak() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, subListAmlak);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (subListAmlak[i]) {
                    case "دستورالعملهای شهرداری":
                        alertOpenPDF(pdfNamesAmlakDastor);
                        break;
                    case "قوانین شهرداری":
                        alertOpenPDF(pdfNamesAmlakRules);
                        break;
                }
                catSubList.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        catSubList = builder_pdfSelector.create();
        catSubList.show();

    }

    private void AlertDialogSubListShora() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, subListShora);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (subListShora[i]) {
                    case "دستورالعملهای شوراها":
                        alertOpenPDF(pdfNamesShoraDastor);
                        break;
                    case "قوانین شوراها":
                        alertOpenPDF(pdfNamesShoraRules);
                        break;
                }
                catSubList.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        catSubList = builder_pdfSelector.create();
        catSubList.show();

    }

    private void AlertDialogSubListAnotherRuls() {
//        alertOpenPDF(pdfNamesAnotherRules);


        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, subListAnotherRules);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (subListAnotherRules[i]) {
                    case "سایر قوانین":
                        alertOpenPDF(pdfNamesAnotherRules);
                        break;
                }
                alertAnotherRules.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        alertAnotherRules = builder_pdfSelector.create();
        alertAnotherRules.show();


//
//        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
//        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
//        final ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
//        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesAnotherRules);
//        listPDFSelector.setAdapter(arrayAdapterPDF);
//        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                checkPDF(pdfNamesAnotherRules[i]);
//
////                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
////                switch (pdfNamesAnotherRules[i]) {
////                    case "آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات":
////                        intentPDF.putExtra("KeyPDF", "آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات");
////                        break;
////                    case "آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن":
////                        intentPDF.putExtra("KeyPDF", "آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن");
////                        break;
////                    case "قانون احکام دائمی توسعه کشور":
////                        intentPDF.putExtra("KeyPDF", "قانون احکام دائمی توسعه کشور");
////                        break;
////                    case "قانون برنامه ششم توسعه":
////                        intentPDF.putExtra("KeyPDF", "قانون برنامه ششم توسعه");
////                        break;
////                    case "قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15":
////                        intentPDF.putExtra("KeyPDF", "قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15");
////                        break;
////                    case "قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور":
////                        intentPDF.putExtra("KeyPDF", "قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور");
////                        break;
////                    case "قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی":
////                        intentPDF.putExtra("KeyPDF", "قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی");
////                        break;
////                    case "قانون مبارزه با پولشویی و آیین نامه اجرایی":
////                        intentPDF.putExtra("KeyPDF", "قانون مبارزه با پولشویی و آیین نامه اجرایی");
////                        break;
////                    case "قانون هوای پاک":
////                        intentPDF.putExtra("KeyPDF", "قانون هوای پاک");
////                        break;
////
////                }
////                startActivity(intentPDF);
//                pdf_selector.dismiss();
//            }
//        });
//
//        builder_pdfSelector.setView(linearLayout_pdfSelector);
//        pdf_selector = builder_pdfSelector.create();
//        pdf_selector.show();

    }


    private void AlertDialogConnectUS() {
        AlertDialog.Builder builder_َACUS2 = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_ACUS2 = (LinearLayout) getLayoutInflater().inflate(R.layout.about_connect_us, null, false);
        TextView txtView_ACUS2 = (TextView) linearLayout_ACUS2.findViewById(R.id.txtView_ACUS);
        txtView_ACUS2.setText(Html.fromHtml(getString(R.string.StringAboutUs)));
        builder_َACUS2.setView(linearLayout_ACUS2);
        about_connect_us = builder_َACUS2.create();
        about_connect_us.show();

    }

    private void AlertDialogAboutUs() {

        AlertDialog.Builder builder_َACUS1 = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_ACUS1 = (LinearLayout) getLayoutInflater().inflate(R.layout.about_connect_us, null, false);
        TextView txtView_ACUS1 = (TextView) linearLayout_ACUS1.findViewById(R.id.txtView_ACUS);
        txtView_ACUS1.setText("این درباره ی ماست");
        builder_َACUS1.setView(linearLayout_ACUS1);
        about_connect_us = builder_َACUS1.create();
        about_connect_us.show();
    }

    private void ImportExelData() {


        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/dastan123.xls";

        File file = new File(directory_path);
        if (!file.exists()) {
            return;
        }


        ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), MyDateBase.DATABASE_NAME, true);
        // Import EXCEL FILE to SQLite
        excelToSQLite.importFromFile(directory_path, new ExcelToSQLite.ImportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String dbName) {
                Toast.makeText(Activity_Posts.this, "کامل شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(Activity_Posts.this, "با مشکل روبه رو شد!", Toast.LENGTH_SHORT).show();
                Log.d("mamad", e.getMessage());
            }
        });

    }

    private void Switch_NV_Items(int id) {
        switch (id) {


            case R.id.Search:
                Intent intentSerach = new Intent(Activity_Posts.this, Activity_Search_SR.class);
                startActivity(intentSerach);
                break;
//            case R.id.PDFs:
//                AlertDialogPDF();

//                break;
            case R.id.AddXLS:
                ImportExelData();

                break;
            case R.id.Setting:
                Intent intentSetting = new Intent(Activity_Posts.this, Activity_Setting.class);
                startActivity(intentSetting);

                break;
            case R.id.Favorit:
                Intent intent = new Intent(Activity_Posts.this, Activity_Favorite.class);
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

    private boolean getFileNames(String namePdf) {

        File f = new File(Environment.getExternalStorageDirectory().getPath() + "/ShahrdariPDF");
        f.mkdirs();
        File[] file = f.listFiles();

        if (Arrays.toString(file).contains(namePdf))
            return true;
        else
            return false;

    }

    // check pdf and open it
    private void checkPDF(String s) {

        if (getFileNames(s)) {
            Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
            intentPDF.putExtra("KeyPDF", s + ".pdf");
            startActivity(intentPDF);
            adOpenPDF.dismiss();
        } else {
            new Activity_Posts.DownloadFile().execute("http://www.azmoonshahri.ir/meysam/" + s + ".pdf", s + ".pdf");
            adOpenPDF.dismiss();
        }

    }

    // Download File *************************************************************************************************
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(Activity_Posts.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String fileName = f_url[1];  // -> maven.pdf
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

//                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


                //External directory path to save file
                folder = Environment.getExternalStorageDirectory().getPath() + "/ShahrdariPDF";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File pdfFile = new File(folder, fileName);

                try {
                    pdfFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(pdfFile);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "false";
        }


        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
            intentPDF.putExtra("KeyPDF", message);
            startActivity(intentPDF);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout_sr.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout_sr.closeDrawer(Gravity.RIGHT);
        } else {
            if (TimeBackPressed + Time_Between_Two_Back > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "برای خروج دوباره کلیک کنید", Toast.LENGTH_SHORT).show();
            }
            TimeBackPressed = System.currentTimeMillis();

            //   super.onBackPressed();
        }
    }

}
