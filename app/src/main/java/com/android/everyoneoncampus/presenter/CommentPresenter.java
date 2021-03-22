package com.android.everyoneoncampus.presenter;

import android.widget.Toast;

import androidx.core.view.TintableBackgroundView;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.entity.Comment;
import com.android.everyoneoncampus.model.modelapi.MySQLModel;
import com.android.everyoneoncampus.view.comment.CommentActivity;

import java.util.List;

public class CommentPresenter {
    private MySQLModel mMySQLModel = new MySQLModel();
    private CommentActivity mCommentActivity ;

    public CommentPresenter(CommentActivity commentActivity){
        this.mCommentActivity = commentActivity;
    }

    //添加评论
    public void addComment(Comment comment){
        mMySQLModel.insertThingsCommentApi(comment, new DataListener<Integer>() {
            @Override
            public void onComplete(Integer result) {
                if(result == 1){
                    Toast.makeText(mCommentActivity, "评论成功！", Toast.LENGTH_SHORT).show();
                }else if (result == 2 || result == 3){
                    Toast.makeText(mCommentActivity, "评论失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //获得评论
    public void getThingsComment(String thingsID){
        mMySQLModel.getThingsCommentApi(thingsID, new DataListener<List<Comment>>() {
            @Override
            public void onComplete(List<Comment> result) {
                if(result.size() != 0){
                    mCommentActivity.setCommentList(result);
                    mCommentActivity.hideLoading();
                    mCommentActivity.showCommentList();
                }else{
                    mCommentActivity.noComment();
                }
            }
        });
    }


}
