package aoc20.days;

import util.AoCDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Day10 extends AoCDay {

    private final List<String> inputList;
    private List<Integer> adapters;
    private final List<Integer> lenSubList;

    public Day10(String day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.adapters = new ArrayList<>();
        this.lenSubList = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        this.adapters = this.inputList.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        this.adapters.add(adapters.get(adapters.size() - 1) + 3);
        this.adapters.add(0, 0);
        int last = 0;
        for (int i = 0; i < adapters.size() - 1; i++) {
            int curr = adapters.get(i);
            if (curr + 3 == adapters.get(i + 1)) {
                lenSubList.add(i + 1 - last);
                last = i + 1;
            }
        }
    }

    @Override
    public String solve1() {
        int n1 = 0;
        int n3 = 0;
        for (int i = 0, adaptersSize = adapters.size(); i < adaptersSize; i++) {
            int adapter = adapters.get(i);
            int prevAdapter = 0;
            if (i != 0) {
                prevAdapter = adapters.get(i - 1);
            }
            if (adapter - 3 == prevAdapter) {
                n3++;
            } else if (adapter - 1 == prevAdapter) {
                n1++;
            }
        }
        return String.valueOf(n1 * n3);
    }

    @Override
    public String solve2() {
        long result = 1;
        for (int len : lenSubList) {
            switch (len) {
                case (3):
                    result *= 2;
                    break;
                case (4):
                    result *= 4;
                    break;
                case (5):
                    result *= 7;
                    break;
                default:
                    result *= -1;
            }
        }
        return String.valueOf(result);
    }
}
