package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.viewpages;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.Things;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        holder.userNicheng.setText(things.userNicheng);
        holder.thingsContent.setText(things.thingsContent);
        holder.thingsDate.setText(things.thingsDate.substring(5,16));
        holder.event.setText(things.event);

        //设置头像
        Bitmap hpic = EocTools.convertByteBitmap(mThingsList.get(position).headPic);
        holder.headPic.setImageBitmap(hpic);

        //设置事件图片
        if(mThingsList.get(position).image == null){
            holder.thingsImage.setVisibility(View.GONE);
        }else{
            Bitmap tImage = EocTools.convertByteBitmap(mThingsList.get(position).image);
            holder.thingsImage.setImageBitmap(tImage);
        }

    }

    @Override
    public int getItemCount() {
        return mThingsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userNicheng;
        TextView event;
        TextView thingsContent;
        TextView thingsDate;
        Button follow;
        CircleImageView headPic;
        ImageView thingsImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNicheng = itemView.findViewById(R.id.txt_user_name);
            thingsDate = itemView.findViewById(R.id.txt_release_date);
            thingsContent = itemView.findViewById(R.id.txt_things_text);
            event = itemView.findViewById(R.id.txt_event);
            follow = itemView.findViewById(R.id.btn_tuijian_info_follow);
            headPic = itemView.findViewById(R.id.img_user_headpic);
            thingsImage = itemView.findViewById(R.id.img_things_pic);
        }
    }

}
