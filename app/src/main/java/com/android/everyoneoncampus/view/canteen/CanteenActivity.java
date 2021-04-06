package com.android.everyoneoncampus.view.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityCanteenBinding;
import com.android.everyoneoncampus.model.entity.Canteen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CanteenActivity extends BaseActivity {
    private ActivityCanteenBinding mBinding;
    private List<Canteen> mCanteenList = new ArrayList<>();
    private CanteenAdapter mCanteenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCanteenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
        initListener();

    }

    private void initView() {
        mBinding.cttCanteen.setTxtTitle("餐厅特色");

        mCanteenList.add(new Canteen("学生第一餐厅", "西区自动化东楼对面", new ArrayList<Integer>(){
            {
                add(R.drawable.eoc_onecanteen);
                add(R.drawable.eoc_onecanteen2);
                add(R.drawable.eoc_onecanteen3);
            }
        }));

        mCanteenList.add(new Canteen("学生第四餐厅", "东区", new ArrayList<Integer>(){
            {
                add(R.drawable.eoc_fourcanteen1);
                add(R.drawable.eoc_fourcanteen2);
                add(R.drawable.eoc_fourcanteen3);
            }
        }));
        mCanteenList.add(new Canteen("学生第五餐厅", "东区", new ArrayList<Integer>(){
            {
                add(R.drawable.eoc_fivecanteen1);
                add(R.drawable.eoc_fivecanteen2);
                add(R.drawable.eoc_fivecanteen3);
            }
        }));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.recCanteen.setLayoutManager(linearLayoutManager);
        mCanteenAdapter = new CanteenAdapter(mCanteenList);
        mBinding.recCanteen.setAdapter(mCanteenAdapter);
    }

    private void initListener() {
        mBinding.cttCanteen.setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}