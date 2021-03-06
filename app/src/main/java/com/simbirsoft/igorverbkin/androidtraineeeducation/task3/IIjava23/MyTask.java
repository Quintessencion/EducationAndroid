package com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava23;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.MainActivity.TAG;

public class MyTask implements Runnable {

    private String message;
    private Printable myClosure;

    public MyTask(String message) {
        this.message = message;
        myClosure = s -> Log.d(TAG, s);
    }

    @Override
    public void run() {
        myClosure.print(message);
    }
}
