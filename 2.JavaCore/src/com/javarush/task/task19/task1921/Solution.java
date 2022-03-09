package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String.
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами.
Заполнить список PEOPLE используя данные из файла.
Закрыть потоки.
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(args[0]))){
            while (buffer.ready()){
                String[]line = buffer.readLine().split(" ");
                int year = Integer.parseInt(line[line.length-1]);
                int month = Integer.parseInt(line[line.length-2]) - 1;
                int day = Integer.parseInt(line[line.length-3]);
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < line.length-3; i++) {
                    name.append(line[i]).append(" ");
                }
                Calendar calendar = new GregorianCalendar(year,month,day);
                Date date = calendar.getTime();
                PEOPLE.add(new Person(name.toString().trim(),date));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
