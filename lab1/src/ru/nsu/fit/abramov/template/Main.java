package ru.nsu.fit.abramov.template;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws MyException {
        Template t = new Template("\\%\\%");
        Map<String, String> values = new HashMap<>();
        values.put("name", "Vasya");
        Map<String, Boolean> conditions = new HashMap<>();
        conditions.put("isUser", true);
        Map<String, Integer> iteration = new HashMap<>();
        iteration.put("redbull", 3);
        System.out.println(t.fill(null, null, null));
    }
}