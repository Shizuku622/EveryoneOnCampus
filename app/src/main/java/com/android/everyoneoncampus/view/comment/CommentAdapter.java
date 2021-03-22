package com.android.everyoneoncampus.view.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.entity.Comment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {


    private List<Comment> mCommentList ;


    public CommentAdapter(List<Comment> comments){
        this.mCommentList = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = mCommentList.get(position);
        if(comment.headPic != null){
            holder.headPic.setImageBitmap(EocTools.convertBitmap(comment.headPic));
        }else{
            holder.headPic.setImageResource(R.drawable.eoc_touxiang);
        }

        holder.txtCommentUserName.setText(comment.userNicheng);
        holder.txtCommentDate.setText(comment.CDate);
        holder.txtCommentContent.setText(comment.CContent);

    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        CircleImageView headPic;
        TextView txtCommentUserName;
        TextView txtCommentDate;
        TextView txtCommentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            headPic = itemView.findViewById(R.id.img_comment_user_headpic);
            txtCommentUserName = itemView.findViewById(R.id.txt_comment_user_name);
            txtCommentDate = itemView.findViewById(R.id.txt_comment_date);
            txtCommentContent = itemView.findViewById(R.id.txt_comment_content);
        }
    }

}
