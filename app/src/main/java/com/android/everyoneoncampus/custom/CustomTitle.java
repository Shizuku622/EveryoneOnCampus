package com.android.everyoneoncampus.custom;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.everyoneoncampus.R;

public class CustomTitle extends RelativeLayout {
    private ImageView mImageView ;
    private TextView mTextView;

    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title,this);
        mImageView = findViewById(R.id.imgbtn_custom_leftexit);
        mTextView = findViewById(R.id.txt_custom_title);
    }


    public void setImgBtnOnClickListener(View.OnClickListener onClickListener){
        mImageView.setOnClickListener(onClickListener);
    }

    public void setTxtTitle(String s){
        mTextView.setText(s);
    }

}
