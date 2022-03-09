package com.javarush.task.task22.task2202;

/* 
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try {
        String[] array = string.split(" ");
        StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 5; i++) {
                sb.append(array[i] + " ");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
