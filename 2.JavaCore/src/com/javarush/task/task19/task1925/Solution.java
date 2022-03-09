package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
В метод main первым параметром приходит путь к файлу1, вторым - путь к файлу2.
Файл1 содержит слова, разделенные пробелом или переводом строки (в файле может быть несколько строк).
Все, что не относится к пробелу или переводу строки, разделителем не считать.
Записать в одну строку через запятую в Файл2 слова, длина которых строго больше 6.
В конце файла2 запятой не должно быть.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            FileWriter writer = new FileWriter(args[1])) {
            while (reader.ready()){
                list.add(reader.readLine());
            }
            StringBuilder sb = new StringBuilder();
            for (String line : list) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if(words[i].length() > 6) {
                        sb.append(words[i]).append(",");
                    }
                }
            }
            writer.write(sb.deleteCharAt(sb.length()-1).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
