package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Считать с консоли 2 имени файла.
Вывести во второй файл все символы с четным порядковым номером (нумерация начинается с 1).
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileNameInput = null;
        String fileNameOutput = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileNameInput = buffer.readLine();
            fileNameOutput = buffer.readLine();
        }

        try(FileReader input = new FileReader(fileNameInput);
            FileWriter output = new FileWriter(fileNameOutput))
        {
            while (input.ready()){
                input.read();
                output.write(input.read());
            }
        }
    }
}
