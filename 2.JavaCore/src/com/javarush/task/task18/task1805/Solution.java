package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
Ввести с консоли имя файла.
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран.
Закрыть поток ввода-вывода.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = buffer.readLine();
        buffer.close();

        Set<Integer> sorted = new TreeSet<>();
        try (FileInputStream input = new FileInputStream(fileName)) {
            while (input.available() > 0)
            sorted.add(input.read());
        }

        for(Integer integer : sorted) {
            System.out.print(integer + " ");
        }
    }
}
