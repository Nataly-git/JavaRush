package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = buffer.readLine();
        buffer.close();

        int [] frequency = new int [256];
        try (FileInputStream input = new FileInputStream(fileName)) {
            while (input.available() > 0) {
                frequency[input.read()] += 1;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int byteCount : frequency) {
            if(byteCount > 0)
            min = Math.min(byteCount, min);
        }

        ArrayList<Integer> arrayMin = new ArrayList<>();
        for (int i = 0; i < frequency.length; i++) {
            if(frequency[i] == min){
                arrayMin.add(i);
            }
        }

        for(Integer integer : arrayMin) {
            System.out.print(integer + " ");
        }
    }
}
