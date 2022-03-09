package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Считать с консоли имя файла.
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int).
Закрыть потоки.
В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity
где id - int.
productName - название товара, может содержать пробелы, String.
price - цена, double.
quantity - количество, int.
Информация по каждому товару хранится в отдельной строке.
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader buffer = new BufferedReader(new FileReader(fileName))){
            while (buffer.ready()){
                list.add(buffer.readLine());
            }
        }
        for(String str : list){
            String[] info = str.split(" ");
            if(info[0].equals(String.valueOf(args[0]))){
                System.out.println(str);
            }
        }
    }
}
