package com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava23;

public class PrintHelper {

    public void repeatTask(int times, Runnable task) {

        Thread thread;

        for (int i = 0; i < times; i++) {
            thread = new Thread(task);
            thread.start();
        }
    }
}
