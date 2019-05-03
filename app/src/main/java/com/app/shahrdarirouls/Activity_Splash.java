package com.app.shahrdarirouls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class Activity_Splash extends AppCompatActivity {

    TextView txtTryAgain;
    private checkInternet internet;
    SharedPreferences preferences;
    AlertDialog alertlogin;
    String cod;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtTryAgain = findViewById(R.id.txtTryAgain);
        internet = new checkInternet(this);

        preferences = getSharedPreferences("shahrdariroulS", 0);
        login = preferences.getBoolean("logIn", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (login)
                    gotoNextActvity();
                else {
                    if (internet.CheckNetworkConnection())
                        AlertDialogLogIn();
                    else
                        checkNet();
                }


            }
        }, 2000);


        txtTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (internet.CheckNetworkConnection())
                    AlertDialogLogIn();
                else
                    checkNet();
            }
        });


        if (internet.CheckNetworkConnection()){
            String url = "http://www.azmoonshahri.ir/meysam/shahrDari.php";

            StringRequest requestCoupons = new StringRequest(Request.Method.GET, url, new OkResListener(), new ErrListener());
            RequestQueue requestQueue = Volley.newRequestQueue(Activity_Splash.this);
            requestQueue.add(requestCoupons);
        }

    }

    private void gotoNextActvity() {


        Intent intent = new Intent(Activity_Splash.this, Activity_Posts.class);
        startActivity(intent);
        finish();

    }

    private void AlertDialogLogIn() {

        String url = "http://www.azmoonshahri.ir/meysam/shahrDari.php";

        StringRequest requestCoupons = new StringRequest(Request.Method.GET, url, new OkResListener(), new ErrListener());
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Splash.this);
        requestQueue.add(requestCoupons);


        AlertDialog.Builder builder_login = new AlertDialog.Builder(Activity_Splash.this);
        LinearLayout linearLayout_login = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_login, null, false);
        final EditText edtCod = linearLayout_login.findViewById(R.id.edtCod);
        Button btnCod = linearLayout_login.findViewById(R.id.btnCod);

        btnCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCod = edtCod.getText().toString();
                if (userCod.equals(cod)) {
                    Toast.makeText(Activity_Splash.this, "خوش آمدید", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("logIn", true);
                    editor.apply();
                    gotoNextActvity();
                    alertlogin.dismiss();
                } else {
                    Toast.makeText(Activity_Splash.this, "کد وارد شده صحیح نمی باشد", Toast.LENGTH_SHORT).show();
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

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("subListMali", object.getString("subListMali"));
                editor.putString("subListAmlak", object.getString("subListAmlak"));
                editor.putString("subListAnotherRules", object.getString("subListAnotherRules"));
                editor.putString("subListEstekgDami", object.getString("subListEstekgDami"));
                editor.putString("subListShora", object.getString("subListShora"));
                editor.putString("subListMali", object.getString("subListMali"));
                editor.putString("pdfNameMaliBodje", object.getString("pdfNameMaliBodje"));
                editor.putString("pdfNameMaliBodje", object.getString("pdfNameMaliBodje"));
                editor.putString("pdfNameMaliDastor", object.getString("pdfNameMaliDastor"));
                editor.putString("pdfNameMaliRules", object.getString("pdfNameMaliRules"));
                editor.putString("pdfNameMaliNasab", object.getString("pdfNameMaliNasab"));
                editor.putString("pdfNamesAmlakDastor", object.getString("pdfNamesAmlakDastor"));
                editor.putString("pdfNamesAmlakRules", object.getString("pdfNamesAmlakRules"));
                editor.putString("pdfNamesShoraDastor", object.getString("pdfNamesShoraDastor"));
                editor.putString("pdfNamesShoraRules", object.getString("pdfNamesShoraRules"));
                editor.putString("pdfNamesAnotherRules", object.getString("pdfNamesAnotherRules"));
                editor.putString("pdfNamesEstekgDami", object.getString("pdfNamesEstekgDami"));
                editor.apply();


            } catch (JSONException e) {
                e.printStackTrace();


            }
        }
    }

    private class ErrListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {

            if (error instanceof NetworkError) {
            } else if (error instanceof ServerError) {
            } else if (error instanceof AuthFailureError) {
            } else if (error instanceof ParseError) {
            } else if (error instanceof NoConnectionError) {
            } else if (error instanceof TimeoutError)
                Toast.makeText(Activity_Splash.this, "ارور در هنگام برقراری با سرور", Toast.LENGTH_SHORT).show();
            error.printStackTrace();

        }
    }


    private void checkNet() {
        txtTryAgain.setVisibility(View.VISIBLE);

        PrettyDialog prettyDialog = new PrettyDialog(this);
        prettyDialog.setIcon(
                R.drawable.pdlg_icon_info,     // icon resource
                R.color.pdlg_color_red,      // icon tint
                new PrettyDialogCallback() {   // icon OnClick listener
                    @Override
                    public void onClick() {

                    }
                });
        prettyDialog.setTitle(getString(R.string.AlertCantConnect));
        prettyDialog.setMessage(getString(R.string.AlertCheckNetAgain));
        prettyDialog.show();
    }

}
