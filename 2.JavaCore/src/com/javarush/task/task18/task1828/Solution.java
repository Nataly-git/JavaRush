package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;

/* 
CRUD для таблицы внутри файла.
Напиши программу, которая считывает с консоли путь к файлу для операций CRUD и при запуске в зависимости от флага, переданного в параметрах обновляет данные товара с заданным id или производит физическое удаление товара с заданным id (удаляет из файла все данные, которые относятся к переданному id).
-u id productName price quantity
-d id
Значения параметров:
-u - флаг, который означает обновление данных товара с заданным id
-d - флаг, который означает физическое удаление товара с заданным id (из файла удаляются все данные, которые относятся к переданному id)
id - id товара, 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа
В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины.
Для чтения и записи файла нужно использовать FileReader и FileWriter соответственно.
Пример содержимого файла:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

public class Solution {
    public static class Product {
        int id;
        String name;
        String price;
        String quantity;

        public Product(int id, String name, String price, String quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, name, price, quantity);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0)
            return;
        String fileName = null;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Product> list = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            while (buffer.ready()) {
                list.add(getProduct(buffer.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (args[0]) {
            case "-u": {
                int id = Integer.parseInt(args[1]);
                String name = args[2];
                if (name.length() > 30) {
                    name = name.substring(0, 30);
                }
                String price = args[3];
                if (price.length() > 8) {
                    price = price.substring(0, 8);
                }
                String quantity = args[4];
                if (quantity.length() > 4) {
                    quantity = quantity.substring(0, 4);
                }
                Product productToUpdate = null;
                for (Product product : list) {
                    if (product.id == id) productToUpdate = product;
                }
                if (productToUpdate != null) {
                    productToUpdate.name = name;
                    productToUpdate.price = price;
                    productToUpdate.quantity = quantity;
                }
                break;
            }
            case "-d":
                int id = Integer.parseInt(args[1]);
                Product productToDelete = null;
                for (Product product : list) {
                    if (product.id == id) productToDelete = product;
                }
                if (productToDelete != null) list.remove(productToDelete);
                break;
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Product product : list) {
                writer.write(product.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Product getProduct(String str) {
        String id = str.substring(0, 8).trim();
        String name = str.substring(8, 38).trim();
        String price = str.substring(38, 46).trim();
        String quantity = str.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price,quantity);
    }
}
