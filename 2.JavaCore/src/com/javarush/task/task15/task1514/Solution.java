package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static
    {
       labels.put(5.,"five");
       labels.put(6.,"six");
       labels.put(7.,"seven");
       labels.put(8.,"eight");
       labels.put(9.,"nine");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
