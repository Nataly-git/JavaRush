package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла.
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
4. Если условие из п.3 не выполнено, то:
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        String firstFile = null;
        String secondFile = null;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            firstFile = buffer.readLine();
            secondFile = buffer.readLine();
        } catch (IOException e) {
            e.getStackTrace();
        }
        if(firstFile == null || secondFile == null)
            throw new IllegalArgumentException();
        try (BufferedReader bufferOne = new BufferedReader(new FileReader(firstFile));
             BufferedReader bufferTwo = new BufferedReader(new FileReader(secondFile)))
        {
            while (bufferOne.ready()) {
                allLines.add(bufferOne.readLine());
            }
            while (bufferTwo.ready()) {
                forRemoveLines.add(bufferTwo.readLine());
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException e) {
            e.getStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
            if (allLines.containsAll(forRemoveLines)) {
                allLines.removeAll(forRemoveLines);
            }
            else {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }
    }
