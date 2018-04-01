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
    public void fillTemplate(StringBuilder sb, Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws MyException {
        if (iterations == null) return;
        if (iterations.get(iterationP) == null) {
            throw new MyException("ArgumentNotFoundException");
        }
        for (int i = 0; i < iterations.get(iterationP); i++) {
            sb.append(contentP);
        }
    }
}
