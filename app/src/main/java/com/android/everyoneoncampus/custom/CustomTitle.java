package com.android.everyoneoncampus.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.everyoneoncampus.R;

public class CustomTitle extends RelativeLayout {
    private ImageView mImgLeftExit;
    private TextView mTxtTitle;
    private ImageView mImgMenu;
    private View mView;
    private TextView mText;

    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title,this);
        mImgLeftExit = findViewById(R.id.imgbtn_custom_leftexit);
        mTxtTitle = findViewById(R.id.txt_custom_title);
        mImgMenu = findViewById(R.id.imgbtn_custom_menu);
        mView = findViewById(R.id.view_custom_title);
        mText = findViewById(R.id.txt_custom_text);
    }

    public void setTxtbtnName(String name){
        mText.setText(name);
    }

    public void setOnTxtBtnClickListener(View.OnClickListener onClickListener){
        mText.setOnClickListener(onClickListener);
    }

    public void showTxtBtn(boolean isShow){
        if(isShow){
            mText.setVisibility(View.VISIBLE);
        }else{
            mText.setVisibility(View.GONE);
        }
    }

    public void showBottomView(boolean isShow){
        if(isShow){
            mView.setVisibility(View.VISIBLE);
        }else{
            mView.setVisibility(View.GONE);
        }
    }

    public void setMenuEnable(boolean set){
        if(set){
            mImgMenu.setVisibility(View.VISIBLE);
        }else{
            mImgMenu.setVisibility(View.GONE);
        }
    }

    public void setImgMenuOnClickListener(View.OnClickListener onClickListener){
        mImgMenu.setOnClickListener(onClickListener);
    }

    public void setImgBtnOnClickListener(View.OnClickListener onClickListener){
        mImgLeftExit.setOnClickListener(onClickListener);
    }

    public void setTxtTitle(String s){
        mTxtTitle.setText(s);
    }

}
