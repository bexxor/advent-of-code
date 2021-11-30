package aoc20.days;

import util.AoCDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 extends AoCDay {

    private final String input;
    private List<Integer> numbers;
    private final HashMap<Integer, Integer> lastIndex;

    public Day15(String day) {
        super(day, false, 2020);
        this.input = super.getInput();
        this.numbers = new ArrayList<>();
        this.lastIndex = new HashMap<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        this.numbers = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < numbers.size()-1; i++) {
            lastIndex.put(numbers.get(i), i);
        }
    }

    @Override
    public String solve1() {
        int spoken = findSpokenNumber(2020);
        return String.valueOf(spoken);
    }

    @Override
    public String solve2() {
        int spoken = findSpokenNumber(30000000);
        return String.valueOf(spoken);
    }

    private int findSpokenNumber(int x){
        int last;
        int spoken = -1;
        for (int i = numbers.size(); i < x; i++) {
            int j = numbers.size()-1;
            last = numbers.get(j);
            if(lastIndex.containsKey(last)){
                spoken = j-lastIndex.get(last);
            } else{
                spoken = 0;
            }
            numbers.add(spoken);
            lastIndex.put(last, j);
        }
        return spoken;
    }
}
