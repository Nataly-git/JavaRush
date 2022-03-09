package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Charset windows = Charset.forName("Windows-1251");
        byte[] buffer = new byte[1000];
        try(FileInputStream inputStream = new FileInputStream(args[0]);
            FileOutputStream outputStream = new FileOutputStream(args[1]))
        {
            inputStream.read(buffer);
            String s = new String(buffer,windows);
            buffer = s.getBytes(StandardCharsets.UTF_8);
            outputStream.write(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
