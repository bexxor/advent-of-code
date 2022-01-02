package aoc21;

import util.AoCDay;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 extends AoCDay {

	private final List<String> numbers;
	private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+");
	private static final Pattern PATTERN_PAIR = Pattern.compile("\\[\\d+,\\d+]");
	private static final String NUMBER = "0123456789";
	private Deque<Character> stack;

	public Day18(int day) {
		super(day, 2021, 2);
		numbers = getInputList();
		this.stack = new LinkedList<>();
	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		return getMagnitude(getInputStream().reduce(this::add).orElse(""));
	}

	@Override
	public String solve2() {
		int max = 0;
		for (String number : numbers) {
			for (String other : numbers) {
				if (!number.equals(other)) {
					String sum = add(number, other);
					max = Math.max(max, Integer.parseInt(getMagnitude(sum)));
				}
			}
		}
		return String.valueOf(max);
	}


	private String add(String x, String y) {
		return reduce("[" + x + "," + y + "]");
	}

	private String reduce(String n) {
		String prev = "";
		while (!prev.equals(n)) {
			prev = n;
			n = explode(n);
			if (prev.equals(n)) {
				n = split(n);
			}
		}
		return n;
	}

	private String split(String n) {
		stack.clear();
		for (int i = 0; i < n.length(); i++) {
			char curr = n.charAt(i);
			if (curr == ']') {
				while (!stack.isEmpty() && stack.peek() != '[') {
					stack.pop();
				}
				if (!stack.isEmpty()) stack.pop();
			} else if (NUMBER.contains(String.valueOf(curr)) && NUMBER.contains(String.valueOf(stack.peek()))) {
				Matcher m = PATTERN_NUMBER.matcher(n);
				String toSplit = m.find(i - 1) ? m.group() : "**";
				int val = Integer.parseInt(toSplit);
				int h = val % 2 == 0 ? val / 2 : val / 2 + 1;
				return n.replaceFirst(toSplit, "[" + val / 2 + "," + h + "]");
			} else {
				stack.push(n.charAt(i));
			}
		}
		return n;
	}

	private String explode(String n) {
		stack.clear();
		for (int i = 0; i < n.length(); i++) {
			char curr = n.charAt(i);
			if (curr == ']') {
				while (!stack.isEmpty() && stack.peek() != '[') {
					stack.pop();
				}
				if (!stack.isEmpty()) stack.pop();
			} else if (curr == '[' && stack.stream().filter(s -> s == '[').count() >= 4) {
				Matcher pairMatcher = PATTERN_PAIR.matcher(n);
				String toExplode = pairMatcher.find(i) ? pairMatcher.group() : "**";
				Matcher m = PATTERN_NUMBER.matcher(toExplode);

				int first = m.find() ? Integer.parseInt(m.group()) : -1;
				int last = m.find(3) ? Integer.parseInt(m.group()) : -1;
				String before = n.substring(0, i);
				Matcher beforeMatcher = PATTERN_NUMBER.matcher(before);
				String nrBefore = "";
				while (beforeMatcher.find()) {
					nrBefore = beforeMatcher.group();
				}
				if (!nrBefore.isEmpty()) {
					int prev = Integer.parseInt(nrBefore) + first;
					StringBuilder sb = new StringBuilder(n.substring(0, i));
					int j = sb.lastIndexOf(nrBefore);
					sb.replace(j, j + nrBefore.length(), String.valueOf(prev));
					before = sb.toString();
				}


				String after = n.substring(i + toExplode.length());
				Matcher afterMatcher = PATTERN_NUMBER.matcher(after);
				String nrAfter = afterMatcher.find() ? afterMatcher.group() : "";
				if (!nrAfter.isEmpty()) {
					int next = Integer.parseInt(nrAfter) + last;
					after = after.replaceFirst(nrAfter, String.valueOf(next));
				}
				return before + "0" + after;
			} else {
				stack.push(n.charAt(i));
			}
		}
		return n;
	}


	private String getMagnitude(String n) {
		stack.clear();
		for (int i = 0; i < n.length(); i++) {
			char curr = n.charAt(i);
			if (curr == ']') {
				StringBuilder first = new StringBuilder();
				StringBuilder second = new StringBuilder();
				while (!stack.peek().equals(',')) {
					second.append(stack.pop());
				}
				stack.pop();
				while (!stack.peek().equals('[')) {
					first.append(stack.pop());
				}
				stack.pop();
				first.reverse();
				second.reverse();
				int val = 3 * Integer.parseInt(first.toString()) + 2 * Integer.parseInt(second.toString());
				String result = String.valueOf(val);
				for (int j = 0; j < result.length(); j++) {
					stack.push(result.charAt(j));
				}
			} else {
				stack.push(curr);
			}
		}
		StringBuilder magnitude = new StringBuilder();
		while (!stack.isEmpty()) {
			magnitude.append(stack.pop());
		}
		return magnitude.reverse().toString();
	}
}
