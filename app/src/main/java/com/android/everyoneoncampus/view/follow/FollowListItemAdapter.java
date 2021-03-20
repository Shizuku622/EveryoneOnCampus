package com.android.everyoneoncampus.view.follow;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.User;

import org.w3c.dom.Text;

import java.lang.invoke.CallSite;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowListItemAdapter extends RecyclerView.Adapter<FollowListItemAdapter.FollowItemViewHolder> {
    private List<User> mUserList;
    private Activity mActivity;
    public FollowListItemAdapter(List<User> list, Activity activity){
        mUserList = list;
        mActivity = activity;
    }

    @NonNull
    @Override
    public FollowItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followlist,parent,false);
        FollowItemViewHolder viewHolder = new FollowItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FollowItemViewHolder holder, int position) {
        User temp = mUserList.get(position);
//        holder.mHeadPic.setImageBitmap(EocTools.convertByteBitmap(temp.headPic));
        holder.mNiCheng.setText(temp.userNicheng);
        holder.mQianMing.setText(temp.userAutograph);
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(mActivity,FollowInfoActivity.class);
            intent.putExtra("follow_userID",temp.userID);
            mActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class FollowItemViewHolder extends RecyclerView.ViewHolder{
        CircleImageView mHeadPic;
        TextView mNiCheng;
        TextView mQianMing;
        public FollowItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeadPic = itemView.findViewById(R.id.img_followlist_headpic);
            mNiCheng = itemView.findViewById(R.id.txt_followlist_nicheng);
            mQianMing = itemView.findViewById(R.id.txt_followlist_qianming);
        }
    }

}
