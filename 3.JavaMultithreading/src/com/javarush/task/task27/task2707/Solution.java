package com.javarush.task.task27.task2707;

/* 
Реализуй логику метода isLockOrderNormal, который должен определять:
соответствует ли порядок synchronized блоков в методе someMethodWithSynchronizedBlocks - порядку передаваемых в него аргументов.
В случае, если сначала происходит синхронизация по o1, а потом по o2, метод должен вернуть true.
Если наоборот - false.
Идея для решения:
1) Захватить и удерживать в основном (первом) треде объект o1
2) Запустить во втором треде someMethodWithSynchronizedBlocks(). Тогда
*  в случае порядка синхронизации "сначала o1, потом o2" внутри этого метода, o2 останется свободен, а тред заблокируется на этапе получения доступа к o1
* в случае порядка синхронизации "сначала o2, потом o1" внутри этого метода, тред успеет захватать o2, и (опять-таки) заблокируется на этапе получения доступа к o1
3) Запустить в третьем треде захват o2 и затем захват o1. Тогда
* если o2 был свободен, то захват o2 произойдёт успешно. Это нужно как-нибудь отметить, например выставить заранее объявленной boolean (или AtomicBoolean) переменной o2Captured значение true . Тред далее заблокируется на получении доступа к o1.
* если o2 был занят, тред заблокируется сразу, ещё на получении доступа к o2
4) isLockOrderNormal() должен вернуть значение o2Locked, а основной тред должен отпустить o1

p.s. Треды блокируются не мгновенно. Если запустить третий тред не дождавшись блокировки второго,    ничего не получится. Проверить состояние треда можно методом .getState()

p.p.s. Некоторые решают через формирование deadlock и переводом тредов в демоны, тогда при завершении остальных тредов, демоны насильственно завершатся и дедлок снимется. Это кривое решение: представьте себе, что у вас реальное приложение, и сервис работает 24/7, и ему регулярно шлют запросы на вызов метода isLockOrderNormal() -- будут постоянно оставаться дедлоки, а значит утечка памяти, а значит сервис будет регулярно крашиться.
Некоторые в решении полагаются на Thread.sleep(x), где x -- 500 или какое-то другое число. Это тоже кривое решение, так как гарантированно не может быть известно, что срока sleep достаточно, к тому же такой метод будет "тормозить"
*/

import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        AtomicBoolean o2Locked = new AtomicBoolean(false);
        synchronized (o1){
            Thread testedMethodThread = new Thread(()->solution.someMethodWithSynchronizedBlocks(o1,o2));
            testedMethodThread.start();
            while (testedMethodThread.getState()!= Thread.State.BLOCKED);
            Thread o2Locker = new Thread(()->{
                synchronized (o2){
                    o2Locked.set(true);
                    synchronized (o1){}
                }
            });
            o2Locker.start();
            while (o2Locker.getState()!= Thread.State.BLOCKED);
            return o2Locked.get();
        }
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
