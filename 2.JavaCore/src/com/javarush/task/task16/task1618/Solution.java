package com.javarush.task.task16.task1618;

/* 
Снова interrupt
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        testThread.start();
        testThread.interrupt();
    }
    public static class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println("Нить запущена");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e){
                System.out.println("Метод прерван");
            }
        }
    }
}