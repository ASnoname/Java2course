package ru.nsu.fit.abramov.template;

import java.util.Map;

public interface TemplateProcessor {
    void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws MyException;
}
