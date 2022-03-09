package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Алгоритмы-числа
*/

public class Solution {

    public static long[] getNumbers(long N) {
        ArrayList<Long> arr = new ArrayList<>();

        for (long i = 0; i < N; i++) {
            long num = 0;
            int M = String.valueOf(i).length();
            for (int j = 0; j < M; j++) {
               num += Math.pow(Character.getNumericValue(String.valueOf(i).charAt(j)),M);
           }
            if(num == i){
                arr.add(i);
            }
        }

        long[] result = new long[arr.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr.get(i);
        }
        arr = null;
        return result;
    }


    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
