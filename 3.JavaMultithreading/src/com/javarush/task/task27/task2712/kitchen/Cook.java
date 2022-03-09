package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private final String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startCookingOrder(Order order) {
        this.busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException ignore) {

        }
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(row);
        this.busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                    while (true) {
                        if(!queue.isEmpty()) {
                            if(!Cook.this.isBusy()) {
                                Cook.this.startCookingOrder(queue.take());
                                }
                            }
                        }
                } catch (InterruptedException ignore) {
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
}
