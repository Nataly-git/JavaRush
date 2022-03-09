package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static  {
        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());
    }

    public static void main(String[] args) {
    }

    public static class ThreadOne extends Thread {
        @Override
        public void run() {
            while (true){
            }
        }
    }

    public static class ThreadTwo extends Thread {
        @Override
        public void run() {
            System.out.println("ThreadTwo start");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }

    public static class ThreadThree extends Thread {
        @Override
        public void run() {
            Thread current = Thread.currentThread();
            while (!current.isInterrupted()){
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ThreadFour extends Thread implements Message {
        Thread current = Thread.currentThread();
        boolean isAlive = true;
        @Override
        public void run() {
            while (isAlive){
            }
        }

        @Override
        public void showWarning() {
            isAlive = false;
        }
    }

    public static class ThreadFive extends Thread {
        @Override
        public void run() {
            String line = null;
            int sum = 0;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
                while (!(line= buffer.readLine()).equals("N")) {
                    sum+= Integer.parseInt(line);
                }
                System.out.println(sum);
            } catch (IOException e){
                e.getStackTrace();
            }
        }
    }
}