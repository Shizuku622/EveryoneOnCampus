package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.api.SPModel;
import com.android.everyoneoncampus.view.personinfo.LabelFragment;
import com.android.everyoneoncampus.view.personinfo.PersonInfoFragment;

import java.util.List;

public class WriteInfoPresenter extends Fragment {

    private DbHelper mDbHelper = new DbHelper();
    private SPModel mSpModel = new SPModel();
    private LabelFragment mLabelFragment;
    private PersonInfoFragment mPersonInfoFragment;
    private MySQLModel mMySQLModel = new MySQLModel();
    //获取全部的已选的标签
    public List<String> getAllSelectedLabel(){
        List<String> labelList = mDbHelper.getSelectedLabelData();
        return labelList;
    }

    public WriteInfoPresenter(){

    }

    public WriteInfoPresenter(LabelFragment labelFragment){
        mLabelFragment = labelFragment;
    }

    public WriteInfoPresenter(PersonInfoFragment personInfoFragment){
        mPersonInfoFragment = personInfoFragment;
    }


    //删除一个标签
    public void deleteLabel(String labelName){
        mDbHelper.deleteSelectedLabel(labelName);
    }

    //选择一个标签
    public void selectLabel(String labelName){
        mDbHelper.SelectLable(labelName);
    }

    public int getLabelCount(){
        return mDbHelper.getLabelCount();
    }

    //确定完善信息
    public boolean infoComplete(){
        //判断 label和 其他信息
        if(mDbHelper.getLabelCount() != 0 && EocApplication.confirmUserInfo()){
            EocApplication.getUserInfo();
            //切割label，用，分开
            StringBuilder sb = new StringBuilder();
            List<String> labelList = mDbHelper.getSelectedLabelData();
            if(!labelList.isEmpty()){
                int i;
                for (i = 0;i < labelList.size()-1;i++){
                    sb.append(labelList.get(i)+",");
                }
                sb.append(labelList.get(labelList.size()-1));
            }
//            SharedPreferences sp = mSpModel.getWriteInfoSp();
//            String ident = sp.getString("ident","无");
//            String sex = sp.getString("sex","无");
//            String nicheng = sp.getString(mSpModel.NICHENG,"无");
//            String xingming = sp.getString(mSpModel.XINGMING,"无");
//            String qianming = sp.getString(mSpModel.QIANMING,"无");
//            String zhuanye = sp.getString(mSpModel.ZHUANYE,"无");
//            String usersno = mSpModel.readUserInfo();
            mMySQLModel.updateUserInfo(sb.toString());
            Toast.makeText(EocApplication.getContext(),"填写成功！",Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(EocApplication.getContext(), "请完善信息！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
