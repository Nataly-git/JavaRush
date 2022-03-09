package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        int a = x % 2;
        int b = x / 2;
        if (b > 0) {
            BinaryRepresentationTask bin = new BinaryRepresentationTask(b);
            bin.fork();
            return bin.join() + a;
        }
        return String.valueOf(a);
    }
}
