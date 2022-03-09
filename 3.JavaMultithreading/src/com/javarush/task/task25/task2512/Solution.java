package com.javarush.task.task25.task2512;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 
В классе Solution реализуй интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений, начиная с самого вложенного.

Пример исключения:
new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))

Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LinkedList<Throwable> throwables = new LinkedList<>();
        t.interrupt();
        while (e != null){
            throwables.add(0, e);
            e = e.getCause();
        }
        for(Throwable thr : throwables) {
            System.out.println(thr);
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
