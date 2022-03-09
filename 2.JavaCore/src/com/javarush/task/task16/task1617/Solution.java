package com.javarush.task.task16.task1617;

/* 
Реализуй логику метода run так, чтобы каждую секунду через пробел
выдавался отсчет начиная с numSeconds до 1, а потом слово [Марш!] (см примеры).
Если нить работает 3.5 секунды или более, прерви ее методом interrupt и внутри нити выведи в консоль слово [Прервано!].
*/

public class Solution {
    public static volatile int numSeconds = 3;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            try {
                while (numSeconds >0){
                System.out.print(numSeconds + " ");
                numSeconds--;
                Thread.sleep(1000);}
                if (numSeconds == 0){
                    System.out.print("Марш!");
                }
            } catch (InterruptedException e){
                System.out.print("Прервано!");
            }
        }
    }
}
