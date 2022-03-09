package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = buffer.readLine();
        buffer.close();

        FileInputStream input = new FileInputStream(fileName);
        int min = input.read();
        while (input.available() > 0) {
            int next = input.read();
            min = next < min ? next : min;
        }
        input.close();

        System.out.println(min);
    }
}
