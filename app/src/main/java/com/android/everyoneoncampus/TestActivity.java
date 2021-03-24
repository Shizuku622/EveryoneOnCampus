package com.android.everyoneoncampus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.view.personinfo.FileUtil;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    MySQLModel mMySQLModel = new MySQLModel();

    ImageView imageView;

    TextView textView;
    private final int CHOOSE_PHOTO_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView2);
        button.setOnClickListener(v->{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},CHOOSE_PHOTO_CODE);
            }else{
                openPhoto();
            }
        });

        button2.setOnClickListener(v->{
            mMySQLModel.testGetPic(new DataListener<byte[]>() {
                @Override
                public void onComplete(byte[] result) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(result,0,result.length);
                    BitmapDrawable bd = new BitmapDrawable(getResources(),bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });

        });

    }

    private void openPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,CHOOSE_PHOTO_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO_CODE:
                Uri uri = data.getData(); // uri
                //获得图片路径
                String filePathName = FileUtil.getFilePathByUri(this,uri);
                //打开文件
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    FileInputStream picFile = new FileInputStream(filePathName);
                    Bitmap bitmap = BitmapFactory.decodeStream(picFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,10,baos);
                    byte[] picByte = baos.toByteArray();
                    mMySQLModel.testUploadPic(picByte);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //保存图片
                if(!TextUtils.isEmpty(filePathName)){
                    Glide.with(this).load(filePathName).into(imageView);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CHOOSE_PHOTO_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    openPhoto();
                }else{
                    Toast.makeText(EocApplication.getContext(), "权限已被拒绝！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}