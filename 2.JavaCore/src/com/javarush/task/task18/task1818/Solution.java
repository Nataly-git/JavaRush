package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Считать с консоли 3 имени файла.
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        String first = null;
        String second = null;
        String third = null;

        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            first = buffer.readLine();
            second = buffer.readLine();
            third = buffer.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedReader buffer = new BufferedReader(new FileReader(second));
            BufferedReader buffer2 = new BufferedReader(new FileReader(third));
            BufferedWriter writer = new BufferedWriter(new FileWriter(first, true)))
        {
            while (buffer.ready()){
                String i = buffer.readLine();
                writer.write(i);
            }
            while (buffer2.ready()){
                String i = buffer2.readLine();
                writer.write(i);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
