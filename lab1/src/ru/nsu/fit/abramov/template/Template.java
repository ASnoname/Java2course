package ru.nsu.fit.abramov.template;

import java.util.*;
import java.lang.*;

class Template {

    private LinkedList<TemplateProcessor> interfaceBlocks;

    Template(String inString) throws MyException {
        interfaceBlocks = new LinkedList<>();
        processorsBlocks(blocks(inString));
    }

    private LinkedList<String> blocks(String inString){
        LinkedList<String> blocks = new LinkedList<>();
        String[] splitBlocks = inString.split("%", -1);
        blocks.addLast(splitBlocks[0]);
        for (int i = 1; i < splitBlocks.length; i++) {
            if (splitBlocks[i-1].endsWith("\\")){
                blocks.addLast(blocks.removeLast().concat("%").concat(splitBlocks[i]));
            }
            else {
                blocks.addLast(splitBlocks[i]);
            }
        }
        return blocks;
    }

    private void addBlockBoolean(ArrayList<Boolean> blockBoolean, boolean value, int count){
        for (int i = 0; i < count; i++) {
            blockBoolean.add(0,value);
        }
    }

    private void processorsBlocks(LinkedList<String> blocks) throws MyException {
        ListIterator<String> thisBlock = blocks.listIterator();
        while (thisBlock.hasNext()){
            thisBlock.next();
        }
        ArrayList<Boolean> blockBoolean = new ArrayList<>();
        while (thisBlock.hasPrevious()) {
            if (thisBlock.previous().equals("!endif!")){
                String content = thisBlock.previous();
                String ifCond = thisBlock.previous();
                if (ifCond.charAt(0) != '!' && ifCond.charAt(ifCond.length()-1) != '!'){
                    throw  new MyException("ArgumentNotFoundException");
                }
                interfaceBlocks.addFirst(new ConditionProcessor(content,ifCond.substring("!if ".length(),ifCond.length()-1)));
                addBlockBoolean(blockBoolean,true,3);
                continue;
            }
            thisBlock.next();
            if (thisBlock.previous().equals("!endrepeat!")) {
                String content = thisBlock.previous();
                String iteration = thisBlock.previous();
                if (iteration.charAt(0) != '!' && iteration.charAt(iteration.length()-1) != '!'){
                    throw  new MyException("ArgumentNotFoundException\n");
                }
                interfaceBlocks.addFirst(new RepeatProcessor(content, iteration.substring("!repeat ".length(),iteration.length() - 1)));
                addBlockBoolean(blockBoolean,true,3);
                continue;
            }
            addBlockBoolean(blockBoolean,false,1);
        }
        for (int i = 0, j = 0; i < blockBoolean.size(); i++,j++) {
            if (blockBoolean.get(i) && !blockBoolean.get(i+1)){
                j--;
            }
            if (!blockBoolean.get(i)){
                if (i == 0){
                    interfaceBlocks.addFirst(new StringProcessor(blocks.getFirst()));
                }
                else if (i == blockBoolean.size()-1){
                    interfaceBlocks.addLast(new StringProcessor(blocks.getLast()));
                }
                else if (!(blockBoolean.get(i-1) || blockBoolean.get(i+1))){
                    interfaceBlocks.add(j,new ValueProcessor(blocks.get(i)));
                }
                else{
                    interfaceBlocks.add(j,new StringProcessor(blocks.get(i)));
                    j--;
                }
            }
        }
    }

    String fill(Map<String, String> values, Map<String, Boolean> conditions, Map<String, Integer> iterations) throws MyException {
        StringBuilder makeOutString = new StringBuilder();
        for (TemplateProcessor interfaceBlock : interfaceBlocks) {
            interfaceBlock.fillTemplate(makeOutString, values, conditions, iterations);
        }
        return makeOutString.toString();
    }
}
