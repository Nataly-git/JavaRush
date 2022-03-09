package com.javarush.task.task26.task2603;

import java.util.Comparator;

/* 
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напиши public static компаратор CustomizedComparator, который будет:
1. В конструкторе принимать массив компараторов.
2. Сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т.
В конструктор передается как минимум один компаратор.
*/

public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T> comparators [];

        public CustomizedComparator(Comparator<T> ... comparators) {
            this.comparators = comparators;
            }

        @Override
        public int compare(T o1, T o2) {
            int res = 0;
            for (Comparator<T> comparator : comparators) {
                res = comparator.compare(o1, o2);
                if(res != 0) return res;
            }
            return res;
        }
    }

    public static void main(String[] args) {
    }
}
