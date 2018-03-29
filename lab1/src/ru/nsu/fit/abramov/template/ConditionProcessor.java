package ru.nsu.fit.abramov.template;

import java.util.Map;

public class ConditionProcessor implements TemplateProcessor{

    private String contentP;
    private String conditionP;
    ConditionProcessor(String content, String condition){
        contentP = content;
        conditionP = condition;
    }

    @Override
    public void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws IfRepeatNameException {
        if (conditions.get(conditionP) == null && conditions != null){
            throw  new IfRepeatNameException("ArgumentNotFoundException");
        }
        if (conditions.get(conditionP)){
            sb.append(contentP);
        }
    }
}