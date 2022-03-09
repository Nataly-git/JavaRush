package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Ввести с консоли имя файла.
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = buffer.readLine();
        buffer.close();

        FileInputStream input = new FileInputStream(fileName);
        int max = input.read();
        while (input.available()>0) {
            int next = input.read();
            max = next > max ? next : max;
        }
        input.close();

        System.out.println(max);
    }
}
