package com.android.everyoneoncampus.presenter;

import android.util.Log;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.DynamicInfoFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UIUserInfoFragment;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoActivity;
import com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo.UserInfoFragment;
import com.android.everyoneoncampus.view.personinfo.PersonInfoFragment;

import java.util.List;

public class UIUserInfoPresenter {

    private UIUserInfoFragment mUiUserInfoFragment;
    private DynamicInfoFragment mDynamicInfoFragment;
    private UserInfoActivity mUserInfoActivity;
    private UserInfoFragment mUserInfoFragment;

    private PersonInfoFragment mPersonInfoFragment;
    private MySQLModel mMySQLModel = new MySQLModel();
    private DbHelper mDbHelper = new DbHelper();


    public UIUserInfoPresenter(UIUserInfoFragment uiUserInfoFragment){
        this.mUiUserInfoFragment = uiUserInfoFragment;
    }
    public UIUserInfoPresenter(PersonInfoFragment personInfoFragment){
        this.mPersonInfoFragment = personInfoFragment;
    }
    public UIUserInfoPresenter(DynamicInfoFragment dynamicInfoFragment){
        this.mDynamicInfoFragment = dynamicInfoFragment;
    }
    public UIUserInfoPresenter(UserInfoActivity userInfoActivity){
        this.mUserInfoActivity = userInfoActivity;
    }
    public UIUserInfoPresenter(UserInfoFragment userInfoFragment){
        this.mUserInfoFragment = userInfoFragment;
    }


    //下面的个人信息
    public void getSQliteBottomDetailUserInfo(){
        mDbHelper.readCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mUserInfoFragment.setUserDetailInfo(result);
            }
        });
    }

    //上面的个人信息
    public void getSQliteTopDetailUserInfo(){
        mDbHelper.readCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mUserInfoActivity.setUserDetailInfo(result);
            }
        });
    }

    //主界面 个人信息 从SQlIte 获取个人信息
    public void getSQliteMainUserInfo(){
        mDbHelper.readCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mUiUserInfoFragment.setUserInfo(result);
            }
        });
    }

    //个人动态
    public void getUserDynamic(){
        mDynamicInfoFragment.refreshDynamic();
        mMySQLModel.getUserDynamic(new DataListener<List<Things>>() {
            @Override
            public void onComplete(List<Things> result) {
                mDynamicInfoFragment.setThingList(result);
                mDynamicInfoFragment.hideRefreshDynamic();
            }
        });
    }

    //获得动态数量
    public void getDynamicNum(){
        mMySQLModel.getDynamicNumber(new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                mUiUserInfoFragment.setDynamic(result);
            }
        });
    }

    //获得关注和被关注数量
    public void getFollowNum(){
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

    private static final String TAG = "UIUserInfoPresenter";
    //获得当前用户的信息
    public void getCurrentUserInfo(){
        mMySQLModel.getCurrentUserInfoApi(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                if(result != null){
                    //更新到本地
                    mDbHelper.updateExistUserInfo(result);
                    //同步跟新到界面
                    getSQliteMainUserInfo();
                    mUiUserInfoFragment.stopRefresh();
                    Log.d(TAG, "onComplete: 停止刷新");
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
//    public void setHeadPic(){
//        try {
//        //先找到本地上有没有该用户的头像，如果没有再从网上找
//        String headPicFile = EocApplication.getContext().getExternalFilesDir("").getAbsolutePath()+File.separator+EocApplication.getUserInfo().userID+"headpic";
//        File file = new File(headPicFile);
//        if(file.exists()){
//            FileInputStream inputStream = new FileInputStream(headPicFile);
//            Bitmap bm = BitmapFactory.decodeStream(inputStream);
//            mUiUserInfoFragment.setHeadPic(bm);
//        }
//        else{
//            mMySQLModel.getPic(new DataListener<byte[]>() {
//                @Override
//                public void onComplete(byte[] result) {
//                    Bitmap headPic = EocTools.convertByteBitmap(result);
//                    //网上下载的图片保存到本地
//                    EocTools.saveBitmapPic(headPic);
//                    mUiUserInfoFragment.setHeadPic(headPic);
//                }
//            });
//        }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
