package com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.MainActivity.TAG;

public class Rectangle implements Shape {

    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public void perimeter() {
        double perimeter = length * 2 + width * 2;
        Log.d(TAG, "Perimeter of the rectangle = " + perimeter);
    }

    @Override
    public void area() {
        double area = length * width;
        Log.d(TAG, "Area of the rectangle = " + area);
    }
}
