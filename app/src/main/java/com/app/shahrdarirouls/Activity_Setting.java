package com.app.shahrdarirouls;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Setting extends AppCompatActivity {

    String[] fontsName = {"نازنین", "نازنین بزرگ", "یکان", "یکان بزرگ", "زر", "زر بزرگ", "ایران سنس", "ایران سنس بزرگ",};
    String[] fonts = {"fonts/b_nazanin.ttf", "fonts/b_nazanin_b.ttf", "fonts/b_yekan.ttf", "fonts/b_yekan_b.ttf", "fonts/b_zar.ttf", "fonts/b_zar_b.ttf", "fonts/iransans_normal.ttf", "fonts/iransans_b.ttf"};
    String font = "fonts/b_zar.ttf";
    SeekBar seekBarFont;
    Button btnsabt;
    Spinner spinnerFont;
    TextView txttestSize, txttestFont;
    int numberProgressAfter = 10, spinnerSelection = 0, spinnerSelectionAfter = 0;
    boolean changeSetting = false;

    SharedPreferences shared;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FontSize fontSize = new FontSize(this);
        fontSize.setFontSize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        spinnerFont = (Spinner) findViewById(R.id.spinnerFont);
        seekBarFont = (SeekBar) findViewById(R.id.seekBarFont);
        txttestSize = (TextView) findViewById(R.id.txttestSize);
        txttestFont = (TextView) findViewById(R.id.txttestFont);
        btnsabt = (Button) findViewById(R.id.btnsabt);
        shared = getSharedPreferences("prefes", 0);
        final int Size = shared.getInt("size?", 16);
        txttestSize.setTextSize(Size);
        seekBarFont.setProgress((Size - 10) / 3);
        spinnerSelection = shared.getInt("idSpinner?", 0);
        spinnerFont.setSelection(spinnerSelection);

        ArrayAdapter arrayAdapter_Spinner = new ArrayAdapter(Activity_Setting.this, android.R.layout.simple_spinner_item, fontsName);
        spinnerFont.setAdapter(arrayAdapter_Spinner);
        spinnerFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelectionAfter = i;
                font = fontsName[i];
                Typeface custom_font = Typeface.createFromAsset(getAssets(), fonts[i]);
                txttestFont.setTypeface(custom_font);
                changeSetting = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        seekBarFont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txttestSize.setTextSize(i * 3 + 10);
                numberProgressAfter = i * 3 + 10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                changeSetting = true;
            }
        });

        btnsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (changeSetting == false) {
                    Toast.makeText(Activity_Setting.this, "تغییراتی داده نشده", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(Activity_Setting.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(Activity_Setting.this);
                    }
                    builder.setTitle("ریست کردن برنامه")
                            .setMessage("برای اعمال تغییرات باید برنامه رو ریست کنید؟")
                            .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                     SharedPreferences.Editor editor = shared.edit();

                                    // save Size
                                    editor.putInt("size?", numberProgressAfter);
                                    editor.commit();
                                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);

                                    // save Font
                                    editor.putString("font?", font);
                                    editor.putInt("idSpinner?", spinnerSelectionAfter);
                                    editor.commit();

                                    Toast.makeText(Activity_Setting.this, "تنظیمات ذخیره شد", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    seekBarFont.setProgress((Size - 10) / 3);
                                    txttestSize.setTextSize(Size);
                                    spinnerFont.setSelection(spinnerSelection);

                                    Toast.makeText(Activity_Setting.this, "تنظیمات ذخیره نشد", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .show();
                }
            }
        });


    }


}



