package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.ui.activity;

import android.view.GestureDetector;
import android.view.MotionEvent;

public interface WrapperGestureDetector extends GestureDetector.OnGestureListener {
    @Override
    default boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    default void onShowPress(MotionEvent e) {

    }

    @Override
    default boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    default boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    default void onLongPress(MotionEvent e) {

    }

    @Override
    boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
