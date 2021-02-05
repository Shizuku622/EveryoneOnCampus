package com.android.everyoneoncampus.view.personinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.everyoneoncampus.databinding.FragmentLabelContentBinding;

import java.util.List;

public class LabelContentFragment extends Fragment {
    private FragmentLabelContentBinding mBinding;

    private List<String> labelName;

    public LabelContentFragment(List<String> labelName){
        this.labelName = labelName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentLabelContentBinding.inflate(inflater,container,false);
        View view = mBinding.getRoot();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        mBinding.recLabelname.setLayoutManager(staggeredGridLayoutManager);
        RecLabelAdapter adapter = new RecLabelAdapter(labelName);
        mBinding.recLabelname.setAdapter(adapter);
        return view;
    }
}
