package com.android.everyoneoncampus.view.mainui.uifrag.uiindex.tuijian;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.view.comment.CommentActivity;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.MyViewHolder> {

    List<Things> mThingsList;
    Activity mActivity;

    public TuiJianAdapter(List<Things> list){
        mThingsList = list;
    }

    public TuiJianAdapter(List<Things> list, Activity activity){
        mThingsList = list;
        mActivity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tuijian_info,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    private static final String TAG = "TuiJianAdapter";
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Things things = mThingsList.get(position);
        holder.userNicheng.setText(things.userNicheng);
        holder.thingsContent.setText(things.thingsContent);
        holder.thingsDate.setText(things.thingsDate.substring(5,16));
        holder.event.setText(things.event);

        if(things.likeMark.equals("0")){
            holder.likeNum.setText("0");
        }else{
            holder.likeNum.setText(things.likeNum);
        }
        if(things.commentMark.equals("0")){
            holder.commentNum.setText("0");
        }else{
            holder.commentNum.setText(things.commentNum);
        }

        //设置头像
        Bitmap hpic = EocTools.convertBitmap(mThingsList.get(position).headPic);
        holder.headPic.setImageBitmap(hpic);

        //设置事件图片
        if(mThingsList.get(position).Thingsimage == null){
            holder.thingsImage.setVisibility(View.GONE);
        }else{
            Bitmap tImage = EocTools.convertBitmap(mThingsList.get(position).Thingsimage);
            holder.thingsImage.setImageBitmap(tImage);
        }

        //点击评论
        holder.llayoutComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击");
                if(mActivity != null){
                    CommentActivity.actionActivity(mActivity,mThingsList.get(position));
                }
            }
        });
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
        LinearLayout llayoutComment;
        TextView commentNum;
        TextView likeNum;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNicheng = itemView.findViewById(R.id.txt_user_name);
            thingsDate = itemView.findViewById(R.id.txt_release_date);
            thingsContent = itemView.findViewById(R.id.txt_things_text);
            event = itemView.findViewById(R.id.txt_event);
            follow = itemView.findViewById(R.id.btn_tuijian_info_follow);
            headPic = itemView.findViewById(R.id.img_user_headpic);
            thingsImage = itemView.findViewById(R.id.img_things_pic);
            llayoutComment = itemView.findViewById(R.id.llayout_comment);
            commentNum = itemView.findViewById(R.id.txt_comment_num);
            likeNum = itemView.findViewById(R.id.txt_like_num);
        }
    }

}
