package aoc20.days;


import util.AoCDay;

import java.util.List;


public class Day01 extends AoCDay {

    private static final int SUM = 2020;
    private final List<String> inputList;

    public Day01(String day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
    }

    @Override
    public void handleInput(int part) {
    }

    @Override
    public String solve1() {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = i + 1; j < inputList.size(); j++) {
                int i1 = Integer.parseInt(inputList.get(i));
                int i2 = Integer.parseInt(inputList.get(j));
                if (i1 + i2 == SUM) {
                    return String.valueOf(i1 * i2);
                }
            }
        }
        return String.valueOf(-1);
    }

    @Override
    public String solve2() {
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = i + 1; j < inputList.size(); j++) {
                for (int k = j + 1; k < inputList.size(); k++) {
                    int i1 = Integer.parseInt(inputList.get(i));
                    int i2 = Integer.parseInt(inputList.get(j));
                    int i3 = Integer.parseInt(inputList.get(k));
                    if (i1 + i2 + i3 == SUM) {
                        return String.valueOf(i1 * i2 * i3);
                    }
                }
            }
        }
        return String.valueOf(-1);
    }
}
