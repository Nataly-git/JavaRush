package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
Реализуй логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы.
Верни отсортированный массив от минимального расстояния до максимального.
Если удаленность одинаковая у нескольких чисел, то сортируй их в порядке возрастания.

Пример входящего массива:
13, 8, 15, 5, 17
медиана - 13

Отсортированный масив:
13, 15, 17, 8, 5
*/

public class Solution {
    public static int midpoint = 0;

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        if(array.length % 2 != 0) midpoint = array[(array.length-1) / 2];
        else midpoint = (array[array.length / 2] + array[array.length / 2 - 1]) /2;
        Comparator<Integer> comparatorByMidpoint = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int res = Math.abs(o1 - midpoint) - Math.abs(o2 - midpoint);
                if(res != 0) return res;
                return o1 - o2;
            }
        };
        Arrays.sort(array, comparatorByMidpoint);
        System.out.println(Arrays.toString(array));
        return array;
    }
}
