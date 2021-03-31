package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.view.mainui.uifrag.uiindex.guanzhu.GuanzhuIndexFragment;

import java.util.List;

public class UIIndexPresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private GuanzhuIndexFragment mGuanzhuIndexFragment;

    public UIIndexPresenter(GuanzhuIndexFragment guanzhuIndexFragment){
        mGuanzhuIndexFragment = guanzhuIndexFragment;
    }


    public void setThingsList(){
        mGuanzhuIndexFragment.startSwipRefreash();
        mMySQLModel.getGuanzhuDynamicApi(new DataListener<List<Things>>() {
            @Override
            public void onComplete(List<Things> result) {
                mGuanzhuIndexFragment.stopSwipRefreash();
                if(result != null){
                    mGuanzhuIndexFragment.setThingsList(result);
//                    Toast.makeText(EocApplication.getContext(), "获取动态成功！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EocApplication.getContext(), "暂无动态！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
