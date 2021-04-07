package com.android.everyoneoncampus.view.writepersoninfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.databinding.FragmentPersoninfoBinding;
import com.android.everyoneoncampus.model.api.SPModel;
import com.android.everyoneoncampus.presenter.UIUserInfoPresenter;
import com.android.everyoneoncampus.presenter.WriteInfoPresenter;
import com.bumptech.glide.Glide;


public class PersonInfoFragment extends Fragment {

    private final int CHOOSE_PHOTO_CODE = 2;

    private FragmentPersoninfoBinding mBinding;
    private SPModel mSpModel = new SPModel();
    private WriteInfoPresenter mPresenter;
    private UIUserInfoPresenter mUIUserInfoPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPersoninfoBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new WriteInfoPresenter(this);
        mUIUserInfoPresenter = new UIUserInfoPresenter(this);
        initView();
        initListenter();
    }

    private void initView() {

    }

    private void initListenter() {
        mBinding.imgMofTouxiang.setOnClickListener(v->{
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},CHOOSE_PHOTO_CODE);
            }else{
                openPhoto();
            }
        });

        mBinding.editZhuanye.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mSpModel.writeZhuanYe(s.toString());
                EocApplication.getUserInfo().userSpeci = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.editQianming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mSpModel.writeQianMing(s.toString());
                EocApplication.getUserInfo().userAutograph = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.editXingming.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mSpModel.writeXingMing(s.toString());
                EocApplication.getUserInfo().userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mBinding.editNichen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mSpModel.writeNiCheng(s.toString());
                EocApplication.getUserInfo().userNicheng = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void openPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,CHOOSE_PHOTO_CODE);
    }

    private static final String TAG = "PersonInfoFragment";
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO_CODE:
                Uri uri = data.getData(); // uri
                //获得图片路径
                String filePathName = FileUtil.getFilePathByUri(getActivity(),uri);
                //复制图片到本地
//              CopyFile.copySigleFile(filePathName,getActivity().getExternalFilesDir("").getAbsolutePath()+File.separator+getPathName(filePathName));
                //上传头像
                mUIUserInfoPresenter.uploadHeadPic(filePathName);
                //保存图片
                if(!TextUtils.isEmpty(filePathName)){
                    Glide.with(getActivity()).load(filePathName).into(mBinding.imgMofTouxiang);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getPathName(String path) {
        String[] pathList = path.split("/");
        String listFianl = pathList[pathList.length-1];
        String nameFinal = listFianl.substring(listFianl.lastIndexOf("."));
        String fileName = "touxiang";
        return fileName;
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
