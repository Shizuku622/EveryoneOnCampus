package com.android.everyoneoncampus.view.mainui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.everyoneoncampus.CopyFile;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityReleaseBinding;
import com.android.everyoneoncampus.presenter.Presenter;
import com.android.everyoneoncampus.presenter.ThingsPresenter;
import com.android.everyoneoncampus.view.personinfo.FileUtil;

import java.io.File;
import java.net.URL;
import java.security.PrivateKey;
import java.sql.Blob;
import java.util.concurrent.TimeoutException;

import io.reactivex.internal.operators.completable.CompletableDoFinally;

public class ReleaseActivity extends AppCompatActivity {
    private ActivityReleaseBinding mBinding;
    private String mMainTitle;
    private Presenter mPresenter;
    private ThingsPresenter mThingsPresenter;
    public final static int CHOSSE_PHOTO = 1;
    private String mFilePathName = "";

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
        EocTools.setStatusBar(this);
        mPresenter = new Presenter(this);
        mThingsPresenter = new ThingsPresenter(this);
        initViews();
        initListeners();
        //权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},CHOSSE_PHOTO);
        }
    }

    private void initListeners() {
        mBinding.llayoutEditClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.editSendContent.setFocusable(true);
                mBinding.editSendContent.setFocusableInTouchMode(true);
                mBinding.editSendContent.requestFocus();
                ReleaseActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });
        mBinding.txtCancel.setOnClickListener(v->{
            finish();
        });
        mBinding.txtSend.setOnClickListener(v->{
            String content = mBinding.editSendContent.getText().toString();
            if(!content.trim().isEmpty()){
                Toast.makeText(EocApplication.getContext(),"正在发送...",Toast.LENGTH_SHORT).show();

                if(mFilePathName.equals("")){
                    mPresenter.sendNewSomething(mMainTitle,content,null);
                }else{
                    byte[] bytes = EocTools.convertBitmapByte(mFilePathName);
                    mPresenter.sendNewSomething(mMainTitle,content,bytes);
                }
            }
        });

        mBinding.imgChooseThingsPic.setOnClickListener(v->{
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }else{
                openPhoto();
            }
        });
    }

    private void openPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,CHOSSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOSSE_PHOTO:
                Uri uri = data.getData();
                String filePath = FileUtil.getFilePathByUri(this,uri);
                String newFile = getExternalCacheDir().getAbsolutePath()+ File.separator+System.currentTimeMillis();
                CopyFile.copySigleFile(filePath,newFile);
                File openNewFile = new File(newFile);
                if(openNewFile.exists()){
                    mFilePathName = newFile;
                }else {
                    mFilePathName = "";
                }
                mBinding.imgPic.setImageURI(uri);
                break;
        }
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
