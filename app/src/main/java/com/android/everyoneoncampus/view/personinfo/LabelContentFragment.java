package com.android.everyoneoncampus.view.personinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.everyoneoncampus.allinterface.GetData;
import com.android.everyoneoncampus.allinterface.OperateMethod;
import com.android.everyoneoncampus.databinding.FragmentLabelContentBinding;
import com.android.everyoneoncampus.presenter.WriteInfoPresenter;

import java.util.List;

public class LabelContentFragment extends Fragment{

//    public class MofFragmentAdapter extends BroadcastReceiver{
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            List<String> temp = new ArrayList<>();
//            temp.addAll(labelName);
//            labelName.clear();
//            mAdapter.notifyDataSetChanged();
//            labelName.addAll(temp);
//            mAdapter.notifyDataSetChanged();
//        }
//    }

    private FragmentLabelContentBinding mBinding;
    private List<String> labelName;
    private List<String> selectedList;
    private RecLabelAdapter mAdapter;
    private OperateMethod mOperateMethod;
    private WriteInfoPresenter mWriteInfoPresenter;

    public LabelContentFragment(List<String> labelName, List<String> sl, OperateMethod operateMethod){
        this.labelName = labelName;
        this.selectedList = sl;
        this.mOperateMethod = operateMethod;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentLabelContentBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        mWriteInfoPresenter = new WriteInfoPresenter();

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recLabelname.setLayoutManager(staggeredGridLayoutManager);

        mAdapter = new RecLabelAdapter(labelName, selectedList, new OperateMethod() {
            @Override
            public void onOperate(Object complete) {
                mOperateMethod.onOperate(complete);
            }
        }, new GetData<List<String>>() {
            @Override
            public List<String> getData() {
                return mWriteInfoPresenter.getAllSelectedLabel();
            }
        });

        mBinding.recLabelname.setAdapter(mAdapter);
        return view;
    }


    public void refreshSelectAllLabel(){
        labelName.add("测试");
        mAdapter.notifyDataSetChanged();
    }


}
