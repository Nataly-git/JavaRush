package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
Дана стандартная реализация методологии wait-notify.
Почитай про CountDownLatch и перепиши тело метода someMethod используя поле latch.
Весь лишний код удали из класса.
*/

public class Solution {
    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
        latch.await();
        retrieveValue();
        latch.countDown();
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {

    }
}
