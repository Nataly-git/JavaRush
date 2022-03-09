package com.javarush.task.task22.task2210;

import java.util.Arrays;
import java.util.StringTokenizer;

/* 
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример
getTokens("level22.lesson13.task01", ".")
возвращает массив строк
{"level22", "lesson13", "task01"}
*/

public class Solution {
    public static void main(String[] args) {
        String[] array = getTokens("level22.lesson13.task01", ".");
        System.out.println(Arrays.toString(array));
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] result = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()){
            result[i] = tokenizer.nextToken();
            i++;
        }
        return result;
    }
}
