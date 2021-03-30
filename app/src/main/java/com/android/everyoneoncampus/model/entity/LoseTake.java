package com.android.everyoneoncampus.model.entity;

import java.util.ArrayList;
import java.util.List;

public class LoseTake {
    private List<Things> mLose;
    private List<Things> mTake;
    public  LoseTake(){
        mLose = new ArrayList<>();
        mTake = new ArrayList<>();
    }

    public List<Things> getLose() {
        return mLose;
    }

    public void setLose(List<Things> lose) {
        mLose = lose;
    }

    public List<Things> getTake() {
        return mTake;
    }

    public void setTake(List<Things> take) {
        mTake = take;
    }
}
