package com.javarush.task.task19.task1923;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со словами, разделенными пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1, abc3d или 564.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            FileWriter writer = new FileWriter(args[1]))
        {
            while (reader.ready()){
                String[] lines = reader.readLine().split(" ");
                for (int i = 0; i < lines.length; i++) {
                    if(lines[i].matches(".*\\d.*")){
                        writer.write(lines[i]);
                        writer.write(" ");
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
