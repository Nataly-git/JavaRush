package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов(детали уточни у своего любимого поисковика).
Обе строки first и last должны принимать участие в сравнении с помощью метода equals и вычислении hashcode.*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object n) {
        if (this == n) return true;
        if(n == null) return false;
        if(this.getClass() != n.getClass()) return false;
        Solution o = (Solution) n;
        return ((first != null && o.first.equals(this.first)) || o.first == first )
                && ((last != null && o.last.equals(this.last)) || o.last == last);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (first == null ? 0 : first.hashCode());
        result = 31 * result + (last == null ? 0 :  last.hashCode());
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
