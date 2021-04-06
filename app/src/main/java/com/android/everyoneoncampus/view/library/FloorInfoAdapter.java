package com.android.everyoneoncampus.view.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.bumptech.glide.load.model.stream.StreamStringLoader;

import java.util.List;

public class FloorInfoAdapter extends RecyclerView.Adapter<FloorInfoAdapter.ViewHolder> {

    private List<String>  mAreaList;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mAreaName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAreaName = itemView.findViewById(R.id.txt_area_name);
        }
    }

    public FloorInfoAdapter(List<String> list){
        mAreaList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mAreaName.setText(mAreaList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAreaList.size();
    }

}
