package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Читайте с консоли имена файлов.
Если файла не существует (передано неправильное имя файла), то перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = null;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try (FileReader reader = new FileReader(fileName = buffer.readLine())) {

            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                break;
            }
        }
    }
}
