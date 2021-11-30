package aoc20.days;

import aocUniverse.Password;
import util.AoCDay;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends AoCDay {

    private final List<String> inputList;
    private final List<Password> passwordRules;


    public Day02(int day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.passwordRules = new ArrayList<>();
        handleInput(0);

    }

    @Override
    public void handleInput(int part) {
        for (String line : inputList) {
            int from = Integer.parseInt(line.replaceAll("-.*", ""));
            int to = Integer.parseInt(line.replaceAll("^\\d+-", "").replaceAll("\\s.*", ""));
            char letter = line.replaceAll(":.*", "").replaceAll("\\d|-|\\s", "").charAt(0);
            String password = line.replaceAll("^.*\\s", "");
            passwordRules.add(new Password(from, to, letter, password));
        }
    }

    @Override
    public String solve1() {
        int count = 0;
        for (Password password : passwordRules) {
            if (password.valid1()) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String solve2() {
        int count = 0;
        for (Password password : passwordRules) {
            if (password.valid2()) {
                count++;
            }
        }
        return String.valueOf(count);
    }
}
