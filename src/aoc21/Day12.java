package aoc21;

import util.AoCDay;

import java.util.List;


public class Day12 extends AoCDay {

    private final List<String> inputList;
    private int x;
    private int y;
    private int dir; // 0=N, 1=E, 2=S, 3=W
    private int wX;
    private int wY;

    public Day12(int day) {
        super(day, 2021, 1);
        this.inputList = super.getInputList();
    }

    @Override
    public void handleInput() {
    }

    @Override
    public String solve1() {
        return String.valueOf(-1);
    }

    @Override
    public String solve2() {
        return String.valueOf(-1);
    }
}
