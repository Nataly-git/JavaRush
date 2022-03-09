package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        String fileNameInput = null;
        String fileNameOutput = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileNameInput = buffer.readLine();
            fileNameOutput = buffer.readLine();
        }

        Pattern pattern = Pattern.compile("\\b\\d+\\b");

        try(BufferedReader reader = new BufferedReader(new FileReader(fileNameInput));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameOutput)))
        {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split(" ");

                for (int i = 0; i < words.length; i++) {
                    Matcher matcher = pattern.matcher(words[i]);
                    if (matcher.find()) {
                        writer.write(words[i] + " ");
                    }
                }
            }
        }
    }
}
