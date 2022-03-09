package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words.
Закрыть потоки.
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        String fileName = null;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                int count = 0;
                String line = reader.readLine();
                String[] subline = line.split(" ");
                for (String word : words) {
                    for (int i = 0; i < subline.length; i++) {
                        if(word.equals(subline[i])){
                            count++;
                        }
                    }
                }
                if (count == 2) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
