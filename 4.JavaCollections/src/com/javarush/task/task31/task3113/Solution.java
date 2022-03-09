package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок - [количество папок в директории и поддиректориях]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]
*/

public class Solution extends SimpleFileVisitor<Path> {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String dirName = buffer.readLine();
        Path filePath = Paths.get(dirName);
        if (!Files.isDirectory(filePath)) {
            System.out.printf("%s - не папка", filePath.toAbsolutePath());
            return;
        }
        final Solution solution = new Solution();
        Files.walkFileTree(filePath, solution);


        System.out.println("Всего папок - " + solution.getDirCount().decrementAndGet());
        System.out.println("Всего файлов - " + solution.getFilesCount().toString());
        System.out.println("Общий размер - " + solution.getAllSize().toString());
    }

    private AtomicInteger dirCount = new AtomicInteger(0);
    private AtomicInteger filesCount = new AtomicInteger(0);
    private AtomicLong allSize = new AtomicLong(0);

    public AtomicInteger getDirCount() {
        return dirCount;
    }

    public AtomicInteger getFilesCount() {
        return filesCount;
    }

    public AtomicLong getAllSize() {
        return allSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if(Files.isRegularFile(file)) {
            byte[] content = Files.readAllBytes(file);
            allSize.addAndGet(content.length);
            filesCount.incrementAndGet();
            return FileVisitResult.CONTINUE;
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(Files.isDirectory(dir)) {
            dirCount.incrementAndGet();
            return FileVisitResult.CONTINUE;
        }
        return super.preVisitDirectory(dir, attrs);
    }
}
