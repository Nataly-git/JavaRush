package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла, удалить все знаки пунктуации, включая символы новой строки.
Результат вывести во второй файл.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        String fileNameInput = null;
        String fileNameOutput = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileNameInput = buffer.readLine();
            fileNameOutput = buffer.readLine();
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(fileNameInput));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameOutput)))
        {
            while (reader.ready()){
                String line = reader.readLine().replaceAll("[^\\s\\w]","");
                writer.write(line);
            }

        }
    }
}
