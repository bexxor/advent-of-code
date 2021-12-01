package aoc21;

import aoc21.days.*;
import util.AoCDay;
import util.InputReaderUtil;

import java.time.LocalDate;


public class Main21 {
    private static final int day = LocalDate.now().getDayOfMonth();

    public static void main(String[] args) {
        solveDay(day);
    }

    public static void solveDay(int day) {
        AoCDay d = null;
        switch (day) {
            case 1 -> d = new Day01(day);
            case 2 -> d = new Day02(day);
            case 3 -> d = new Day03(day);
            case 4 -> d = new Day04(day);
            case 5 -> d = new Day05(day);
            case 6 -> d = new Day06(day);
            case 7 -> d = new Day07(day);
            case 8 -> d = new Day08(day);
            case 9 -> d = new Day09(day);
            case 10 -> d = new Day10(day);
            case 11 -> d = new Day11(day);
            case 12 -> d = new Day12(day);
            case 13 -> d = new Day13(day);
            case 14 -> d = new Day14(day);
            case 15 -> d = new Day15(day);
            case 16 -> d = new Day16(day);
            case 17 -> d = new Day17(day);
            case 18 -> d = new Day18(day);
            case 19 -> d = new Day19(day);
            case 20 -> d = new Day20(day);
            case 21 -> d = new Day21(day);
            case 22 -> d = new Day22(day);
            case 23 -> d = new Day23(day);
            case 24 -> d = new Day24(day);
            case 25 -> d = new Day25(day);
            default -> System.out.println("something went wrong... (:");
        }
        if (d != null) {
            System.out.println(d.solve());
        }
    }
}
