package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/* 
Считай с консоли имя файла, который имеет HTML-формат.

Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>

Первым параметром в метод main приходит тег. Например, "span".
Вывести на консоль все теги, которые соответствуют заданному тегу.
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле.
Количество пробелов, \n, \r не влияют на результат.
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нет.
Тег может содержать вложенные теги.

Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = buffer.readLine();
        buffer.close();

        StringBuilder readFileContent = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                readFileContent.append(fileReader.readLine());
            }
        } catch (IOException ignore) {
        }

        String fileContent = readFileContent.toString().replaceAll("[\\r\\n]+", "");

        String tag = args[0];
        String openedTag = "<" + tag;
        String closedTag = "</" + tag;
        int openedTagIndex = fileContent.indexOf(openedTag);
        int closedTagIndex = fileContent.indexOf(closedTag);
        int closedTagCount = 0;
        ArrayList<Integer> openedTagsIndexes = new ArrayList<>();
        ArrayList<Integer> closedTagsIndexes = new ArrayList<>();

        while (openedTagIndex != -1 || closedTagIndex != -1) {
            if (openedTagIndex != -1 && openedTagIndex < closedTagIndex) {
                openedTagsIndexes.add(openedTagIndex);
                openedTagIndex = fileContent.indexOf(openedTag, openedTagIndex + 1);
            } else if (closedTagCount != -1 && (closedTagIndex < openedTagIndex || openedTagIndex == -1)) {
                closedTagsIndexes.add(closedTagIndex + tag.length() + 3);
                closedTagCount++;
                closedTagIndex = fileContent.indexOf(closedTag, closedTagIndex + 1);
            }
        }

        Stack<String> stack = new Stack<>();
        for (int i = openedTagsIndexes.size() - 1; i >= 0; i--) {
            stack.push(fileContent.substring(openedTagsIndexes.get(i), getNextCloseTag(closedTagsIndexes, openedTagsIndexes.get(i))));
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    private static int getNextCloseTag(ArrayList<Integer> closedTagsIndexes, Integer openTagIndex) {
        Iterator<Integer> iterator = closedTagsIndexes.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > openTagIndex) {
                iterator.remove();
                return next;
            }
        }
        return 0;
    }
}