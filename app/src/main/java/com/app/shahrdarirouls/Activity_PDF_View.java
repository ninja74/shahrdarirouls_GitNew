package com.app.shahrdarirouls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joanzapata.pdfview.PDFView;

public class Activity_PDF_View extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        PDFView pdfview = (PDFView) findViewById(R.id.pdfview);

        String keyPDF = getIntent().getStringExtra("KeyPDF");

        switch (keyPDF) {
            //Mali Bodje ***********************************************
            case "بخشنامه بودجه سال 1395 شهرداری های کشور":
                pdfview.fromAsset("بخشنامه بودجه سال 1395 شهرداری های کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "بخشنامه بودجه سال 1396 شهرداری های کشور":
                pdfview.fromAsset("بخشنامه بودجه سال 1396 شهرداری های کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "بخشنامه بودجه سال 1397 شهرداري هاي كشور":
                pdfview.fromAsset("بخشنامه بودجه سال 1397 شهرداري هاي كشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "بخشنامه بودجه سال 1398 شهرداری های کشور":
                pdfview.fromAsset("بخشنامه بودجه سال 1398 شهرداری های کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();

            case "دستورالعمل تهیه و تدوین بودحه شهرداری ها 1399به بعد":
                pdfview.fromAsset("دستورالعمل تهیه و تدوین بودحه شهرداری ها 1399به بعد.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;

            //End Mali Bodje ***********************************************

            //Mali Dastor ***********************************************

            case "آئین نامه حق جلسه اعضا کمیسیون ها":
                pdfview.fromAsset("آئین نامه حق جلسه اعضا کمیسیون ها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "آئین نامه حق جلسه به شوراهای اسلامی":
                pdfview.fromAsset("آئین نامه حق جلسه به شوراهای اسلامی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل  نحوه واگذاری ، ميزان و واریز تنخواه گردان":
                pdfview.fromAsset("دستورالعمل  نحوه واگذاری ، ميزان و واریز تنخواه گردان.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل تحریر دفاتر قانونی در شهرداری، سازمانها و شرکت های وابسته":
                pdfview.fromAsset("دستورالعمل تحریر دفاتر قانونی در شهرداری، سازمانها و شرکت های وابسته.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل خزانه داری در شهرداری ها":
                pdfview.fromAsset("دستورالعمل خزانه داری در شهرداری ها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل شناسایی و پلاک کوبی اموال در شهرداری ها":
                pdfview.fromAsset("دستورالعمل شناسایی و پلاک کوبی اموال در شهرداری ها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل کارگروه استانی نظارت بر امور حسابرسی شهرداری ها":
                pdfview.fromAsset("دستورالعمل کارگروه استانی نظارت بر امور حسابرسی شهرداری ها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه ایجاد، تبدیل، نگهداری و امحاء اسناد و مدارک مالی شهرداری":
                pdfview.fromAsset("دستورالعمل نحوه ایجاد، تبدیل، نگهداری و امحاء اسناد و مدارک مالی شهرداری.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه هزینه کرد مواد 16 و 17 بودجه شهرداری":
                pdfview.fromAsset("دستورالعمل نحوه هزینه کرد مواد 16 و 17 بودجه شهرداری.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه ی استفاده از خدمات مؤسسات حسابرسی":
                pdfview.fromAsset("دستورالعمل نحوه ی استفاده از خدمات مؤسسات حسابرسی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "شیوه نامه اجرایی کمیته تخصیص اعتبار":
                pdfview.fromAsset("شیوه نامه اجرایی کمیته تخصیص اعتبار.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Mali Dastor ***********************************************

            //Mali Rules ****************************************************
            case "آیین نامه اجرایی نحوه وضع و وصول عوارض":
                pdfview.fromAsset("آیین نامه اجرایی نحوه وضع و وصول عوارض.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "آئین نامه معاملاتی شهرداری ها به استثنا تهران و مرکز استان":
                pdfview.fromAsset("آئین نامه معاملاتی شهرداری ها به استثنا تهران و مرکز استان.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "آئین نامه معاملاتی کلانشهرها و مراکز استان":
                pdfview.fromAsset("آئین نامه معاملاتی کلانشهرها و مراکز استان.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "حقوق و مزایای شهرداران":
                pdfview.fromAsset("حقوق و مزایای شهرداران.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Mali Rules ***********************************************

            //Mali Nasab ***************************************************
            case "نصاب معاملات94":
                pdfview.fromAsset("نصاب معاملات94.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "نصاب معاملات95":
                pdfview.fromAsset("نصاب معاملات95.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "نصاب معاملات96":
                pdfview.fromAsset("نصاب معاملات96.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "نصاب معاملات97":
                pdfview.fromAsset("نصاب معاملات97.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Mali Nasab **********************************************

            //Amlak Dastor ************************************************
            case "دستورالعمل اجرایی مشاغل و اصناف مزاحم شهری":
                pdfview.fromAsset("دستورالعمل اجرایی مشاغل و اصناف مزاحم شهری.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل واگذاری اتوبوس و بهره\u200Cبرداری \u200Cاز خطوط اتوبوسرانی شهری و حومه شهری":
                pdfview.fromAsset("دستورالعمل واگذاری اتوبوس و بهره\u200Cبرداری \u200Cاز خطوط اتوبوسرانی شهری و حومه شهری.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Amlak Dastor *******************************************

            //Amlak Rules ************************************************
            case "تعاریف محدوده و حریم شهر و نحوه تعیین آنها":
                pdfview.fromAsset("تعاریف محدوده و حریم شهر و نحوه تعیین آنها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "تعریف و ضوابط تقسیمات کشوری":
                pdfview.fromAsset("تعریف و ضوابط تقسیمات کشوری.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون شهرداری مصوب 1344 با اصلاحات و الحاقات بعدی آن":
                pdfview.fromAsset("قانون شهرداری مصوب 1344 با اصلاحات و الحاقات بعدی آن.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون نوسازی و عمران شهری و آئین نامه ای مربوط":
                pdfview.fromAsset("قانون نوسازی و عمران شهری و آئین نامه ای مربوط.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Amlak Rules ********************************************

            //Shora Dastor ***********************************************
            case "آیین\u200Cنامه نامگذاری شهرها، خیابان\u200Cها، اماکن و مؤسسات عمومی":
                pdfview.fromAsset("آیین\u200Cنامه نامگذاری شهرها، خیابان\u200Cها، اماکن و مؤسسات عمومی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل ترکیب کمیسیون های شوراها":
                pdfview.fromAsset("دستورالعمل ترکیب کمیسیون های شوراها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل شرح وظیفه هیأت رییسه و سخنگوی شوراهای":
                pdfview.fromAsset("دستورالعمل شرح وظیفه هیأت رییسه و سخنگوی شوراهای.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوره رسیدگی به طرح ها  ولوایح":
                pdfview.fromAsset("دستورالعمل نحوره رسیدگی به طرح ها  ولوایح.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه اداره جلسات شوراها":
                pdfview.fromAsset("دستورالعمل نحوه اداره جلسات شوراها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه انجام مراسم تحلیف شوراهای اسلامی کشور":
                pdfview.fromAsset("دستورالعمل نحوه انجام مراسم تحلیف شوراهای اسلامی کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "دستورالعمل نحوه ماموریت شوراها":
                pdfview.fromAsset("دستورالعمل نحوه ماموریت شوراها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "سفر شوراهای محلی به خارج از کشور":
                pdfview.fromAsset("سفر شوراهای محلی به خارج از کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Shora Dastor *******************************************

            //Shora Rules ************************************************
            case "آئین نامه های اجرایی شوراهای اسلامی":
                pdfview.fromAsset("آئین نامه های اجرایی شوراهای اسلامی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "آئین نامه های داخلی شوراهای شهر و شهرستان و استان و عالی لستانها":
                pdfview.fromAsset("آئین نامه های داخلی شوراهای شهر و شهرستان و استان و عالی لستانها.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون تشکیلات شوراهای اسلامی کشوری و انتخابات شوراهای مزبور مصوب 29 تیر 1365 با اصلاحیه های بعدی":
                pdfview.fromAsset("قانون تشکیلات شوراهای اسلامی کشوری و انتخابات شوراهای مزبور مصوب 29 تیر 1365 با اصلاحیه های بعدی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون تشکیلات ووظایف شوراها و انتخاب شهراران به همراه آئین نامه ها و دستورالعملهای":
                pdfview.fromAsset("قانون تشکیلات ووظایف شوراها و انتخاب شهراران به همراه آئین نامه ها و دستورالعملهای.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون تشكيلات، وظايف و انتخابات شوراهاي اسلامي كشور و انتخاب شهرداران مصوب 1375":
                pdfview.fromAsset("قانون تشكيلات، وظايف و انتخابات شوراهاي اسلامي كشور و انتخاب شهرداران مصوب 1375.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Shora Rules ********************************************


            //Another Rules **********************************************
            case "آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات":
                pdfview.fromAsset("آیین نامه اجرایی بند “الف” ماده (26) قانون برگزاری مناقصات.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن":
                pdfview.fromAsset("آیین نامه مناقصه امور ساختمان وزارت راه و راه آهن.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون احکام دائمی توسعه کشور":
                pdfview.fromAsset("قانون احکام دائمی توسعه کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون برنامه ششم توسعه":
                pdfview.fromAsset("قانون برنامه ششم توسعه.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15":
                pdfview.fromAsset("قانون رسیدگی به تخلفات رانندگی و آیین\u200Cنامه اجرایی تبصره 1 ماده 15.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور":
                pdfview.fromAsset("قانون رفع موانع توليد رقابت\u200Cپذير و ارتقاي نظام مالي کشور.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی":
                pdfview.fromAsset("قانون مالیات بر ارزش افزوده و آیین نامه های اجرایی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون مبارزه با پولشویی و آیین نامه اجرایی":
                pdfview.fromAsset("قانون مبارزه با پولشویی و آیین نامه اجرایی.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            case "قانون هوای پاک":
                pdfview.fromAsset("قانون هوای پاک.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();
                break;
            //End Another Rules ******************************************

        }

    }
}
