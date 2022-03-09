package com.javarush.task.task19.task1907;

import java.io.*;

/* 
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = buffer.readLine();
        }

        int count = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split("\\W");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("world"))
                        count++;
                }
            }
        }
        System.out.println(count);
    }
}
