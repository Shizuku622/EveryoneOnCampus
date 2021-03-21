package com.android.everyoneoncampus.view.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityUserloginBinding;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.presenter.LoginPresenter;
import com.android.everyoneoncampus.view.mainui.MainUIActivity;
import com.android.everyoneoncampus.view.personinfo.PersoninfoActivity;
import com.android.everyoneoncampus.view.register.RegisterActivity;
import com.bumptech.glide.util.LogTime;

import java.io.File;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import io.reactivex.internal.operators.flowable.FlowableElementAtSingle;
import retrofit2.internal.EverythingIsNonNull;

public class UserActivity extends BaseActivity implements UserViewInterface{

    private ActivityUserloginBinding mBinding;
    private LoginPresenter mLoginPresenter;
    private static final String TAG = "UserActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserloginBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        //设置状态栏
        setStatusBar();

        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.getAllLable();
        initViews();
        initListener();

        File files = Environment.getExternalStorageDirectory().getAbsoluteFile();
        Log.d(TAG,Environment.getExternalStoragePublicDirectory("").getAbsolutePath());

        File[] twofile = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
        for(File file:twofile){
            Log.d(TAG, file.toString());
        }

    }

    private void setStatusBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initListener() {
        //登录
        mBinding.btnLogin.setOnClickListener(v->{
            String user = mBinding.etUser.getText().toString();
            String passwd = mBinding.etPasswd.getText().toString();
            mLoginPresenter.userLogin(user,passwd);
            InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
            if(manager != null){
                manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        //注册按钮
        mBinding.txtRegister.setOnClickListener(v->{
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void initViews(){

    }
    @Override
    public void loginMainUI(User user){
        //自动开启
        Bitmap headPic = EocTools.convertByteBitmap(user.headPic);
        String savePath = EocTools.saveBitmapPic(headPic);
        CustomUserProvider.addChatUser(new LCChatKitUser(EocApplication.USER_MARK+user.userID,user.userName,savePath));

        AVIMOptions.getGlobalOptions().setAutoOpen(true);
        LCChatKit.getInstance().open(EocApplication.USER_MARK + user.userID, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    finish();
                    Intent intent = new Intent(UserActivity.this, MainUIActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "leancloud 登录成功！");
                } else {
                    Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void userWriteUserInfo() {
        //获取登录用户信息是否跳转到填写信息 登录跳转
        //已mark的不用登记，没有填写要登记。
        startActivity(new Intent(this, PersoninfoActivity.class));
        finish();
    }

    @Override
    public void userMainUserUI() {

    }

    @Override
    public void showProgressLogin() {
        mBinding.probarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLogin() {
        mBinding.probarLogin.setVisibility(View.GONE);
    }
}