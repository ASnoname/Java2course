package ru.nsu.fit.abramov.template;

import java.util.Map;

public class ValueProcessor implements TemplateProcessor{

    private String value;
    ValueProcessor(String blockName){
        value = blockName;
    }

    @Override
    public void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws MyException {
        if (iterations == null) return;
        if (values.get(value) == null){
            throw  new MyException("ArgumentNotFoundException");
        }
        sb.append(values.get(value));
    }
}