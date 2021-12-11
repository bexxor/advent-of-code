package aoc21;

import util.AoCDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class Day10 extends AoCDay {

	private final List<String> brackets;
	private Stack<Integer> stack;
	private List<Long> completion;
	int sum;
	private static final char b10 = '('; //  40
	private static final char b11 = ')'; //  41
	private static final char b20 = '['; //  91
	private static final char b21 = ']'; //  93
	private static final char b30 = '{'; // 123
	private static final char b31 = '}'; // 125
	private static final char b40 = '<'; //  60
	private static final char b41 = '>'; //  62

	public Day10(int day) {
		super(day, 2021, 2);
		this.brackets = getInputStream().collect(Collectors.toList());
		this.stack = new Stack<>();
		this.completion = new ArrayList<>();
		this.sum = 0;
	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		for (String line : brackets) {
			isCorrupted(line.toCharArray());
		}
		return String.valueOf(sum);
	}

	@Override
	public String solve2() {
		for (String line : brackets) {
			isCorrupted(line.toCharArray());
		}
		completion = completion.stream().sorted().collect(Collectors.toList());
		return String.valueOf(completion.get(completion.size() / 2));
	}


	private void isCorrupted(char[] line) {
		stack = new Stack<>();
		for (int c : line) {
			int diff = !stack.empty() ? Math.abs(stack.peek() - c) : 0;
			if (diff == 2 || diff == 1) {
				stack.pop();
			} else if (c == b11 || c == b21 || c == b31 || c == b41) {
				sum += getPoints(c);
				return;
			} else {
				stack.push(c);
			}
		}
		long i = 0;
		while (!stack.empty()) {
			i *= 5;
			i += getComPoints(stack.pop());
		}
		completion.add(i);
	}

	private int getComPoints(int c) {
		if (c == b10) return 1;
		if (c == b20) return 2;
		if (c == b30) return 3;
		if (c == b40) return 4;
		return 0;
	}

	private int getPoints(int c) {
		if (c == b11) return 3;
		if (c == b21) return 57;
		if (c == b31) return 1197;
		if (c == b41) return 25137;
		return 0;
	}
}
