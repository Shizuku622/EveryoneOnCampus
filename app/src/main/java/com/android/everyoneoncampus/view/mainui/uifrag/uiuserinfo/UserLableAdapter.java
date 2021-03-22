package com.android.everyoneoncampus.view.mainui.uifrag.uiuserinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;

import java.util.List;

public class UserLableAdapter extends RecyclerView.Adapter<UserLableAdapter.MyLableViewHolder> {

    private List<String> mLableList;

    public UserLableAdapter(List<String> list){
        mLableList = list;
    }

    @NonNull
    @Override
    public MyLableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label,parent,false);
        MyLableViewHolder myLableViewHolder = new MyLableViewHolder(view);
        return myLableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyLableViewHolder holder, int position) {
        holder.label.setText(mLableList.get(position));
    }

    @Override
    public int getItemCount() {
        return mLableList.size();
    }

    class MyLableViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        public MyLableViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.txt_lablename);
        }
    }
}
