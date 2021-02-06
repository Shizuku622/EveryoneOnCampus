package com.android.everyoneoncampus.view.personinfo;

import android.content.Intent;
import android.text.Layout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.OperateMethod;

import org.w3c.dom.Text;

import java.util.List;

public class LabelDeleteAdapter extends RecyclerView.Adapter<LabelDeleteAdapter.lDeleteViewHolder> {


    class lDeleteViewHolder extends RecyclerView.ViewHolder{
        public TextView txtDeleteLabel;
        public lDeleteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDeleteLabel = itemView.findViewById(R.id.txt_delete_lablename);
        }
    }

    private List<String> mSelectedList;
    private List<LabelContentFragment> mFragments;
    private OperateMethod mOperateMethod;
    private LabelFragment mLabelFragment;
    private List<LabelContentFragment> mLabelContentFragment;

    public LabelDeleteAdapter(List<String> sl,List<LabelContentFragment> fragments){
        mSelectedList = sl;
        mFragments = fragments;
    }
    public LabelDeleteAdapter(List<String> sl,OperateMethod om){
        mSelectedList = sl;
        mOperateMethod = om;
    }public LabelDeleteAdapter(List<String> sl,LabelFragment lf) {
        mSelectedList = sl;
        mLabelFragment = lf;
    }
    public LabelDeleteAdapter(List<String> sl,LabelFragment lf,List<LabelContentFragment> lcf) {
        mSelectedList = sl;
        mLabelFragment = lf;
        mLabelContentFragment = lcf;
    }

    @NonNull
    @Override
    public lDeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delete_label,parent,false);
        lDeleteViewHolder lDeleteViewHolder = new lDeleteViewHolder(view);
        return lDeleteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull lDeleteViewHolder holder, int position) {
        holder.txtDeleteLabel.setText(mSelectedList.get(position)+"  X");
        holder.txtDeleteLabel.setOnClickListener(v->{
            mLabelFragment.deleteLabel(mSelectedList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mSelectedList.size();
    }


}
