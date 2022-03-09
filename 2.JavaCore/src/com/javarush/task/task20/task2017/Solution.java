package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.
*/

public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        A a = null;
        try {
            a = (A) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        finally {
            return a;
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
