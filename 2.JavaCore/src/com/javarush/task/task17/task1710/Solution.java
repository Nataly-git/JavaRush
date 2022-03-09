package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-r id
-u id name sex bd
-d id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null

id соответствует индексу в списке.
Все люди должны храниться в allPeople.
Используй Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {

        switch (args[0]) {
            case "-c":
                create(args[1], args[2], args[3]);
                break;
            case "-r":
                read(args[1]);
                break;
            case "-u":
                update(args[1], args[2], args[3], args[4]);
                break;
            case "-d":
                delete(args[0]);
                break;
        }
    }

    public static void create(String name, String sex, String bd) {
        Sex sexx = sex.equals("м") ? Sex.MALE: Sex.FEMALE;

            Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(bd);
        } catch (Exception e) {
            e.getStackTrace();
        }



        Person person;
        switch (sexx) {
            case MALE:
                person = Person.createMale(name,date);
                break;
            case FEMALE:
                person = Person.createFemale(name,date);
                break;
            default:
                person = null;
        }

        allPeople.add(person);

        System.out.println(allPeople.indexOf(person));
    }

    public static void read(String id) {
        Person person = allPeople.get(Integer.parseInt(id));

        String sex = person.getSex().toString();

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        System.out.println(person.getName() + " " + sex + " " + format.format(person.getBirthDate()));
    }

    public static void update(String id, String name, String sex, String bd) {
        Person person = allPeople.get(Integer.parseInt(id));

        person.setName(name);

        person.setSex(sex.equals("м") ? Sex.MALE: Sex.FEMALE);

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(bd);
        } catch (Exception e) {
            e.getStackTrace();
        }
        person.setBirthDate(date);
        allPeople.set(Integer.parseInt(id),person);
    }

    public static void delete(String id) {
        Person person = allPeople.get(Integer.parseInt(id));

        person.setName(null);
        person.setSex(null);
        person.setBirthDate(null);

    }
}
