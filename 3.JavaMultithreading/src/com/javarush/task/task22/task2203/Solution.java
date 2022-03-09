package com.javarush.task.task22.task2203;

/* 
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            int indBeg = string.indexOf('\t') + 1;
            int indEnd = string.indexOf('\t', indBeg);
            return string.substring(indBeg, indEnd);
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
