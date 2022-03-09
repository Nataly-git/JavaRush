package com.javarush.task.task18.task1816;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
В метод main первым параметром приходит путь к файлу.
Посчитать количество символов в файле, которые соответствуют буквам английского алфавита.
Вывести на экран число (количество символов).
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) throws Exception{
        int count = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        try(BufferedReader buffer = new BufferedReader(new FileReader(args[0]))) {
            while (buffer.ready()) {
                Matcher matcher = pattern.matcher(buffer.readLine());
                while (matcher.find()){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
