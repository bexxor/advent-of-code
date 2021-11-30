package aoc20;

import aoc20.days.*;
import util.*;


public class Main20 {
    private static final String day = "01";
    private static final String part = "1";

    public static void main(String[] args) {
        solveDay(day, part);
    }

    public static void solveDay(String day, String part) {
        AoCDay d = null;
        switch (day) {
            case "01":
                d = new Day01(day);
                break;

            case "02":
                d = new Day02(day);
                break;

            case "03":
                d = new Day03(day);
                break;

            case "04":
                d = new Day04(day);
                break;

            case "05":
                d = new Day05(day);
                break;

            case "06":
                d = new Day06(day);
                break;

            case "07":
                d = new Day07(day);
                break;

            case "08":
                d = new Day08(day);
                break;

            case "09":
                d = new Day09(day);
                break;

            case "10":
                d = new Day10(day);
                break;

            case "11":
                d = new Day11(day);
                break;

            case "12":
                d = new Day12(day);
                break;

            case "13":
                d = new Day13(day);
                break;

            case "14":
                d = new Day14(day);
                break;

            case "15":
                d = new Day15(day);
                break;

            case "16":
                d = new Day16(day);
                break;

            case "17":
                d = new Day17(day);
                break;

            case "18":
                d = new Day18(day);
                break;

            case "19":
                d = new Day19(day);
                break;

            case "20":
                d = new Day20(day);
                break;

            case "21":
                d = new Day21(day);
                break;

            case "22":
                d = new Day22(day);
                break;

            case "23":
                d = new Day23(day);
                break;

            case "24":
                d = new Day24(day);
                break;

            case "25":
                d = new Day25(day);
                break;

            default:
                System.out.println("something went wrong... (:");
        }
        if (d != null) {
            System.out.println(d.solve(part));
        }
    }
}
