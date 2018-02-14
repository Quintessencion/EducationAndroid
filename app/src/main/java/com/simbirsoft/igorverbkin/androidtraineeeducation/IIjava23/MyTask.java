package com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava23;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.MainActivity.TAG;

public class MyTask implements Runnable {

    private Printable myClosure;

    public MyTask() {
        myClosure = s -> Log.d(TAG, s);
    }

    @Override
    public void run() {
        myClosure.print("I love java 10 раз");
    }
}
