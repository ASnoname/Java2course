package ru.nsu.fit.abramov.template;

import java.util.Map;

public class RepeatProcessor implements TemplateProcessor{

    private String contentP;
    private String iterationP;
    RepeatProcessor(String content, String iteration){
        iterationP = iteration;
        contentP = content;
    }

    @Override
    public void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws IfRepeatNameException {
        if (iterations.get(iterationP) == null && iterations != null){
            throw  new IfRepeatNameException("ArgumentNotFoundException");
        }
        for (int i = 0; i < iterations.get(iterationP); i++) {
            sb.append(contentP);
        }
    }
}
