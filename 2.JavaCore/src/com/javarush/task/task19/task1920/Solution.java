package com.javarush.task.task19.task1920;

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
Вывести в консоль имена в алфавитном порядке, у которых максимальная сумма.
Имена разделять пробелом либо выводить с новой строки.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> salary = new TreeMap<>();
        double max = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(args[0])))
        {
            while (reader.ready()){
                String[] line = reader.readLine().split(" ");
                double value = Double.parseDouble(line[1]);
                if(salary.containsKey(line[0])){
                    salary.replace(line[0], salary.get(line[0])+value);
                }
                else
                    salary.put(line[0], value);
                if(salary.get(line[0]) > max)
                    max = salary.get(line[0]);
            }
        } catch (IOException e){

        }
        for(Map.Entry<String,Double> entry : salary.entrySet()){
            if(entry.getValue() == max){
                System.out.println(entry.getKey());
            }
        }
    }
}
