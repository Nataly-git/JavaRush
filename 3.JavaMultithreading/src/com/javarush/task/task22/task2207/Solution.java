package com.javarush.task.task22.task2207;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String fileName = null;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> words = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            while (buffer.ready()) {
                String line = buffer.readLine();
                String[] lines = line.split(" ");
                words.addAll(Arrays.asList(lines));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); ) {
                if (i >= words.size()) {
                    break;
                }
                if (words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i) {
                    Pair pair = new Pair();
                    pair.first = words.get(j);
                    pair.second = words.get(i);
                    result.add(pair);
                    words.remove(j);
                    words.remove(i);
                    j = 0;
                } else
                    j++;
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
