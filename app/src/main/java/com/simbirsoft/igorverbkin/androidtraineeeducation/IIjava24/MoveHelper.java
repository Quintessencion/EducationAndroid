package com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24.Directions.DOWN;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24.Directions.LEFT;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24.Directions.RIGHT;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24.Directions.UP;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.MainActivity.TAG;

public class MoveHelper {

    private int step = 1;

    private int[] move(int[] location, Directions directions) {
        switch (directions) {
            case UP:
                location[1] += step;
                return location;
            case RIGHT:
                location[0] += step;
                return location;
            case DOWN:
                location[1] -= step;
                return location;
            case LEFT:
                location[0] -= step;
                return location;
            default:
                return new int[]{0};
        }
    }

    public void multiMove() {
        int[] location = {0, 0};
        Directions[] directions = {UP, UP, LEFT, DOWN, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};

        for (Directions d : directions) {
            location = move(location, d);
        }

        Log.d(TAG, "x = " + location[0] + ", y = " + location[1]);
    }
}
