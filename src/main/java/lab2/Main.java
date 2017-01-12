package lab2;

import java.util.HashMap;

/**
 * Created by ihb on 10.01.17.
 */
public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> j = new MyHashMap<>();
        System.out.println(j.size());
        j.put("Hello", 1);
        j.put("Hell", 1);
        j.put("Java", 1);
        j.put("Hell", 5);
        j.put("Hell", 5);
        System.out.println(j.size());
        j.get("Hell");
        j.get("Hello");
        j.containsKey("Hello");
        j.containsKey("AAAAAAAAAAAAAAAAAA");
        j.remove("Me");
        System.out.println(j.size());
        j.remove("Hello");
        System.out.println(j.size());
        j.put("Hello", 1);
        System.out.println(j.size());
    }
}
