package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Запрети сериализацию класса SubSolution используя NotSerializableException.
Сигнатуры классов менять нельзя.
*/

public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream out) throws NotSerializableException {
            throw new NotSerializableException("Not serializable");
        }

        private void readObject(ObjectInputStream in) throws NotSerializableException {
            throw new NotSerializableException("Not serializable");
        }
    }

    public static void main(String[] args) {
    }
}
