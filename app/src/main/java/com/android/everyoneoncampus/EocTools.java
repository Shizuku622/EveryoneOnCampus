package com.android.everyoneoncampus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;

public class EocTools {

    private static final String TAG = "EocTools";
    public static final String HEADPIC = "HEADPIC";
    public static void setStatusBar(AppCompatActivity activity){
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //上传图片
    public static byte[] convertBitmapByte(String filePathName){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream picFile = new FileInputStream(filePathName);
            Bitmap bitmap = BitmapFactory.decodeStream(picFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,13,baos);
            byte[] picByte = baos.toByteArray();
            picFile.close();
            return  picByte;
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
            return  null;
        }
        finally {

        }
    }

    //转换byte[]为bitmap
    public static Bitmap convertByteBitmap(byte[] bytes){
        if(bytes != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return bitmap;
        }
        return null;
    }

    //保存Bitmap到本地上
    public static String saveBitmapPic(Bitmap bitmap){
        try{
            String targetPath = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath();
            File saveFile = new File(targetPath,EocApplication.getUserInfo().userID+"headpic");
            FileOutputStream saveInputFile = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,15,saveInputFile);
            saveInputFile.flush();
            saveInputFile.close();
            return EocApplication.getContext().getExternalFilesDir("").getAbsolutePath() + File.separator + EocApplication.getUserInfo().userID+"headpic";
        }catch (Exception e){
            return  "";
        }
    }
    //保存任意文件
    public static String saveBitmapFile(Bitmap bitmap,String fileName){
        try{
            String targetPath = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath();
            File saveFile = new File(targetPath,fileName);
            FileOutputStream saveInputFile = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,15,saveInputFile);
            saveInputFile.flush();
            saveInputFile.close();
            return EocApplication.getContext().getExternalFilesDir("").getAbsolutePath() + File.separator +fileName;
        }catch (Exception e){
            return  "";
        }
    }

}
