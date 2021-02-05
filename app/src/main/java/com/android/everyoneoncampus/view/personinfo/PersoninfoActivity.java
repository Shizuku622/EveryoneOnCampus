package com.android.everyoneoncampus.view.personinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.databinding.ActivityPersoninfoBinding;
import com.android.everyoneoncampus.model.LabelAll;
import com.android.everyoneoncampus.presenter.EocPresenter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersoninfoActivity extends AppCompatActivity implements PersoninfoViewInterface {
    private ActivityPersoninfoBinding mBinding;
    private LabelFragment mLabelFragment;
    private EocPresenter mEocPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPersoninfoBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        setStatusBar();
        mEocPresenter = new EocPresenter(this);
        mEocPresenter.getLabelContent();
        initViews();
    }

    private void initViews() {
        //初始化viewpage

    }

    private void setStatusBar(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private static final String TAG = "PersoninfoActivity";

    @Override
    public void setTitleContent(List<String> labelType, LabelAll labelName,List<String> selectedLabel) {
        Log.d(TAG, "到这里");
        List<Fragment> fragmentsList = new ArrayList<>();
        fragmentsList.add(new SexFragment());
        fragmentsList.add(new IdentFragment());
        fragmentsList.add(new LabelFragment(labelType,labelName));
        WriteInfoViewPageAdapter adapter = new WriteInfoViewPageAdapter(getSupportFragmentManager(),fragmentsList);
        mBinding.vpageUserinfo.setAdapter(adapter);
    }

}