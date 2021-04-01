package com.android.everyoneoncampus.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoSwipeViewPager extends ViewPager {
    private boolean mCanSwipe = true;
    public NoSwipeViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mCanSwipe && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mCanSwipe && super.onInterceptTouchEvent(ev);
    }

    public void setCanSwipe(boolean canSwipe) {
        mCanSwipe = canSwipe;
    }
}
