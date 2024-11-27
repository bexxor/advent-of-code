package aoc23;


import util.AoCDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static util.MathUtil.lcm;


public class Day08 extends AoCDay {

	private Stream<String> test;
	private int[] instructions;
	private Map<String, String[]> network;

	public Day08(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		String[] s = getInputString().split("\\n\\n");
		String[] rows = s[1].split("\\n");
		this.instructions = Arrays.stream(s[0].replaceAll("L", "0").replaceAll("R", "1").split("")).mapToInt(Integer::parseInt).toArray();
		this.network = new HashMap<>();
		for (String row : rows) {
			mapNetwork(row);
		}
	}

	private void mapNetwork(String s) {
		String[] row = s.replaceAll("[()]", "").split(" = ");
		this.network.put(row[0], row[1].split(",\\s+"));
	}

	@Override
	public String solve1() {
		return String.valueOf(walk("AAA", "ZZZ")); // 177 too low
	}

	@Override
	public String solve2() {
		long steps = lcm(network
				.keySet()
				.stream()
				.filter(s -> s.endsWith("A"))
				.mapToLong(start -> walk(start, ""))
				.toArray());
		return String.valueOf(steps);
	}


	private int walk(String start, String goal) {
		String curr = start;
		int cnt = 0;
		while (true) {
			curr = network.get(curr)[instructions[cnt % (instructions.length)]];
			cnt++;
			if (goal.isEmpty() ? curr.endsWith("Z") : curr.equals(goal)) {
				return cnt;
			}
		}
	}

	private String print(String s) {
		System.out.println(s);
		return s;
	}
}
