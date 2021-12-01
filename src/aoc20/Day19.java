package aoc20;

import util.AoCDay;

import java.util.*;
import java.util.regex.Pattern;

public class Day19 extends AoCDay {

    private static final Pattern NUMBER = Pattern.compile(".*\\d+.*");

    private final List<String> inputList;
    private final HashMap<String, String> simpleRules;
    private final List<String> messages;
    private String rule0;


    public Day19(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.messages = new ArrayList<>();
        this.simpleRules = new HashMap<>();
        handleInput();
    }

    @Override
    public void handleInput() {
        int i, size = inputList.size();
        for (i = 0; i < size; i++) {
            String line = inputList.get(i);
            if (line.equals("")) {
                i++;
                break;
            }
            String[] split = line.replaceAll("\"", "").split(": ");
            String val = split[1];
            if(split[0].equals("0")){
                rule0 = String.format("^( %s ) $",split[1]);
            }
            else if(val.length()==1){
                simpleRules.put(split[0], val);
            } else{
                simpleRules.put(split[0], String.format("( %s )", val));
            }
        }
        for (int j = i; j < size; j++) {
            messages.add(inputList.get(j));
        }
    }

    @Override
    public String solve1() {
        Pattern rule0Pattern = createPattern();
        int count = 0;
        for (String message : messages) {
            count = rule0Pattern.matcher(message).matches() ? count + 1 : count;
        }
        return String.valueOf(count);
    }

    @Override
    public String solve2() {
        simpleRules.put("8", "( 42 + )"); // 8: 42 | 42 8
        simpleRules.put("11", "( 42 31 | 42 11 31 )"); // 11: 42 31 | 42 11 31 // "(?x)(?: 42 (?= 42 *(\\\\1?+ 31 )))+\\\\1"
        Pattern rule0Pattern = createPattern();
        int count = 0;
        for (String message : messages) {
            count = rule0Pattern.matcher(message).matches() ? count + 1 : count;
        }
        return String.valueOf(count);
    }

    private Pattern createPattern(){
        int i = 0;
        while (i<10 && containsNumber(rule0)) {
            for (String key : simpleRules.keySet()) {
                String newKey = String.format(" %s ", key);
                String newValue = String.format(" %s ", simpleRules.get(key));
                rule0 = rule0.replaceAll(newKey, newValue);
            }
            i++;
        }
        rule0 = rule0.replaceAll("\\s+", "");
        return Pattern.compile(rule0);
    }

    private boolean containsNumber(String str) {
        return NUMBER.matcher(str).matches();
    }
}
