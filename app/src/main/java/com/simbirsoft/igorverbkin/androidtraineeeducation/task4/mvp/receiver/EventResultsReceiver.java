package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.mvp.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class EventResultsReceiver extends ResultReceiver {

    private Receiver receiver;

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle data);
    }

    public EventResultsReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
    }
}
