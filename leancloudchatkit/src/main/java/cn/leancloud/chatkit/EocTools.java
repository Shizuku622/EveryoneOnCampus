package cn.leancloud.chatkit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EocTools {

    private static final String TAG = "EocTools";
    public static final String HEADPIC = "HEADPIC";


    //byte[]转换string
    public static String byteConvertString(byte[] bytes){
        String str = Base64.encodeToString(bytes,Base64.DEFAULT);
        return str;
    }
    //string转换byte[]
    public static byte[] stringConvertByte(String str){
        byte[] bytes = Base64.decode(str,Base64.DEFAULT);
        return bytes;
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
    public static Bitmap convertBitmap(byte[] bytes){
        if(bytes != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return bitmap;
        }
        return null;
    }

    public static Bitmap stringConvertBitmap(String avatarUrl){
        byte[] hpb = Base64.decode(avatarUrl,Base64.DEFAULT);
        Bitmap bitmap = convertBitmap(hpb);
        return bitmap;
    }


}
