package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.Things;

import java.util.List;

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.MyViewHolder> {

    List<Things> mThingsList;

    public TuiJianAdapter(List<Things> list){
        mThingsList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tuijian_info,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Things things = mThingsList.get(position);
        holder.userName.setText(things.userName);
        holder.thingsText.setText(things.thingsText);
        holder.realaseDate.setText(things.date);
        holder.event.setText(things.event);
    }

    @Override
    public int getItemCount() {
        return mThingsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView realaseDate;
        TextView thingsText;
        TextView event;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.txt_user_name);
            realaseDate = itemView.findViewById(R.id.txt_release_date);
            thingsText = itemView.findViewById(R.id.txt_things_text);
            event = itemView.findViewById(R.id.txt_event);
        }
    }

}
