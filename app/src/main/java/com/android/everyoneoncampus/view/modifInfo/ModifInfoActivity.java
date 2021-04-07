package com.android.everyoneoncampus.view.modifInfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityModiInfoBinding;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.presenter.ModifPrensenter;
import com.android.everyoneoncampus.view.writepersoninfo.FileUtil;
import com.bumptech.glide.Glide;

import static com.android.everyoneoncampus.EocTools.convertBitmapByte;

public class ModifInfoActivity extends BaseActivity {
    private User modifUser = new User();
    private final int CHOOSE_PHOTO_CODE = 2;
    class InfoEditListener implements TextWatcher{
        private String mModifContent;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mModifContent = s.toString();
//            Toast.makeText(ModifInfoActivity.this, "BeforeTextChanged:" + mModifContent, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(editFlag){
                if(!mModifContent.equals("")){
                    editFlag = false;
                    mBinding.cttModiUserInfoTitle.showTxtBtn(true);
//                    Toast.makeText(ModifInfoActivity.this, "OnTextChanged:" + mModifContent, Toast.LENGTH_SHORT).show();
                }
            }

        }
        @Override
        public void afterTextChanged(Editable s) {}
    }

    private static final String TAG = "ModifInfoActivity";
    private ActivityModiInfoBinding mBinding;
    private ModifPrensenter mModifPrensenter;
    private boolean editFlag = true;
    private boolean spnFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityModiInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mModifPrensenter = new ModifPrensenter(this);
        initView();
        initListener();
        mModifPrensenter.setModifInfo();
    }

    private void initView() {
        mBinding.cttModiUserInfoTitle.setTxtTitle("编辑资料");
        mBinding.cttModiUserInfoTitle.showBottomView(true);
        mBinding.cttModiUserInfoTitle.setTxtbtnName("保存");
    }

    private void initListener() {
        mBinding.flayoutModiHeadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ModifInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ModifInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ModifInfoActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},CHOOSE_PHOTO_CODE);
                }else{
                    openPhoto();
                }
            }
        });


        mBinding.spnModifSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(editFlag && spnFlag){
                    mBinding.cttModiUserInfoTitle.showTxtBtn(true);
                    Log.d(TAG, "onItemSelected: 保存");
                }
                if(!spnFlag){
                    spnFlag = !spnFlag;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.cttModiUserInfoTitle.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //保存用户信息
        mBinding.cttModiUserInfoTitle.setOnTxtBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ModifInfoActivity.this, "正在保存...", Toast.LENGTH_SHORT).show();
                modifUser.userNicheng = mBinding.editModiNicheng.getText().toString();
                modifUser.userSex = mBinding.spnModifSex.getSelectedItem().toString();
                modifUser.userAutograph = mBinding.editModiJianjie.getText().toString();
                modifUser.userPhone = mBinding.editModifPhone.getText().toString();
                modifUser.userPlace = mBinding.editModifPlace.getText().toString();
                if(modifUser.headPic == null){
                    mModifPrensenter.modifUserInfo(modifUser,false);
                }else{
                    mModifPrensenter.modifUserInfo(modifUser,true);
                }
            }
        });

        mBinding.editModiNicheng.addTextChangedListener(new InfoEditListener());
        mBinding.editModifPhone.addTextChangedListener(new InfoEditListener());
        mBinding.editModifPlace.addTextChangedListener(new InfoEditListener());
        mBinding.editModiJianjie.addTextChangedListener(new InfoEditListener());
    }

    private void openPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,CHOOSE_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_PHOTO_CODE:
                if(data != null){
                    Uri uri = data.getData();
                    String filePathName = FileUtil.getFilePathByUri(this,uri);
                    Log.d(TAG, "onActivityResult: "+filePathName);
                    byte[] headpic = EocTools.convertBitmapByte(filePathName);
                    mBinding.imgModifHeadpic.setImageBitmap(EocTools.convertBitmap(headpic));
                    modifUser.headPic = headpic;
                    mBinding.cttModiUserInfoTitle.showTxtBtn(true);
                }
                break;
        }
    }


    public void setCurrentUserInfo(User user){
        mBinding.editModiNicheng.setText(user.userNicheng);
        mBinding.editModiJianjie.setText(user.userAutograph);
        mBinding.editModifPlace.setText(user.userPlace);
        mBinding.editModifPhone.setText(user.userPhone);
        if(user.userSex.equals("男")){
            mBinding.spnModifSex.setSelection(0);
        }else{
            mBinding.spnModifSex.setSelection(1);
        }
        mBinding.editModiSchool.setText(user.userSchool);
        mBinding.imgModifHeadpic.setImageBitmap(EocTools.convertBitmap(user.headPic));
    }


}