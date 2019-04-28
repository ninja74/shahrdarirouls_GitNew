package com.app.shahrdarirouls;

import android.content.Context;
import android.content.SharedPreferences;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by SMH on 30-Aug-18.
 */

public class FontSize {
    Context context;
    String font = "fonts/b_zar.ttf";

    public FontSize(Context context) {
        this.context = context;
    }


    public void setFontSize() {
        final SharedPreferences shared = context.getSharedPreferences("prefes", 0);
        int Size = shared.getInt("size?", 1);
        switch (Size) {
            case 10:
                context.setTheme(R.style.Font10);
                break;

            case 13:
                context.setTheme(R.style.Font13);
                break;

            case 16:
                context.setTheme(R.style.Font16);
                break;

            case 19:
                context.setTheme(R.style.Font19);
                break;

            case 22:
                context.setTheme(R.style.Font22);
                break;

            case 25:
                context.setTheme(R.style.Font25);
                break;

            case 28:
                context.setTheme(R.style.Font28);
                break;

            case 31:
                context.setTheme(R.style.Font31);
                break;

            case 34:
                context.setTheme(R.style.Font34);
                break;

            case 37:
                context.setTheme(R.style.Font37);
                break;

            case 40:
                context.setTheme(R.style.Font40);
                break;

        }

        String fontSwitch = shared.getString("font?", font);
        switch (fontSwitch) {
            case "نازنین":
                font = "fonts/b_nazanin.ttf";
                break;
            case "نازنین بزرگ":
                font = "fonts/b_nazanin_b.ttf";
                break;
            case "یکان":
                font = "fonts/b_yekan.ttf";
                break;
            case "یکان بزرگ":
                font = "fonts/b_yekan_b.ttf";
                break;
            case "زر":
                font = "fonts/b_zar.ttf";
                break;
            case "زر بزرگ":
                font = "fonts/b_zar_b.ttf";
                break;
            case "ایران سنس":
                font = "fonts/iransans_normal.ttf";
                break;
            case "ایران سنس بزرگ":
                font = "fonts/iransans_b.ttf";
                break;


        }


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(font).setFontAttrId(R.attr.fontPath).build());

    }


}
