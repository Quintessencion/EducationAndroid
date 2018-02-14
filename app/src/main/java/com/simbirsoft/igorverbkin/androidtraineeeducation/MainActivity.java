package com.simbirsoft.igorverbkin.androidtraineeeducation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava23.MyTask;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava23.PrintHelper;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava24.MoveHelper;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava25.Circle;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava25.Rectangle;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava25.Shape;
import com.simbirsoft.igorverbkin.androidtraineeeducation.IIjava25.Square;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // II. Java. Часть 2. 3
        new PrintHelper().repeatTask(10, new MyTask());

        // II. Java. Часть 2. 4
        new MoveHelper().multiMove();

        // II. Java. Часть 2. 5
        Shape shape = new Rectangle(3, 5);
        printParametersShapes(shape);
        shape = new Square(10);
        printParametersShapes(shape);
        shape = new Circle(6);
        printParametersShapes(shape);
    }

    private void printParametersShapes(Shape shape) {
        shape.perimeter();
        shape.area();
    }
}
