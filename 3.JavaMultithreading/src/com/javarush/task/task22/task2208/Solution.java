package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}
Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("name", "Ivanov");
        paramsMap.put("country", "Ukraine");
        paramsMap.put("city", "Kiev");
        paramsMap.put("age", null);

        System.out.println(getQuery(paramsMap));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet()){
            if(entry.getValue() != null) {
                str.append(String.format("%s = '%s' and ", entry.getKey(), entry.getValue()));
            }
        }
        try{
            String res = str.substring(0, str.lastIndexOf(" and "));
            return res;
        } catch (Exception e) {
            return "";
        }
    }
}
