package com.android.everyoneoncampus.view.comment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.everyoneoncampus.BaseActivity;
import com.android.everyoneoncampus.EocApplication;
import com.android.everyoneoncampus.EocTools;
import com.android.everyoneoncampus.databinding.ActivityCommentBinding;
import com.android.everyoneoncampus.model.entity.Comment;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.presenter.CommentPresenter;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {
    private ActivityCommentBinding mBinding;
    private CommentPresenter mCommentPresenter;
    private Things mIntentThings;
    private CommentAdapter mCommentAdapter;
    private List<Comment> mCommentList = new ArrayList<>();
    //启动
    public static void actionActivity(Context context, Things things){
        Intent intent = new Intent(context,CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("things",things);
        intent.putExtra("things",bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mCommentPresenter = new CommentPresenter(this);
        initView();
        initListener();
        mCommentPresenter.getThingsComment(mIntentThings.thingsID);
    }

    private void initListener() {
        mBinding.imgbtnLeftexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.btnAddCommentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cContent = mBinding.editAddComment.getText().toString();
                if(!cContent.isEmpty()){
                    Comment comment = new Comment();
                    comment.headPic = EocApplication.getUserInfo().headPic;
//                    comment.CDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").toString();
                    comment.CContent =cContent;
                    comment.userNicheng = EocApplication.getUserInfo().userNicheng;
                    comment.userID = EocApplication.getUserInfo().userID;
                    comment.thingsID = mIntentThings.thingsID;
                    mCommentList.add(comment);
                    mCommentAdapter.notifyDataSetChanged();
                    mCommentPresenter.addComment(comment);
                }else{
                    Toast.makeText(CommentActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        //获取things
        Intent intent = getIntent();
        Bundle intentThings = intent.getBundleExtra("things");
        mIntentThings = (Things)intentThings.getSerializable("things");

        if(mIntentThings != null){
            mBinding.txtCommentTitle.setText(mIntentThings.event);
            mBinding.imgUserHeadpic.setImageBitmap(EocTools.convertBitmap(mIntentThings.headPic));
            mBinding.txtThingsText.setText(mIntentThings.thingsContent);
            mBinding.txtReleaseDate.setText(mIntentThings.thingsDate);
            mBinding.txtUserName.setText(mIntentThings.userNicheng);

            if(mIntentThings.image != null){
                mBinding.imgThingsPic.setImageBitmap(EocTools.convertBitmap(mIntentThings.image));
            }else{
                mBinding.imgThingsPic.setVisibility(View.GONE);
            }
        }

        //recyclerview
        mCommentAdapter = new CommentAdapter(mCommentList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recCommentList.setLayoutManager(layoutManager);
        mBinding.recCommentList.setAdapter(mCommentAdapter);

    }

    public void hideLoading(){
        mBinding.txtLoading.setVisibility(View.GONE);
    }

    public void setCommentList(List<Comment> commentList){
        mCommentList.clear();
        mCommentList.addAll(commentList);
        mCommentAdapter.notifyDataSetChanged();
    }

    public void showCommentList(){
        mBinding.recCommentList.setVisibility(View.VISIBLE);
    }

    public void noComment(){
        mBinding.txtLoading.setText("暂无评论...");
    }



}