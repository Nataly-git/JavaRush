package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Считай с консоли имя файла.
В методе main подмени объект System.out написанной тобой ридер-оберткой по аналогии с лекцией.
Твоя ридер-обертка должна выводить весь текст и на консоль и в файл, имя которого ты считал.
Вызови готовый метод printSomething(), воспользуйся testString.
Верни переменной System.out первоначальный поток.
Закрой поток файла.
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException{
        String fileName = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = reader.readLine();
        }

        PrintStream cons = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        System.setOut(stream);
        testString.printSomething();
        try(FileOutputStream inputStream = new FileOutputStream(fileName)){
            output.writeTo(inputStream);
        }
        System.setOut(cons);
        System.out.println(output);
        output.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

