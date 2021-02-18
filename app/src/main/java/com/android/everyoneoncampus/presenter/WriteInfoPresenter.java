package com.android.everyoneoncampus.presenter;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.model.DbHelper;
import com.android.everyoneoncampus.model.MySQLModel;
import com.android.everyoneoncampus.model.SPModel;
import com.android.everyoneoncampus.view.personinfo.LabelFragment;

import java.io.InterruptedIOException;
import java.util.List;

public class WriteInfoPresenter extends Fragment {

    private DbHelper mDbHelper = new DbHelper();
    private SPModel mSpModel = new SPModel();
    private LabelFragment mLabelFragment;
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
        if(mDbHelper.getLabelCount() != 0 && mSpModel.infoSexIdent()){
            StringBuilder sb = new StringBuilder();
            List<String> labelList = mDbHelper.getSelectedLabelData();
            if(!labelList.isEmpty()){
                int i;
                for (i = 0;i < labelList.size()-1;i++){
                    sb.append(labelList.get(i)+",");
                }
                sb.append(labelList.get(labelList.size()-1));
            }
            SharedPreferences sp = mSpModel.getSp();
            String ident = sp.getString("ident","无");
            String sex = sp.getString("sex","无");
            String usersno = mSpModel.readUserInfo();
            mMySQLModel.updateUserInfo(usersno,sex,ident,sb.toString());
            Toast.makeText(EocApplication.getContext(),"填写成功！",Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(EocApplication.getContext(), "请完善信息！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
