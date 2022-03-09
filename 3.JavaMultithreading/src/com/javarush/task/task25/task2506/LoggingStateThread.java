package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;
    State state;

    public LoggingStateThread(Thread thread){
        this.thread = thread;
    }

    @Override
    public void run() {
       do {
           if(state == null || state != thread.getState()){
               state = thread.getState();
               System.out.println(state);
           }
        }  while (state != State.TERMINATED);
    }
}
