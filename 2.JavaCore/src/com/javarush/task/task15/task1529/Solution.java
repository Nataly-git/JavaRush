package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))
        {
            String line = buffer.readLine();
            if(line.equals("helicopter")){
                result = new Helicopter();
            }
            if(line.equals("plane")){
                int count = Integer.parseInt(buffer.readLine());
                result = new Plane(count);
            }
        } catch (Exception e){
            e.getStackTrace();
        }

    }
}
