package com.app.shahrdarirouls;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

public class Activity_PDF_View extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        PDFView pdfview = (PDFView) findViewById(R.id.pdfview);

        String keyPDF = getIntent().getStringExtra("KeyPDF");

//        pdfview.fromFile(keyPDF).defaultPage(0).showMinimap(true).enableSwipe(true).load();

        pdfview.fromFile(new File(Environment.getExternalStorageDirectory().getPath()+"/ShahrdariPDF", keyPDF)).defaultPage(0).showMinimap(true).enableSwipe(true).load();

    }
}
