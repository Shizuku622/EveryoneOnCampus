package com.android.everyoneoncampus.model.entity;

import android.graphics.text.MeasuredText;

import java.util.List;

public class Canteen {
    private String mCanteenName = "";
    private String mCanteenAddress = "";
    private List<Integer> mCanteenPic;

    public Canteen(){

    }

    public Canteen(String name,String address,List<Integer> pic){
        mCanteenName = name;
        mCanteenAddress = address;
        mCanteenPic = pic;
    }

    public String getCanteenName() {
        return mCanteenName;
    }

    public void setCanteenName(String canteenName) {
        mCanteenName = canteenName;
    }

    public String getCanteenAddress() {
        return mCanteenAddress;
    }

    public void setCanteenAddress(String canteenAddress) {
        mCanteenAddress = canteenAddress;
    }

    public List<Integer> getCanteenPic() {
        return mCanteenPic;
    }

    public void setCanteenPic(List<Integer> canteenPic) {
        mCanteenPic = canteenPic;
    }
}
