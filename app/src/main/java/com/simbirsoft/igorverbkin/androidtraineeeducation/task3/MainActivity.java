package com.simbirsoft.igorverbkin.androidtraineeeducation.task3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava23.MyTask;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava23.PrintHelper;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.Directions;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.MoveHelper;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25.Circle;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25.Rectangle;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25.Shape;
import com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava25.Square;

import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.Directions.DOWN;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.Directions.LEFT;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.Directions.RIGHT;
import static com.simbirsoft.igorverbkin.androidtraineeeducation.task3.IIjava24.Directions.UP;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // II. Java. Часть 2. 3
        new PrintHelper().repeatTask(10, new MyTask("I love java 10 раз"));

        // II. Java. Часть 2. 4
        Directions[] directions = {UP, UP, LEFT, DOWN, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
        new MoveHelper().multiMove(directions);

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
