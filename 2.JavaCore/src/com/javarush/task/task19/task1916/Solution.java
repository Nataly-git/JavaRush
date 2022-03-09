package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Считать с консоли 2 пути к файлам - file1, file2.
Файлы содержат строки. Так как file2 является обновленной версией file1, то часть строк совпадает.
Нужно создать объединенную версию строк из обоих файлов и записать эти строки в список lines.
Правила объединения:

Если строка в обоих файлах совпадает, то в результат она попадает с операцией (приставкой) SAME.
Например, SAME строка1.
Если строка есть в file1, но ее нет в file2, то считаем, что строку удалили и в результат она попадает с операцией (приставкой) REMOVED.
Например, REMOVED строка2.
Если строки нет в file1, но она есть в file2, то считаем, что строку добавили и в результат она попадает с операцией (приставкой) ADDED.
Например, ADDED строка0.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
Пустые строки даны в примере для наглядности и означают, что этой строки нет в определенном файле.
В оригинальном и редактируемом файлах пустых строк нет!
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException{
        String oldFileName = null;
        String newFileName = null;
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            oldFileName = buffer.readLine();
            newFileName = buffer.readLine();
        }

        List<String> oldFileLines = readFileLines(oldFileName);
        List<String> newFileLines = readFileLines(newFileName);

        int oldFileLine = 0;
        int newFileLine = 0;

        while ((oldFileLine < oldFileLines.size()) && (newFileLine < newFileLines.size())) {

            if (oldFileLines.get(oldFileLine).equals(newFileLines.get(newFileLine))) {
                lines.add(new LineItem(Type.SAME, oldFileLines.get(oldFileLine)));
                oldFileLine++;
                newFileLine++;
            } else if ((newFileLine + 1 < newFileLines.size()) && oldFileLines.get(oldFileLine).equals(newFileLines.get(newFileLine + 1))) {
                lines.add(new LineItem(Type.ADDED, newFileLines.get(newFileLine)));
                newFileLine++;
            } else if ((oldFileLine + 1 < oldFileLines.size()) && oldFileLines.get(oldFileLine + 1).equals(newFileLines.get(newFileLine))) {
                lines.add(new LineItem(Type.REMOVED, oldFileLines.get(oldFileLine)));
                oldFileLine++;
            }
        }

        while (oldFileLine < (oldFileLines.size())) {
            lines.add(new LineItem(Type.REMOVED, oldFileLines.get(oldFileLine)));
            oldFileLine++;
        }
        while (newFileLine < (newFileLines.size())) {
            lines.add(new LineItem(Type.ADDED, newFileLines.get(newFileLine)));
            newFileLine++;
        }
    }

    static List<String> readFileLines(String fileName) throws IOException {
        BufferedReader fReader = new BufferedReader(new FileReader(fileName));
        List<String> fileLines = new ArrayList<String>();
        String line;
        while ((line = fReader.readLine()) != null) {
            fileLines.add(line);
        }
        fReader.close();
        return fileLines;
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
