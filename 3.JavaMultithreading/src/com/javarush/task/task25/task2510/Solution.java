package com.javarush.task.task25.task2510;

/* 
Создай свой UncaughtExceptionHandler в виде локального класса внутри конструктора.
UncaughtExceptionHandler должен маскировать звездочками имя трэда и выводить в консоль описание возникшей ошибки.
"Thread-0" должно быть заменено на "********".
"Thread-4321" должно быть заменено на "***********".
*/

public class Solution extends Thread {

    public Solution() {
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                }
                 else if(e instanceof Exception) {
                    System.out.println("Надо обработать");
                }
                else
                    System.out.println("Поживем - увидим");
            }
        });
    }

    public static void main(String[] args) {
    }
}
