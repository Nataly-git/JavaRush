package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u - обновляет соответствующие данные людей с заданными id
-d - производит логическое удаление человека с id, заменяет все его данные на null
-i - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке.
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople.
Порядок вывода данных соответствует вводу данных.
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных).
Используй Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1)
            throw new RuntimeException();

        Date birthday;
        Person person;

        switch (args[0]) {
            case "-c":
                int i = 1;
                synchronized (allPeople) {
                do {
                        birthday = sdf.parse(args[i + 2]);
                        if (whatSex(args[i + 1]) == Sex.MALE) {
                            person = Person.createMale(args[i], birthday);
                        } else {
                            person = Person.createFemale(args[i], birthday);
                        }
                    allPeople.add(person);
                    System.out.println(allPeople.indexOf(person));
                        i += 3;
                    }
                    while (i < args.length - 1) ;
                }
                break;
            case "-u":
                i = 1;
                synchronized (allPeople) {
                do {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        if (person == null)
                            throw new IllegalArgumentException();
                        person.setName(args[i + 1]);
                        person.setSex(whatSex(args[i + 2]));
                        birthday = sdf.parse(args[i + 3]);
                        person.setBirthDate(birthday);
                        allPeople.set(Integer.parseInt(args[i]), person);
                        i += 4;
                    }
                    while (i < args.length - 1) ;
                }
                break;
            case "-d":
                i = 1;
                synchronized (allPeople) {
                do {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                        allPeople.set(Integer.parseInt(args[i]), person);
                        i++;
                    }
                    while (i < args.length) ;
                }
                break;
            case "-i":
                i = 1;
                synchronized (allPeople) {
                do {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        String sex = person.getSex() == Sex.MALE ? "м" : "ж";
                        System.out.println(person.getName() + " " + sex + " " + sdf2.format(person.getBirthDate()));
                        i++;
                    }
                    while (i < args.length) ;
                }
                break;
        }
    }

    public static Sex whatSex(String sex){
        return sex.equals("м")? Sex.MALE : Sex.FEMALE;
    }
}
