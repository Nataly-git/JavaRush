package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Считать с консоли URL-ссылку.
Вывести на экран список всех параметров через пробел (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Выводить параметры нужно в той же последовательности, в которой они представлены в URL.
Если присутствует параметр obj, то передать его значение в нужный метод alert():
alert(double value) - для чисел (не забывай о том, что число может быть дробным);
alert(String value) - для строк.
Обрати внимание на то, что метод alert() необходимо вызывать после вывода списка всех параметров на экран.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        StringBuilder sb = new StringBuilder(url);
        sb.delete(0,sb.indexOf("?")+1);
        String nextUrl = sb.toString();
        String[] array = nextUrl.split("&");
        String isAlert = null;
        for (int i = 0; i < array.length; i++) {
            if(array[i].contains("=")){
                String newArg = array[i].substring(0,array[i].indexOf('='));
                if(newArg.equals("obj")){
                    isAlert = array[i].substring(array[i].indexOf('=')+1);
                }
                array[i] = newArg;
            }
        }
        String urlNew = String.join(" ", array);
        System.out.println(urlNew);

        if(isAlert!=null){
            try{
                double num = Double.parseDouble(isAlert);
                alert(num);
            }catch (Exception e){
                alert(isAlert);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }

}

