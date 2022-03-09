package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
Придумать механизм шифровки/дешифровки.
Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где:
fileName - имя файла, который необходимо зашифровать/расшифровать.
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования.
-e - ключ указывает, что необходимо зашифровать данные.
-d - ключ указывает, что необходимо расшифровать данные.
*/

public class Solution {
    public static void main(String[] args) {
        int password = 19;
        try (FileInputStream input = new FileInputStream(args[1]);
             FileOutputStream output = new FileOutputStream(args[2]))
        {
            while (input.available() > 0) {
               int res = input.read() ^ password;
               output.write(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
