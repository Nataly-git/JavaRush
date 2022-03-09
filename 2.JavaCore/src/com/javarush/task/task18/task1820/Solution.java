package com.javarush.task.task18.task1820;

import java.io.*;

/*
Округление чисел
Считать с консоли 2 имени файла.
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415.
Округлить числа до целых и записать через пробел во второй файл.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();


        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName1));
             FileWriter writer = new FileWriter(fileName2)) {
            while (buffer.ready()) {
                String line = buffer.readLine();
                String[] allNumbers = line.split(" ");
                for (int i = 0; i < allNumbers.length; i++) {
                    double num = Double.parseDouble(allNumbers[i]);
                    int number = (int) Math.round(num);
                    writer.write(number + " ");
                }
            }
        }
    }
}
