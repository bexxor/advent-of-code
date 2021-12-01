package aoc20.days;

import util.AoCDay;

import java.util.HashMap;
import java.util.List;

public class Day14 extends AoCDay {

    private final List<String> inputList;
    private HashMap<String, Long> memory;

    public Day14(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.memory = new HashMap<>();
        handleInput(0);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public String solve1() {
        StringBuilder mask = new StringBuilder();
        for (String line : inputList) {
            if (line.startsWith("mask")) {
                mask = new StringBuilder(line.replaceAll("mask = ", ""));
                continue;
            }
            String  mem = line.replaceAll("mem\\[|].*", "");
            String num = Integer.toBinaryString(Integer.parseInt(line.replaceAll(".*\\s", "")));
            StringBuilder bin = new StringBuilder(String.format("%36s", num).replace(' ', '0'));
            for (int i = 0; i < 36; i++) {
                bin.setCharAt(i, mask.charAt(i) == 'X' ? bin.charAt(i) : mask.charAt(i));
            }
            long val = Long.parseLong(bin.toString(), 2);
            memory.put(mem, val);
        }
        return String.valueOf(memory.values().stream().mapToLong(Long::longValue).sum());
    }

    @Override
    public String solve2() {
        StringBuilder mask = new StringBuilder();
        for (String line : inputList) {
            if (line.startsWith("mask")) {
                mask = new StringBuilder(line.replaceAll("mask = ", ""));
                continue;
            }
            long val = Long.parseLong(line.replaceAll(".*\\s", ""));
            String mem = Integer.toBinaryString(Integer.parseInt(line.replaceAll("mem\\[|].*", "")));
            StringBuilder bin = new StringBuilder(String.format("%36s", mem).replace(' ', '0'));
            for (int i = 0; i < 36; i++) {
                bin.setCharAt(i, mask.charAt(i) == '0' ? bin.charAt(i) : mask.charAt(i)== '1'? '1': mask.charAt(i));
            }
            int floating = bin.toString().replaceAll("\\d", "").length();
            for (int i = 0; i < Math.pow(2, floating); i++) {
                String binary = String.format("%36s", Integer.toBinaryString(i)).replace(' ', '0');
                StringBuilder addr = new StringBuilder(bin.toString());
                int j = 35;
                while (addr.toString().contains("X")){
                    int index = addr.lastIndexOf("X");
                    addr.replace(index,index+1, binary.substring(j, j+1));
                    j--;
                }
                memory.put(addr.toString(), val);
            }
        }
        return String.valueOf(memory.values().stream().mapToLong(Long::longValue).sum());
    }
}
