package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
С консоли считать имя файла.
Посчитать в файле количество символов ',', количество вывести на консоль.
Закрыть потоки.
Подсказка:
нужно сравнивать с ascii-кодом символа ','.
*/

public class Solution {
    public static void main(String[] args) {
        String fileName = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        try(FileInputStream input = new FileInputStream(fileName))
        {
            while(input.available() > 0) {
                if(input.read() == 44){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
