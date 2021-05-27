package util;

import java.util.ArrayList;
import java.util.List;

public abstract class AoCDay {

    private final String day;
    public final String input;
    public final List<String> inputList;

    public AoCDay(String day, boolean inputAsList, int year) {
        this.day = day;
        this.input = inputAsList ? "" : InputReaderUtil.inputAsStringLine(year, day);
        this.inputList = inputAsList ? InputReaderUtil.inputAsListOfLines(year, day) : new ArrayList<>();
    }

    public final String solve(String part) {
        return (part.equals("1")) ? this.solve1() : this.solve2();
    }

    public abstract void handleInput(int part);

    public abstract String solve1();

    public abstract String solve2();

    public String getDay() {
        return day;
    }

    public String getInput() {
        return input;
    }

    public List<String> getInputList() {
        return inputList;
    }
}
