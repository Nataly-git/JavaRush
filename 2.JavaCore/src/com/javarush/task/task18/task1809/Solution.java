package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* 
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {
        String file1 = null;
        String file2 = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            file1 = buffer.readLine();
            file2 = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> list = new ArrayList<>();
        try(FileInputStream input = new FileInputStream(file1);
            FileOutputStream output = new FileOutputStream(file2))
        {
            while (input.available() > 0) {
                list.add(input.read());
            }
            Collections.reverse(list);
            for(Integer num : list) {
                output.write(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
