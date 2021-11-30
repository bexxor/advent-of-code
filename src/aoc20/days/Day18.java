package aoc20.days;

import util.AoCDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day18 extends AoCDay {

    private final List<String> inputList;
    private final List<List<String>> equations;

    public Day18(int day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.equations = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        for (String line : inputList) {
            List<String> eq = Arrays.asList(line.replaceAll("\\(", "( ").replaceAll("\\)", " )").split(" "));
            equations.add(eq);
        }
    }

    @Override
    public String solve1() {
        long sum = 0;
        for (List<String> eq : equations) {
            long result = calculateLeftToRight(eq);
            sum += result;
        }
        return String.valueOf(sum);
    }

    @Override
    public String solve2() {
        long sum = 0;
        for (List<String> eq : equations) {
            long result = calculatePlusPrecedent(eq);
            sum += result;
        }
        return String.valueOf(sum);

    }

    private long calculateLeftToRight(List<String> eq) {
        Stack<String> output = new Stack<>();
        Stack<String> operator = new Stack<>();
        for (String x : eq) {
            switch (x) {
                case ("*"):
                case ("+"):
                    if (!operator.empty() && !operator.peek().equals("(")) {
                        output.push(solve(output.pop(), output.pop(), operator.pop()));
                    }
                    operator.push(x);
                    break;
                case ("("):
                    operator.push(x);
                    break;
                case (")"):
                    while (!operator.peek().equals("(")) {
                        output.push(solve(output.pop(), output.pop(), operator.pop()));
                    }
                    operator.pop();
                    break;
                default:
                    output.push(x);
                    break;
            }
        }
        while (!operator.empty()) {
            output.push(solve(output.pop(), output.pop(), operator.pop()));
        }
        return Long.parseLong(output.pop());
    }

    private String solve(String op1, String op2, String operator) {
        long x = Long.parseLong(op1);
        long y = Long.parseLong(op2);
        long result = x;
        switch (operator) {
            case ("*"):
                result *= y;
                break;
            case ("+"):
                result += y;
                break;
        }
        return String.valueOf(result);
    }

    private long calculatePlusPrecedent(List<String> eq) {
        Stack<String> output = new Stack<>();
        Stack<String> operator = new Stack<>();
        for (String x : eq) {
            switch (x) {
                case ("*"):
                    if (!operator.empty() && !operator.peek().equals("(")) {
                        output.push(solve(output.pop(), output.pop(), operator.pop()));
                    }
                    operator.push(x);
                    break;
                case ("+"):
                    if (!operator.empty() && !operator.peek().equals("(") && !operator.peek().equals("*")) {
                        output.push(solve(output.pop(), output.pop(), operator.pop()));
                    }
                    operator.push(x);
                    break;
                case ("("):
                    operator.push(x);
                    break;
                case (")"):
                    while (!operator.peek().equals("(")) {
                        output.push(solve(output.pop(), output.pop(), operator.pop()));
                    }
                    operator.pop();
                    break;
                default:
                    output.push(x);
                    break;
            }
        }
        while (!operator.empty()) {
            output.push(solve(output.pop(), output.pop(), operator.pop()));
        }
        return Long.parseLong(output.pop());
    }


}
