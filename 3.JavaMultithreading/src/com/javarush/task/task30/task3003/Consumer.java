package com.javarush.task.task30.task3003;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue = new LinkedTransferQueue<>();

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(450);
                ShareItem take = queue.take();
                System.out.format("Processing %s\n", take.toString());
            } catch (InterruptedException e) {

            }
        }
    }
}
