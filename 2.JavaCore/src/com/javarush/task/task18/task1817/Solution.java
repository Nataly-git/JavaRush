package com.javarush.task.task18.task1817;

import java.io.FileReader;
import java.io.IOException;

/* 
В метод main первым параметром приходит путь к файлу.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45.
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой до ближайшего.
4. Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        int count = 0;
        int countS = 0;
        try(FileReader reader = new FileReader(args[0]))
        {
            while (reader.ready()){
               char ch = (char) reader.read();
                if(ch == ' '){
                    countS++;
                }
                count++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        double result = (double) countS / count * 100;
        System.out.printf("%.2f" ,result);
    }
}
