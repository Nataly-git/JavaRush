package com.javarush.task.task18.task1810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки работы с файлами.
2.2 Выбросить исключение DownloadException.
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream input = null;
        try {
            do {
                input = new FileInputStream(buffer.readLine());
                if (input.available() < 1000) {
                    input.close();
                    throw new DownloadException();
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class DownloadException extends Exception {

    }
}
