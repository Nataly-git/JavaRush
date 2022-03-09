package com.javarush.task.task15.task1515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
В статическом блоке считать с консоли А и В — две переменные с типом int.
Обработать IOException в блоке catch.
Закрыть поток ввода методом close().
*/

public class Solution {
    public static int A;
    public static int B;

    static {
        BufferedReader buffer = null;
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            buffer = new BufferedReader(reader);
            A = Integer.parseInt(buffer.readLine());
            B = Integer.parseInt(buffer.readLine());
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
            }
        }
    }

    public static final int MIN = min(A, B);

    public static void main(String[] args) {
        System.out.println(MIN);
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }
}
