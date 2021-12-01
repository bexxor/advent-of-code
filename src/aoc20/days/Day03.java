package aoc20.days;

import util.AoCDay;

import java.util.List;

public class Day03 extends AoCDay {

    private final List<String> inputList;

    public Day03(int day) {
        super(day, true, 2020, 1);
        this.inputList = super.getInputList();
    }

    @Override
    public void handleInput(int part) {
    }

    @Override
    public String solve1() {
        long trees = encounterTrees(1, 3);
        return String.valueOf(trees);
    }

    @Override
    public String solve2() {
        long s1 = encounterTrees(1, 1);
        long s2 = encounterTrees(1, 3);
        long s3 = encounterTrees(1, 5);
        long s4 = encounterTrees(1, 7);
        long s5 = encounterTrees(2, 1);
        long product = s1 * s2 * s3 * s4 * s5;
        return String.valueOf(product);
    }

    private long encounterTrees(int y, int x) {
        int j = 0;
        long count = 0;
        int len = inputList.get(0).length();
        int size = inputList.size();
        for (int i = 0; i < size; i += y) {
            String s = inputList.get(i);
            char way = s.charAt(j % len);
            if (way == '#') {
                count++;
            }
            j += x;
        }
        return count;
    }
}
