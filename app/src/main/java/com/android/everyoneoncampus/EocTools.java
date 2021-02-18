package com.android.everyoneoncampus;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EocTools {
    public static void setStatusBar(AppCompatActivity activity){
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
