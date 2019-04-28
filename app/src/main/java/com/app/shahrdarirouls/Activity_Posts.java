package com.app.shahrdarirouls;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shahrdarirouls.DataBase.DatabaseManagement;
import com.app.shahrdarirouls.DataBase.MyDateBase;

import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Posts extends AppCompatActivity {
    private static final int Time_Between_Two_Back = 2000;
    private long TimeBackPressed;
    //    private MyDateBase db;
    private DatabaseManagement db;

    String[] lstNameMain = {"مالی", "شهرداری و املاک", "شورا", "سایر قوانین", "استخدامی", "جستجو"};


    String[] subListMali = {"بخشنامه های بودجه", "دستورالعملهای مالی", "قوانین مالی", "نصاب معاملات"};
    String[] subListAmlak = {"دستورالعملهای شهرداری", "قوانین شهرداری"};
    String[] subListShora = {"دستورالعملهای شوراها", "قوانین شوراها"};


    String[] pdfNameMaliBodje = {"بخشنامه بودجه سال 1395 شهرداری های کشور", "بخشنامه بودجه سال 1396 شهرداری های کشور", "بخشنامه بودجه سال 1397 شهرداري هاي كشور", "بخشنامه بودجه سال 1398 شهرداری های کشور", "دستورالعمل تهیه و تدوین بودحه شهرداری ها 1399به بعد"};
    String[] pdfNameMaliDastor = {"آئین نامه حق جلسه اعضا کمیسیون ها", "آئین نامه حق جلسه به شوراهای اسلامی", "دستورالعمل  نحوه واگذاری ، ميزان و واریز تنخواه گردان", "دستورالعمل تحریر دفاتر قانونی در شهرداری، سازمانها و شرکت های وابسته", "دستورالعمل خزانه داری در شهرداری ها", "دستورالعمل شناسایی و پلاک کوبی اموال در شهرداری ها", "دستورالعمل کارگروه استانی نظارت بر امور حسابرسی شهرداری ها", "دستورالعمل نحوه ایجاد، تبدیل، نگهداری و امحاء اسناد و مدارک مالی شهرداری", "دستورالعمل نحوه هزینه کرد مواد 16 و 17 بودجه شهرداری", "دستورالعمل نحوه ی استفاده از خدمات مؤسسات حسابرسی", "شیوه نامه اجرایی کمیته تخصیص اعتبار"};
    String[] pdfNameMaliRules = {"آیین نامه اجرایی نحوه وضع و وصول عوارض", "آئین نامه معاملاتی شهرداری ها به استثنا تهران و مرکز استان", "آئین نامه معاملاتی کلانشهرها و مراکز استان", "حقوق و مزایای شهرداران"};
    String[] pdfNameMaliNasab = {"نصاب معاملات94", "نصاب معاملات95", "نصاب معاملات96", "نصاب معاملات97"};


    String[] pdfNamesAmlakDastor = {"دستورالعمل اجرایی مشاغل و اصناف مزاحم شهری", "دستورالعمل واگذاری اتوبوس و بهره\u200Cبرداری \u200Cاز خطوط اتوبوسرانی شهری و حومه شهری"};
    String[] pdfNamesAmlakRules = {"تعاریف محدوده و حریم شهر و نحوه تعیین آنها", "تعریف و ضوابط تقسیمات کشوری", "قانون شهرداری مصوب 1344 با اصلاحات و الحاقات بعدی آن", "قانون نوسازی و عمران شهری و آئین نامه ای مربوط"};


    String[] pdfNamesShoraDastor = {"آیین\u200Cنامه نامگذاری شهرها، خیابان\u200Cها، اماکن و مؤسسات عمومی", "آئین نامه حق جلسه به شوراهای اسلامی", "دستورالعمل ترکیب کمیسیون های شوراها", "دستورالعمل شرح وظیفه هیأت رییسه و سخنگوی شوراهای", "دستورالعمل نحوره رسیدگی به طرح ها  ولوایح", "دستورالعمل نحوه اداره جلسات شوراها", "دستورالعمل نحوه انجام مراسم تحلیف شوراهای اسلامی کشور", "دستورالعمل نحوه ماموریت شوراها", "سفر شوراهای محلی به خارج از کشور"};
    String[] pdfNamesShoraRules = {"آئین نامه های اجرایی شوراهای اسلامی", "آئین نامه های داخلی شوراهای شهر و شهرستان و استان و عالی لستانها", "قانون تشکیلات شوراهای اسلامی کشوری و انتخابات شوراهای مزبور مصوب 29 تیر 1365 با اصلاحیه های بعدی", "قانون تشکیلات ووظایف شوراها و انتخاب شهراران به همراه آئین نامه ها و دستورالعملهای", "قانون تشكيلات، وظايف و انتخابات شوراهاي اسلامي كشور و انتخاب شهرداران مصوب 1375"};

    String[] pdfNamesAnotherRules = {"آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات","آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن","قانون احکام دائمی توسعه کشور","قانون برنامه ششم توسعه","قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15","قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور","قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی","قانون مبارزه با پولشویی و آیین نامه اجرایی","قانون هوای پاک"};

    AlertDialog catSubList, pdf_selector, about_connect_us;
    DrawerLayout drawerLayout_sr;
    NavigationView navigationview_sr;


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


    //pdf AlertDialogs ***********************************************

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
                        AlertDialogPDFMaliBodje();
                        break;
                    case "دستورالعملهای مالی":
                        AlertDialogPDFMaliDastor();
                        break;
                    case "قوانین مالی":
                        AlertDialogPDFMaliRules();
                        break;
                    case "نصاب معاملات":
                        AlertDialogPDFMaliNasab();
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
                        AlertDialogPDFAmlakDastor();
                        break;
                    case "قوانین شهرداری":
                        AlertDialogPDFAmlakRules();
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
                        AlertDialogPDFShoraDastor();
                        break;
                    case "قوانین شوراها":
                        AlertDialogPDFShoraRules();
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

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        final ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesAnotherRules);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNamesAnotherRules[i]) {
                    case "آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات":
                        intentPDF.putExtra("KeyPDF", "آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات");
                        break;
                    case "آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن":
                        intentPDF.putExtra("KeyPDF", "آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن");
                        break;
                    case "قانون احکام دائمی توسعه کشور":
                        intentPDF.putExtra("KeyPDF", "قانون احکام دائمی توسعه کشور");
                        break;
                    case "قانون برنامه ششم توسعه":
                        intentPDF.putExtra("KeyPDF", "قانون برنامه ششم توسعه");
                        break;
                    case "قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15":
                        intentPDF.putExtra("KeyPDF", "قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15");
                        break;
                    case "قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور":
                        intentPDF.putExtra("KeyPDF", "قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور");
                        break;
                    case "قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی":
                        intentPDF.putExtra("KeyPDF", "قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی");
                        break;
                    case "قانون مبارزه با پولشویی و آیین نامه اجرایی":
                        intentPDF.putExtra("KeyPDF", "قانون مبارزه با پولشویی و آیین نامه اجرایی");
                        break;
                    case "قانون هوای پاک":
                        intentPDF.putExtra("KeyPDF", "قانون هوای پاک");
                        break;

                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }



    private void AlertDialogPDFMaliBodje() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNameMaliBodje);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNameMaliBodje[i]) {
                    case "بخشنامه بودجه سال 1395 شهرداری های کشور":
                        intentPDF.putExtra("KeyPDF", "بخشنامه بودجه سال 1395 شهرداری های کشور");
                        break;
                    case "بخشنامه بودجه سال 1396 شهرداری های کشور":
                        intentPDF.putExtra("KeyPDF", "بخشنامه بودجه سال 1396 شهرداری های کشور");
                        break;
                    case "بخشنامه بودجه سال 1397 شهرداري هاي كشور":
                        intentPDF.putExtra("KeyPDF", "بخشنامه بودجه سال 1397 شهرداري هاي كشور");
                        break;
                    case "بخشنامه بودجه سال 1398 شهرداری های کشور":
                        intentPDF.putExtra("KeyPDF", "بخشنامه بودجه سال 1398 شهرداری های کشور");
                        break;
                    case "دستورالعمل تهیه و تدوین بودحه شهرداری ها 1399به بعد":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل تهیه و تدوین بودحه شهرداری ها 1399به بعد");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }

    private void AlertDialogPDFMaliDastor() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNameMaliDastor);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNameMaliDastor[i]) {
                    case "آئین نامه حق جلسه اعضا کمیسیون ها":
                        intentPDF.putExtra("KeyPDF", "آئین نامه حق جلسه اعضا کمیسیون ها");
                        break;
                    case "آئین نامه حق جلسه به شوراهای اسلامی":
                        intentPDF.putExtra("KeyPDF", "آئین نامه حق جلسه به شوراهای اسلامی");
                        break;
                    case "دستورالعمل  نحوه واگذاری ، ميزان و واریز تنخواه گردان":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل  نحوه واگذاری ، ميزان و واریز تنخواه گردان");
                        break;
                    case "دستورالعمل تحریر دفاتر قانونی در شهرداری، سازمانها و شرکت های وابسته":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل تحریر دفاتر قانونی در شهرداری، سازمانها و شرکت های وابسته");
                        break;
                    case "دستورالعمل خزانه داری در شهرداری ها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل خزانه داری در شهرداری ها");
                        break;
                    case "دستورالعمل شناسایی و پلاک کوبی اموال در شهرداری ها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل شناسایی و پلاک کوبی اموال در شهرداری ها");
                        break;
                    case "دستورالعمل کارگروه استانی نظارت بر امور حسابرسی شهرداری ها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل کارگروه استانی نظارت بر امور حسابرسی شهرداری ها");
                        break;
                    case "دستورالعمل نحوه ایجاد، تبدیل، نگهداری و امحاء اسناد و مدارک مالی شهرداری":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه ایجاد، تبدیل، نگهداری و امحاء اسناد و مدارک مالی شهرداری");
                        break;
                    case "دستورالعمل نحوه هزینه کرد مواد 16 و 17 بودجه شهرداری":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه هزینه کرد مواد 16 و 17 بودجه شهرداری");
                        break;
                    case "دستورالعمل نحوه ی استفاده از خدمات مؤسسات حسابرسی":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه ی استفاده از خدمات مؤسسات حسابرسی");
                        break;
                    case "شیوه نامه اجرایی کمیته تخصیص اعتبار":
                        intentPDF.putExtra("KeyPDF", "شیوه نامه اجرایی کمیته تخصیص اعتبار");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }

    private void AlertDialogPDFMaliRules() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNameMaliRules);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNameMaliRules[i]) {
                    case "آیین نامه اجرایی نحوه وضع و وصول عوارض":
                        intentPDF.putExtra("KeyPDF", "آیین نامه اجرایی نحوه وضع و وصول عوارض");
                        break;
                    case "آئین نامه معاملاتی شهرداری ها به استثنا تهران و مرکز استان":
                        intentPDF.putExtra("KeyPDF", "آئین نامه معاملاتی شهرداری ها به استثنا تهران و مرکز استان");
                        break;
                    case "آئین نامه معاملاتی کلانشهرها و مراکز استان":
                        intentPDF.putExtra("KeyPDF", "آئین نامه معاملاتی کلانشهرها و مراکز استان");
                        break;
                    case "حقوق و مزایای شهرداران":
                        intentPDF.putExtra("KeyPDF", "حقوق و مزایای شهرداران");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }

    private void AlertDialogPDFMaliNasab() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNameMaliNasab);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNameMaliNasab[i]) {
                    case "نصاب معاملات94":
                        intentPDF.putExtra("KeyPDF", "نصاب معاملات94");
                        break;
                    case "نصاب معاملات95":
                        intentPDF.putExtra("KeyPDF", "نصاب معاملات95");
                        break;
                    case "نصاب معاملات96":
                        intentPDF.putExtra("KeyPDF", "نصاب معاملات96");
                        break;
                    case "نصاب معاملات97":
                        intentPDF.putExtra("KeyPDF", "نصاب معاملات97");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }


    private void AlertDialogPDFAmlakDastor() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesAmlakDastor);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNamesAmlakDastor[i]) {
                    case "دستورالعمل اجرایی مشاغل و اصناف مزاحم شهری":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل اجرایی مشاغل و اصناف مزاحم شهری");
                        break;
                    case "دستورالعمل واگذاری اتوبوس و بهره\u200Cبرداری \u200Cاز خطوط اتوبوسرانی شهری و حومه شهری":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل واگذاری اتوبوس و بهره\u200Cبرداری \u200Cاز خطوط اتوبوسرانی شهری و حومه شهری");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }

    private void AlertDialogPDFAmlakRules() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesAmlakRules);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNamesAmlakRules[i]) {
                    case "تعاریف محدوده و حریم شهر و نحوه تعیین آنها":
                        intentPDF.putExtra("KeyPDF", "تعاریف محدوده و حریم شهر و نحوه تعیین آنها");
                        break;
                    case "تعریف و ضوابط تقسیمات کشوری":
                        intentPDF.putExtra("KeyPDF", "تعریف و ضوابط تقسیمات کشوری");
                        break;
                    case "قانون شهرداری مصوب 1344 با اصلاحات و الحاقات بعدی آن":
                        intentPDF.putExtra("KeyPDF", "قانون شهرداری مصوب 1344 با اصلاحات و الحاقات بعدی آن");
                        break;
                    case "قانون نوسازی و عمران شهری و آئین نامه ای مربوط":
                        intentPDF.putExtra("KeyPDF", "قانون نوسازی و عمران شهری و آئین نامه ای مربوط");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }


    private void AlertDialogPDFShoraDastor() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesShoraDastor);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNamesShoraDastor[i]) {
                    case "آیین\u200Cنامه نامگذاری شهرها، خیابان\u200Cها، اماکن و مؤسسات عمومی":
                        intentPDF.putExtra("KeyPDF", "آیین\u200Cنامه نامگذاری شهرها، خیابان\u200Cها، اماکن و مؤسسات عمومی");
                        break;
                    case "آئین نامه حق جلسه به شوراهای اسلامی":
                        intentPDF.putExtra("KeyPDF", "آئین نامه حق جلسه به شوراهای اسلامی");
                        break;
                    case "دستورالعمل ترکیب کمیسیون های شوراها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل ترکیب کمیسیون های شوراها");
                        break;
                    case "دستورالعمل شرح وظیفه هیأت رییسه و سخنگوی شوراهای":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل شرح وظیفه هیأت رییسه و سخنگوی شوراهای");
                        break;
                    case "دستورالعمل نحوره رسیدگی به طرح ها  ولوایح":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوره رسیدگی به طرح ها  ولوایح");
                        break;
                    case "دستورالعمل نحوه اداره جلسات شوراها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه اداره جلسات شوراها");
                        break;
                    case "دستورالعمل نحوه انجام مراسم تحلیف شوراهای اسلامی کشور":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه انجام مراسم تحلیف شوراهای اسلامی کشور");
                        break;
                    case "دستورالعمل نحوه ماموریت شوراها":
                        intentPDF.putExtra("KeyPDF", "دستورالعمل نحوه ماموریت شوراها");
                        break;
                    case "سفر شوراهای محلی به خارج از کشور":
                        intentPDF.putExtra("KeyPDF", "سفر شوراهای محلی به خارج از کشور");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }

    private void AlertDialogPDFShoraRules() {

        AlertDialog.Builder builder_pdfSelector = new AlertDialog.Builder(Activity_Posts.this);
        LinearLayout linearLayout_pdfSelector = (LinearLayout) getLayoutInflater().inflate(R.layout.pdf_selector, null, false);
        ListView listPDFSelector = linearLayout_pdfSelector.findViewById(R.id.listPDFSelector);
        ArrayAdapter arrayAdapterPDF = new ArrayAdapter(Activity_Posts.this, android.R.layout.simple_list_item_1, pdfNamesShoraRules);
        listPDFSelector.setAdapter(arrayAdapterPDF);
        listPDFSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentPDF = new Intent(Activity_Posts.this, Activity_PDF_View.class);
                switch (pdfNamesShoraRules[i]) {
                    case "آئین نامه های اجرایی شوراهای اسلامی":
                        intentPDF.putExtra("KeyPDF", "آئین نامه های اجرایی شوراهای اسلامی");
                        break;
                    case "آئین نامه های داخلی شوراهای شهر و شهرستان و استان و عالی لستانها":
                        intentPDF.putExtra("KeyPDF", "آئین نامه های داخلی شوراهای شهر و شهرستان و استان و عالی لستانها");
                        break;
                    case "قانون تشکیلات شوراهای اسلامی کشوری و انتخابات شوراهای مزبور مصوب 29 تیر 1365 با اصلاحیه های بعدی":
                        intentPDF.putExtra("KeyPDF", "قانون تشکیلات شوراهای اسلامی کشوری و انتخابات شوراهای مزبور مصوب 29 تیر 1365 با اصلاحیه های بعدی");
                        break;
                    case "قانون تشکیلات ووظایف شوراها و انتخاب شهراران به همراه آئین نامه ها و دستورالعملهای":
                        intentPDF.putExtra("KeyPDF", "قانون تشکیلات ووظایف شوراها و انتخاب شهراران به همراه آئین نامه ها و دستورالعملهای");
                        break;
                    case "قانون تشكيلات، وظايف و انتخابات شوراهاي اسلامي كشور و انتخاب شهرداران مصوب 1375":
                        intentPDF.putExtra("KeyPDF", "قانون تشكيلات، وظايف و انتخابات شوراهاي اسلامي كشور و انتخاب شهرداران مصوب 1375");
                        break;
                }
                startActivity(intentPDF);
                pdf_selector.dismiss();
            }
        });

        builder_pdfSelector.setView(linearLayout_pdfSelector);
        pdf_selector = builder_pdfSelector.create();
        pdf_selector.show();

    }



    //pdf AlertDialogs ***********************************************


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
