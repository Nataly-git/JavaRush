package com.javarush.task.task16.task1619;

/* 
Реализуй метод ourInterruptMethod таким образом, чтобы он прерывал нить TestThread. Исправь остальной код программы, если это необходимо. Нельзя использовать метод interrupt.
*/

public class Solution {
    public static volatile boolean isReset = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new TestThread());
        t.start();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod() {
        isReset=true;
    }

    public static class TestThread implements Runnable {

        public void run() {
            while (!isReset) {
                try {
                    System.out.println("he-he");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
