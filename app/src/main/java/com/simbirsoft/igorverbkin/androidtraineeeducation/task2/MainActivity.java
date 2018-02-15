package com.simbirsoft.igorverbkin.androidtraineeeducation.task2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.simbirsoft.igorverbkin.androidtraineeeducation.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2
        final double a = 0.504829;
        final double b = 10.101303471;
        Log.d(TAG, "Average: " + (a + b) / 2);

        // 3
        new User("Андрей", "Лохматов", "Full name: [%s] [%s]").printToConsole();

        // 4
        Log.d(TAG, "Fibonacci: " + numberFibonacci(15));

        // 5
        int[] array = {2, 5, 1, 8, 0, 4, 3, 7, 6, 9};
        Log.d(TAG, "Bubble sort: " + Arrays.toString(bubbleSort(array)));

        // 6
        Log.d(TAG, "Adding a unit to a number in a row: " + addOneToString("abc123"));
    }

    // 3
    class User {

        private String firstName;
        private String lastName;
        private String template;

        public User(String firstName, String lastName, String template) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.template = template;
        }

        public void printToConsole() {
            Log.d(TAG, String.format(template, firstName, lastName));
        }
    }

    // 4
    public String numberFibonacci(int n) {
        int first = 0;
        int second = 1;
        int temp;

        StringBuilder sb = new StringBuilder(first + " " + second + " ");
        for (int i = 0; i < n - 2; i++) {
            temp = first + second;
            first = second;
            second = temp;
            sb.append(temp).append(" ");
        }
        return sb.toString();
    }

    // 5
    private int[] bubbleSort(int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            for (int j = ar.length - 1; j > i; j--) {
                if (ar[j - 1] > ar[j]) {
                    int temp = ar[j];
                    ar[j] = ar[j - 1];
                    ar[j - 1] = temp;
                }
            }
        }
        return ar;
    }

    // 6
    public String addOneToString(String input) {
        StringBuilder totalNumber = new StringBuilder();
        StringBuilder totalString = new StringBuilder();
        int result = 0;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                totalNumber.append(input.charAt(i));
            } else {
                totalString.append(input.charAt(i));
            }
        }
        String resultNumber = totalNumber.toString();
        if (!TextUtils.isEmpty(resultNumber)) {
            result = Integer.parseInt(resultNumber);
        }

        return totalString.toString() + (result + 1);
    }
}
