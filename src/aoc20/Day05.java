package aoc20;

import util.AoCDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05 extends AoCDay {

    private final List<String> inputList;
    private final List<Integer> seatIDs;

    public Day05(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.seatIDs = new ArrayList<>();
        handleInput();
    }

    @Override
    public void handleInput() {
        for (String line : inputList) {
            String pass = line.replaceAll("[FL]", "0").replaceAll("[BR]", "1");
            int row = Integer.parseInt(pass.substring(0, 7), 2);
            int col = Integer.parseInt(pass.substring(7), 2);
            seatIDs.add(row * 8 + col);
        }
    }

    @Override
    public String solve1() {
        int max = Collections.max(seatIDs);
        return String.valueOf(max);
    }

    @Override
    public String solve2() {
        Collections.sort(seatIDs);
        int min = seatIDs.get(0);
        for (int i = min; i < seatIDs.size() + min; i++) {
            if (seatIDs.get(i - min) != i) {
                return String.valueOf(i);
            }
        }
        return String.valueOf(-1);
    }
}
