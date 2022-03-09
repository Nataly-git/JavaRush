package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/

public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution o = (Solution) super.clone();
        Map<String, User> newUs = new LinkedHashMap();
        for (Map.Entry<String, User> entry : users.entrySet()){
            newUs.put(entry.getKey(),(User) entry.getValue().clone());
        }
        o.users = newUs;
        return o;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = result + age;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null) return false;
            if(getClass() != obj.getClass()) return false;
            User user = (User) obj;
            if(name != null ? !name.equals(user.name) : user.name != null) return false;
            return age == user.age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
