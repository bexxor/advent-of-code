package aoc20.days;

import aocUniverse.Instruction;
import util.AoCDay;

import java.util.ArrayList;
import java.util.List;

public class Day08 extends AoCDay {

    private final List<String> inputList;
    private final List<Instruction> instructions;

    public Day08(int day) {
        super(day, true, 2020, 1);
        this.inputList = super.getInputList();
        this.instructions = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        for (String line : inputList) {
            String instruction = line.replaceAll("\\d+|-|\\+|\\s+", "");
            int number = Integer.parseInt(line.replaceAll("[a-z]+|\\s+", ""));
            instructions.add(new Instruction(instruction, number, false));
        }
    }

    @Override
    public String solve1() {
        return String.valueOf(followInstructions1());
    }

    @Override
    public String solve2() {
        int acc = 0;
        int size = instructions.size();
        for (int i = 0; i < size; i++) {
            resetVisited();
            acc = followInstructions2(i);
            if (acc != -1) {
                break;
            }
        }
        return String.valueOf(acc);
    }


    private int followInstructions1() {
        int size = instructions.size();
        int acc = 0;
        for (int i = 0; i < size; i += 0) {
            Instruction instruction = instructions.get(i);
            if (instruction.isVisited()) {
                break;
            } else {
                instruction.setVisited(true);
            }
            switch (instruction.getType()) {
                case ("jmp"): {
                    i += instruction.getAmount();
                    break;
                }
                case ("acc"): {
                    acc += instruction.getAmount();
                    i++;
                    break;
                }
                case ("nop"): {
                    i++;
                    break;
                }
            }
        }
        return acc;
    }

    private int followInstructions2(int x) {
        int size = instructions.size();
        int acc = 0;
        for (int i = 0; i < size; i += 0) {
            Instruction instruction = instructions.get(i);
            if (instruction.isVisited()) {
                return -1;
            } else {
                instruction.setVisited(true);
            }
            switch (instruction.getType()) {
                case ("jmp"): {
                    if (i == x) {
                        i++;
                    } else {
                        i += instruction.getAmount();
                    }
                    break;
                }
                case ("acc"): {
                    acc += instruction.getAmount();
                    i++;
                    break;
                }
                case ("nop"): {
                    if (i == x) {
                        i += instruction.getAmount();
                    } else {
                        i++;
                    }
                    break;
                }
            }
        }
        return acc;
    }

    private void resetVisited() {
        for (Instruction instruction : instructions) {
            instruction.setVisited(false);
        }
    }
}
