package aoc20.days;

import util.AoCDay;

import java.util.*;

public class Day06 extends AoCDay {

    private final List<String> inputList;

    public Day06(int day) {
        super(day, true, 2020, 1);
        this.inputList = super.getInputList();
    }

    @Override
    public void handleInput(int part) {
    }

    @Override
    public String solve1() {
        int sum = 0;
        Set<String> answersPerGroup = new HashSet<>();
        for (String s : inputList) {
            if (s.equals("")) {
                sum += answersPerGroup.size();
                answersPerGroup = new HashSet<>();
            } else {
                List<String> entries = Arrays.asList(s.split(""));
                answersPerGroup.addAll(entries);
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String solve2() {
        long sum = 0;
        Map<String, Integer> answersPerGroup = new HashMap<>();
        int people = 0;
        for (String s : inputList) {
            if (s.equals("")) {
                int finalPeople = people;
                sum += answersPerGroup.entrySet().stream().filter(entry -> entry.getValue() == finalPeople).count();
                answersPerGroup = new HashMap<>();
                people = 0;
            } else {
                people++;
                String[] entries = s.split("");
                for (String entry : entries) {
                    if (answersPerGroup.containsKey(entry)) {
                        answersPerGroup.put(entry, answersPerGroup.get(entry) + 1);
                    } else {
                        answersPerGroup.put(entry, 1);
                    }
                }
            }
        }
        return String.valueOf(sum);
    }
}
