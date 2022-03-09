package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
В методе main подмени объект System.out написанной тобой ридер-оберткой.
Твоя ридер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а.
Вызови готовый метод printSomething(), воспользуйся testString.
Верни переменной System.out первоначальный поток.

Рекламный текст: "JavaRush - курсы Java онлайн"
*/

public class Solution {
    public static TestString testString = new TestString();
    static String advertising = "JavaRush - курсы Java онлайн";

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        System.setOut(stream);
        testString.printSomething();
        String[] out = output.toString().split("\\n");
        System.setOut(console);
        for (int i = 0; i < out.length; i+=2) {
            System.out.println(out[i]);
            if(out.length>i+1) {
                System.out.println(out[i + 1]);
                System.out.println(advertising);
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
