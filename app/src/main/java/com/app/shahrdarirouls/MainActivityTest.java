package com.app.shahrdarirouls;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.joanzapata.pdfview.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityTest extends AppCompatActivity {


    Button btn,btnShow;
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10 ;
    private static final String TAG = MainActivityTest.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        btn = findViewById(R.id.button);
        btnShow = findViewById(R.id.button2);


        if (!(Build.VERSION.SDK_INT < 23)) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DownloadFile().execute("http://meysamsm.gigfa.com/pdf/anotherrules/test2.pdf" ,"test.pdf");
                new DownloadFile().execute("http://www.azmoonshahri.ir/meysam/test.pdf" ,"test.pdf");
//                new DownloadFile().execute("http://dl2.pnueb.com/Status%20of%20educational%20resources/manabe-132210.pdf" ,"test.pdf");

//                String mUrl= "http://www.azmoonshahri.ir/meysam/test.pdf";
//                connect request = new connect(Request.Method.GET, mUrl,
//                        new Response.Listener<byte[]>() {
//                            @Override
//                            public void onResponse(byte[] response) {
//                                // TODO handle the response
//                                try {
//                                    if (response!=null) {
//
//                                        FileOutputStream outputStream;
//                                        String name="test.pdf";
//                                        outputStream = openFileOutput(name, Context.MODE_PRIVATE);
//                                        outputStream.write(response);
//                                        outputStream.close();
//                                        Toast.makeText(MainActivityTest.this,
//                                                MainActivityTest.this.getFilesDir()+"Download complete.", Toast.LENGTH_LONG).show();
//                                    }
//                                } catch (Exception e) {
//                                    // TODO Auto-generated catch block
//                                    Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
//                                    e.printStackTrace();
//                                }
//                            }
//                        } ,new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO handle the error
//                        error.printStackTrace();
//                    }
//                }, null);
//                RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack());
//                mRequestQueue.add(request);



            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PDFView pdfview = (PDFView) findViewById(R.id.pdfview);

                if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageDirectory())){
                    pdfview.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() , "test.pdf")).defaultPage(0).showMinimap(true).enableSwipe(true).load();
                }

//                Toast.makeText(MainActivityTest.this, Environment.getExternalStorageDirectory()+"", Toast.LENGTH_SHORT).show();

//                pdfview.fromAsset("1-1.pdf").defaultPage(0).showMinimap(true).enableSwipe(true).load();


//                File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "test.pdf");  // -> filename = maven.pdf
//                Uri path = Uri.fromFile(pdfFile);
//                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                pdfIntent.setDataAndType(path, "application/pdf");
//                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                try{
//                    startActivity(pdfIntent);
//                }catch(ActivityNotFoundException e){
//                    Toast.makeText(MainActivityTest.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
//                }
            }
        });



    }



//    private class DownloadFile extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
//            String fileName = strings[1];  // -> maven.pdf
//            String extStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
//            File folder = new File(extStorageDirectory);
//            folder.mkdir();
//
//            File pdfFile = new File(folder, fileName);
//
//            try{
//                pdfFile.createNewFile();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            connect.downloadFile(fileUrl, pdfFile);
//            return null;
//        }
//    }



    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(MainActivityTest.this);
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

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


                //External directory path to save file
                folder = Environment.getExternalStorageDirectory().getAbsolutePath();

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
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
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
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();
        }
    }


}
