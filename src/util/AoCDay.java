package util;

import java.util.ArrayList;
import java.util.List;

public abstract class AoCDay {

    private final int day;
    public final String input;
    public final List<String> inputList;

    public AoCDay(int day, boolean inputAsList, int year) {
        this.day = day;
        this.input = inputAsList ? "" : InputReaderUtil.inputAsStringLine(year, day);
        this.inputList = inputAsList ? InputReaderUtil.inputAsListOfLines(year, day) : new ArrayList<>();
    }

    public final String solve(int part) {
        return (part==1) ? this.solve1() : this.solve2();
    }

    public abstract void handleInput(int part);

    public abstract String solve1();

    public abstract String solve2();

    public int getDay() {
        return day;
    }

    public String getInput() {
        return input;
    }

    public List<String> getInputList() {
        return inputList;
    }
}
