package aoc20.days;

import util.AoCDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Day09 extends AoCDay {

    private final List<String> inputList;
    private final List<Long> numbers;

    public Day09(String day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.numbers = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        for (String line : this.inputList) {
            numbers.add(Long.parseLong(line));
        }
    }

    @Override
    public String solve1() {
        int size = numbers.size();
        for (int i = 25; i < size; i++) {
            long curr = numbers.get(i);
            if (!sumOfEvery2Numbers(numbers.subList(i - 25, i), curr)) {
                return String.valueOf(curr);
            }
        }
        return String.valueOf(-1);
    }

    @Override
    public String solve2() {
        long wrongNumber = Long.parseLong(solve1());
        int k = numbers.indexOf(wrongNumber);
        for (int i = k - 1; i > 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                List<Long> sublist = numbers.subList(j, i);
                long sum = sublist.stream().mapToLong(Long::longValue).sum();
                if (sum > wrongNumber) {
                    break;
                }
                if (sum == wrongNumber) {
                    long result = Collections.min(sublist) + Collections.max(sublist);
                    return String.valueOf(result);
                }
            }
        }
        return String.valueOf(-1);
    }


    private boolean sumOfEvery2Numbers(List<Long> numbers, long sum) {
        for (int i = 0; i < numbers.size(); i++) {
            long num1 = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                long num2 = numbers.get(j);
                if (num1 + num2 == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}