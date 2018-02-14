package com.simbirsoft.igorverbkin.androidtraineeeducation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "task";

    // 4
    private int first = 0;
    private int second = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2
        final double a = 0.5;
        final double b = 10.101;
        double average = (a + b) / 2;

        // 4
        int[] array = {2, 5, 1, 8, 0, 4, 3, 7, 6, 9};
        int[] sortArray = bubbleSort(array);
    }

    // 3
    class User {

        private String firstName;
        private String lastName;
        private String template;

        public User() {
            firstName = "Андрей";
            lastName = "Лохматов";
            template = "Full name: [%s] [%s]";
        }

        public void printToConsole() {
            Log.d(TAG, String.format(template, firstName, lastName));
        }
    }

    // 4
    public String numberFibonacci() {
        StringBuilder sb = new StringBuilder(first + " " + second + " ");
        for (int i = 0; i < 13; i++) {
            sb.append(getNextNumb()).append(" ");
        }
        return sb.toString();
    }

    // 4
    public int getNextNumb() {
        int temp = first + second;
        first = second;
        second = temp;
        return temp;
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
        if (!totalNumber.toString().isEmpty()) {
            result = Integer.parseInt(totalNumber.toString());
        }

        return totalString.toString() + ++result;
    }
}
