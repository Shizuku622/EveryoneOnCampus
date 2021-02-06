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

public class EocFragmentPresenter extends Fragment {

    private DbHelper mDbHelper = new DbHelper();
    private SPModel mSpModel = new SPModel();
    private LabelFragment mLabelFragment;
    private MySQLModel mMySQLModel = new MySQLModel();
    //获取全部的已选的标签
    public List<String> getAllSelectedLabel(){
        List<String> labelList = mDbHelper.getSelectedLabelData();
        return labelList;
    }

    public EocFragmentPresenter(){

    }

    public EocFragmentPresenter(LabelFragment labelFragment){
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
    public void infoComplete(){

        if(mDbHelper.getLabelCount() != 0 && mSpModel.infoSexIdent()){
            List<String> labelList = mDbHelper.getSelectedLabelData();
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i < labelList.size();i++){
                sb.append(labelList.get(i)+",");
            }
            SharedPreferences sp = mSpModel.getSp();
            String ident = sp.getString("ident","无");
            String sex = sp.getString("sex","无");
            String usersno = mSpModel.readUserInfo().userSno;
            mMySQLModel.updateUserInfo(usersno,sex,ident,sb.toString());
            Toast.makeText(EocApplication.getContext(),"填写成功！",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(EocApplication.getContext(), "请完善信息！", Toast.LENGTH_SHORT).show();
        }
    }


}
