package ru.nsu.fit.abramov.template;
import java.util.Map;

public class StringProcessor implements TemplateProcessor{

    private String string;
    StringProcessor(String blockString){
        string = blockString;
    }

    @Override
    public void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations){
        sb.append(string);
    }
}