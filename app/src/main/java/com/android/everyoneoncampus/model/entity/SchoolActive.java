package com.android.everyoneoncampus.model.entity;

import com.android.everyoneoncampus.view.mainui.MainUIActivity;
import com.android.everyoneoncampus.view.schoolactivity.SchoolActivity;

import java.io.PipedReader;

public class SchoolActive {
    private String mTitle;
    private String mContent;
    private int mSchoolBadge;

    public SchoolActive(String t,String c,int img){
        mTitle = t;
        mContent = c;
        mSchoolBadge = img;
    }

    public int getSchoolBadge() {
        return mSchoolBadge;
    }

    public void setSchoolBadge(int schoolBadge) {
        mSchoolBadge = schoolBadge;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
