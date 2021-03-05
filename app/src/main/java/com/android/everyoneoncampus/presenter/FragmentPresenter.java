package com.android.everyoneoncampus.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.User;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UIUserInfoFragment;
import com.android.everyoneoncampus.view.personinfo.PersonInfoFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FragmentPresenter {

    private UIUserInfoFragment mUiUserInfoFragment;
    private MySQLModel mMySQLModel = new MySQLModel();
    private PersonInfoFragment mPersonInfoFragment;

    public FragmentPresenter(UIUserInfoFragment uiUserInfoFragment){
        this.mUiUserInfoFragment = uiUserInfoFragment;
    }
    public FragmentPresenter(PersonInfoFragment personInfoFragment){
        this.mPersonInfoFragment = personInfoFragment;
    }

    //获得动态数量
    public void setDynamicInfo(){
        mMySQLModel.getDynamicNumber(new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                mUiUserInfoFragment.setDynamic(result);
            }
        });
    }
    //获得关注和被关注数量
    public void setFollow(){
        mMySQLModel.getFollow(new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                mUiUserInfoFragment.setFollow(result);
            }
        });
        mMySQLModel.getFollowed(new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                mUiUserInfoFragment.setFollowed(result);
            }
        });
    }

    public void getCurrentUser(){
        mMySQLModel.getCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                if(result != null){
                    mUiUserInfoFragment.setUserInfo(result);
                }
            }
        });
    }

    //上传头像
    public void uploadHeadPic(String filePathName){
        byte[] bytes = EocTools.convertBitmapByte(filePathName);
        mMySQLModel.uploadPic(bytes);
    }

    //设置头像
    public void setHeadPic(){
        try {
        //先找到本地上有没有该用户的头像，如果没有再从网上找
        String headPicFile = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath()+File.separator+EocApplication.getUserInfo().userID+"headpic";
        File file = new File(headPicFile);
        if(file.exists()){
            FileInputStream inputStream = new FileInputStream(headPicFile);
            Bitmap bm = BitmapFactory.decodeStream(inputStream);
            mUiUserInfoFragment.setHeadPic(bm);
        }
        else{
            mMySQLModel.getPic(new DataListener<byte[]>() {
                @Override
                public void onComplete(byte[] result) {
                    Bitmap headPic = EocTools.convertByteBitmap(result);
                    //网上下载的图片保存到本地
                    EocTools.saveBitmapPic(headPic);
                    mUiUserInfoFragment.setHeadPic(headPic);
                }
            });
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
