package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses(){
        return horses;
    }

    Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(){
        for(Horse horse : horses) {
            horse.move();
        }
    }

    public void print(){
        for(Horse horse : horses){
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        Horse horse = Collections.max(horses, new Comparator<Horse>() {
            @Override
            public int compare(Horse o1, Horse o2) {
                return Double.compare(o1.getDistance(),o2.getDistance());
            }
        });
        return horse;
    }

    public void printWinner(){
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Pushok", 3, 0));
        horses.add(new Horse("Wind", 3, 0));
        horses.add(new Horse("Pegas", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
