package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import com.android.everyoneoncampus.CustomUserProvider;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.User;
import com.android.everyoneoncampus.view.modifInfo.ModifInfoActivity;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

public class ModifPrensenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private DbHelper mDbHelper = new DbHelper();
    private ModifInfoActivity mModifInfoActivity;
    public ModifPrensenter(ModifInfoActivity modifInfoActivity){
        mModifInfoActivity = modifInfoActivity;
    }

    /**
     * 修改用户信息
     */
    public void modifUserInfo(User user,boolean headpic){
        //保存在网络
        mMySQLModel.updateModifUserApi(user, headpic, new DataListener<Integer>() {
            @Override
            public void onComplete(Integer result) {
                if(result == 1){
                    Toast.makeText(mModifInfoActivity, "保存成功！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mModifInfoActivity, "保存失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setModifInfo(){
        mDbHelper.readCurrentUserInfo(new DataListener<User>() {
            @Override
            public void onComplete(User result) {
                mModifInfoActivity.setCurrentUserInfo(result);
            }
        });
    }

}
