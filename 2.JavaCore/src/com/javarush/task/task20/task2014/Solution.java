package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {
        System.out.println(new Solution(4));
        try(FileInputStream inputStream = new FileInputStream("/Users/nataly/Downloads/jdk-17.jdk/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2014/myFile");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            FileOutputStream outputStream = new FileOutputStream("/Users/nataly/Downloads/jdk-17.jdk/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2014/myFile");
            ObjectOutputStream ous = new ObjectOutputStream(outputStream)){
            Solution savedObject = new Solution(15);
            ous.writeObject(savedObject);
            Solution loadedObject = (Solution) ois.readObject();
            System.out.println(loadedObject.string.equals(savedObject.toString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
