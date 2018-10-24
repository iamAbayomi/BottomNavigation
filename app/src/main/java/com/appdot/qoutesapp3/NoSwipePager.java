package com.appdot.qoutesapp3;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoSwipePager extends ViewPager {
    private boolean enabled;

    public NoSwipePager(Context context, AttributeSet attrs) {
        super( context, attrs);
        this.enabled = true;

        // Required empty public constructor
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
