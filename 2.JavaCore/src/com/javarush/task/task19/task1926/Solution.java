package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
1. Считать с консоли имя файла. Считать содержимое файла.
2. Для каждой строки в файле:
2.1. переставить все символы в обратном порядке.
2.2. вывести на экран.
3. Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        String fileName = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = buffer.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }

        StringBuilder fileText = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while (reader.ready()){
                fileText = new StringBuilder().append(reader.readLine());
                System.out.println(fileText.reverse());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
