package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> salary = new TreeMap();
        try(BufferedReader buffer = new BufferedReader(new FileReader(args[0])))
        {
            while (buffer.ready()){
                String line = buffer.readLine();
                String[] subline = line.split(" ");
                if(!salary.containsKey(subline[0])) {
                    salary.put(subline[0], Double.parseDouble(subline[1]));
                }
                else{
                    salary.replace(subline[0],salary.get(subline[0]) + Double.parseDouble(subline[1]));
                }
            }
        } catch (IOException ignore){

        }
        for(Map.Entry<String, Double> entries : salary.entrySet())
        {
            System.out.println(entries.getKey() + " " + entries.getValue());
        }
    }
}
