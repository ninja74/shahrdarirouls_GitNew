package com.app.shahrdarirouls;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Splash extends AppCompatActivity {

    ImageView imgSplash;
    TextView txtSplashName,txtSplashVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgSplash = findViewById(R.id.imgSplash);
        txtSplashName = findViewById(R.id.txtSplashName);
        txtSplashVersion = findViewById(R.id.txtSplashVersion);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Activity_Splash.this , Activity_Posts.class);
                startActivity(intent);
                finish();
            }
        },2000);



    }
}
