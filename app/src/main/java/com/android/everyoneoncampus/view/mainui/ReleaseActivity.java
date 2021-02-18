package com.android.everyoneoncampus.view.mainui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.databinding.ActivityReleaseBinding;
import com.android.everyoneoncampus.presenter.Presenter;

import java.util.concurrent.TimeoutException;

public class ReleaseActivity extends AppCompatActivity {
    private ActivityReleaseBinding mBinding;
    private String mMainTitle;
    private Presenter mPresenter;

    public static void actionActivity(Context context, String t){
        String titleValue = t;
        Intent intent = new Intent(context,ReleaseActivity.class);
        intent.putExtra("title",titleValue);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityReleaseBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mPresenter = new Presenter(this);
        initViews();
        initListeners();
    }

    private void initListeners() {
        mBinding.txtCancel.setOnClickListener(v->{
            finish();
        });
        mBinding.txtSend.setOnClickListener(v->{
            String content = mBinding.editSendContent.getText().toString();
            if(!content.trim().isEmpty()){
                Toast.makeText(EocApplication.getContext(),"正在发送...",Toast.LENGTH_SHORT).show();
                mPresenter.sendNewSomething(mMainTitle,content);
            }
        });
    }

    private void initViews() {
        String title = getIntent().getStringExtra("title");
        mMainTitle = title;
        if(title != null){
            switch (title){
                case "things":
                    mBinding.txtTitle.setText("新鲜事");
                    break;
                case "problem":
                    mBinding.txtTitle.setText("提问");
                    break;
                case "lose":
                    mBinding.txtTitle.setText("丢失");
                    break;
                case "sign":
                    mBinding.txtTitle.setText("签到");
                    break;
                default:
                    mBinding.txtTitle.setText("空");
                    break;
            }
        }
    }


}
