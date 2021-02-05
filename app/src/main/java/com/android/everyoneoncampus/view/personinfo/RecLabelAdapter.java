package com.android.everyoneoncampus.view.personinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;

import java.util.List;

public class RecLabelAdapter extends RecyclerView.Adapter<RecLabelAdapter.LableViewHolder> {

    class LableViewHolder extends RecyclerView.ViewHolder {
        public  TextView lableName ;
        public LableViewHolder(@NonNull View itemView) {
            super(itemView);
            lableName = itemView.findViewById(R.id.txt_lablename);
        }
    }

    private List<String> labelNameList;

    public RecLabelAdapter(List<String> list){
        this.labelNameList = list;
    }

    @NonNull
    @Override
    public LableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label,parent,false);
        LableViewHolder lableViewHolder = new LableViewHolder(view);
        return lableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LableViewHolder holder, int position) {
        holder.lableName.setText(labelNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return labelNameList.size();
    }

}
