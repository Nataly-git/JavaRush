package com.javarush.task.task27.task2706;

/* 
Используя стратегию избегания deadlock-а сделай так, чтобы он не возник.
Метод main не участвует в тестировании.
Действуй аналогично примеру из лекций.
Изменения вноси только в safeMethod.
*/

public class Solution {
    public void safeMethod(Object obj1, Object obj2) {
        Object oMax = obj1.toString().compareTo(obj2.toString()) > 0 ? obj1 : obj2;
        Object oMin = obj2.toString().compareTo(obj1.toString()) < 0 ? obj2 : obj1;

        synchronized (oMax) {
            longTimeMethod();
            synchronized (oMin) {
                unsafeMethod(obj1, obj2);
            }
        }
    }

    public void longTimeMethod() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    protected void unsafeMethod(Object obj1, Object obj2) {
        System.out.println(obj1 + " " + obj2);
    }

    public static void main(String[] args) {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Solution solution = new Solution();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o1, o2);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o2, o1);
            }
        }.start();
    }
}
