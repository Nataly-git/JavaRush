package com.javarush.task.task15.task1518;

/* 
Создать класс Cat с публичным полем name типа String в классе Solution.
В статическом блоке создать объект типа Cat и присвоить его переменной cat (не забудь инициализировать поле name).
В статическом блоке вывести имя созданного кота на экран.
*/

public class Solution {
    public static Cat cat;
    static {
        cat = new Cat();
        cat.name = "Tom";
        System.out.println(cat.name);
    }

    public static void main(String[] args) {

    }

    public static class Cat{
        public String name;
    }
}
