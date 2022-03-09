package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать большую часть.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        String [] array = new String[3];
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            for (int i = 0; i < array.length; i++) {
                array[i] = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileInputStream input = new FileInputStream(array[0]);
            FileOutputStream outputSecond = new FileOutputStream(array[1]);
            FileOutputStream outputThird = new FileOutputStream(array[2]))
        {
            int buffer = input.available();
            int length = buffer % 2 == 0 ? buffer / 2 : (buffer / 2) + 1 ;
                for (int i = 0; i < length; i++) {
                    int data = input.read();
                    outputSecond.write(data);
                }
                for (int i = length; i < buffer; i++) {
                    int data = input.read();
                    outputThird.write(data);
                }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
