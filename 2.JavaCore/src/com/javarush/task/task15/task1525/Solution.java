package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Инициализируй переменную Statics.FILE_NAME полным путем к файлу с данными, который содержит несколько строк.
В статическом блоке считай из файла с именем Statics.FILE_NAME все строки и добавь их по отдельности в List lines.
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(lines);
    }

    static{
        try (BufferedReader buffer = new BufferedReader(new FileReader(Statics.FILE_NAME)))
        {
            while (buffer.ready()){
                String line = buffer.readLine();
                lines.add(line);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
