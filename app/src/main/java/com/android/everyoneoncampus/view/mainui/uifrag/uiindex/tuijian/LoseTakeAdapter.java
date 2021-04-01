package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.view.follow.FollowInfoActivity;

import java.util.List;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import io.reactivex.internal.schedulers.ImmediateThinScheduler;

public class LoseTakeAdapter extends RecyclerView.Adapter<LoseTakeAdapter.LoseTakeViewHolder> {

    private List<Things> mLostTakeThingsList;
    private Activity mActivity;
    private DataListener<Things> mThingsDataListener;

    public LoseTakeAdapter(List<Things> list, Activity activity){
        mLostTakeThingsList = list;
        mActivity = activity;
    }

    public LoseTakeAdapter(List<Things> list, Activity activity,DataListener<Things> dataListener){
        mLostTakeThingsList = list;
        mActivity = activity;
        mThingsDataListener = dataListener;
    }

    @NonNull
    @Override
    public LoseTakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lose_take,parent,false);
        return new LoseTakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoseTakeViewHolder holder, int position) {
        Things things = mLostTakeThingsList.get(position);
        //1是丢失，2是拾到
        if(things.event.equals("丢失")){
            //设置view颜色
            holder.mView.setBackgroundResource(R.color.tran_red);
        }
        if(things.event.equals("拾到")){
            holder.mView.setBackgroundResource(R.color.btn_green_color);
        }
        holder.txt_nicheng.setText(things.userNicheng);
        holder.txt_content.setText(things.thingsContent);
        if(things.Thingsimage != null){
            Bitmap pic = EocTools.convertBitmap(things.Thingsimage);
            holder.img_pic.setImageBitmap(pic);
        }else{
            holder.img_pic.setVisibility(View.GONE);
        }
        if(things.headPic != null){
            Bitmap headpic = EocTools.convertBitmap(things.headPic);
            holder.img_headpic.setImageBitmap(headpic);
        }else{
            holder.img_headpic.setImageResource(R.drawable.eoc_touxiang);
        }

        //发起聊天
        holder.img_lt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mLostTakeThingsList.get(position).userID.equals(EocApplication.getUserID())){
                    Intent intent = new Intent(mActivity,FollowInfoActivity.class);
                    intent.putExtra("userID",mLostTakeThingsList.get(position).userID);
                    mActivity.startActivity(intent);
                }else{
                    Toast.makeText(mActivity, "不能跟自己私聊", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mLostTakeThingsList.size();
    }

    class LoseTakeViewHolder extends RecyclerView.ViewHolder{
        TextView txt_content;
        TextView txt_nicheng;
        ImageView img_headpic;
        ImageView img_pic;
        ImageView img_lt_chat;
        View mView;

        public LoseTakeViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_content = itemView.findViewById(R.id.txt_lt_content);
            txt_nicheng = itemView.findViewById(R.id.txt_lt_user_incheng);
            img_lt_chat = itemView.findViewById(R.id.img_lt_chat);
            img_pic = itemView.findViewById(R.id.img_lt_pic);
            img_headpic = itemView.findViewById(R.id.img_lt_headpic);
            mView = itemView.findViewById(R.id.view_lt_color);
        }
    }

}
