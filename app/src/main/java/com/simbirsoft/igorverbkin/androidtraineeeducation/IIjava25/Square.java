package com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava25;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.MainActivity.TAG;

public class Square implements Shape {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public void perimeter() {
        double perimeter = length * 4;
        Log.d(TAG, "Perimeter of the square = " + perimeter);
    }

    @Override
    public void area() {
        double area = Math.pow(length, 2);
        Log.d(TAG, "Area of the square = " + area);
    }
}