package com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25;

import android.util.Log;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.MainActivity.TAG;

public class Circle implements Shape {

    private double diameter;

    public Circle(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public void perimeter() {
        double perimeter = Math.PI * diameter;
        Log.d(TAG, "Circumference = " + perimeter);
    }

    @Override
    public void area() {
        double area = (Math.PI * Math.pow(diameter / 2, 2));
        Log.d(TAG, "Area of the circle = " + area);
    }
}
