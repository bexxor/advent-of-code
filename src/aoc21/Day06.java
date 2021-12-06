package aoc21;

import util.AoCDay;

import java.util.*;
import java.util.stream.Collectors;

public class Day06 extends AoCDay {

    private final static int STAGE = 9;
    private final LinkedList<Integer> fish;
    private final long[] amount;

    public Day06(int day) {
        super(day, 2021, 2);
        this.fish = Arrays.stream(getFirstLine().split(",")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        this.amount = new long[STAGE];
        handleInput();

    }

    @Override
    public void handleInput() {
        for (int i = 0; i < STAGE; i++) {
            int finalI = i;
            amount[i] = fish.stream().filter(f -> f== finalI).count();
        }
    }

    private void cycle(){
        long birth = amount[0];
        for (int i = 1; i < STAGE; i++) {
            amount[i-1] = amount[i];
        }
        amount[8] = 0;
        amount[6] += birth;
        amount[8] += birth;
    }

    private long countAmount(){
        long sum = 0;
        for (long i: amount) {
            sum += i;
        }
        return sum;
    }

    @Override
    public String solve1() {
        for (int i = 0; i < 80; i++) {
            cycle();
        }
        return String.valueOf(countAmount());
    }

    @Override
    public String solve2() {
        for (int i = 0; i < 256; i++) {
            cycle();
        }
        return String.valueOf(countAmount());
    }
}
