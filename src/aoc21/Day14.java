package aoc21;

import util.AoCDay;

import java.util.*;

public class Day14 extends AoCDay {

	private final Set<Character> chars;
	private final String input;
	private final Map<String, String> rules;
	private final Map<Character, Long> countChars;
	private final Map<String, Long> countPairs;

	public Day14(int day) {
		super(day, 2021, 2);
		input = getInputList().get(0);
		rules = new HashMap<>();
		chars = new HashSet<>();
		countPairs = new HashMap<>();
		countChars = new HashMap<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		getInputStream().filter(l -> l.matches(".. -> .")).map(String::toCharArray).forEach(l -> rules.put("" + l[0] + l[1], "" + l[0] + l[6]));
		getInputStream().filter(l -> l.matches(".. -> .")).map(String::toCharArray).forEach(l -> chars.add(l[6]));
		getInputStream().filter(l -> l.matches(".. -> .")).map(String::toCharArray).forEach(l -> countPairs.put("" + l[0] + l[1], 0L));
		for (Character ch : chars) {
			countChars.put(ch, count(ch));
		}
	}

	@Override
	public String solve1() {
		countAll(10);
		return String.valueOf(Collections.max(countChars.values()) - Collections.min(countChars.values()));
	}

	@Override
	public String solve2() {
		countAll(40);
		return String.valueOf(Collections.max(countChars.values()) - Collections.min(countChars.values()));
	}


	private void countAll(int steps) {
		for (int i = 0; i < input.length() - 1; i++) {
			String toRep = input.substring(i, i + 2);
			countPairs.put(toRep, countPairs.get(toRep) + 1);
		}
		for (int i = 0; i < steps; i++) {
			Map<String, Long> curr = new HashMap<>();
			getInputStream().filter(l -> l.matches(".. -> .")).map(String::toCharArray).forEach(l -> curr.put("" + l[0] + l[1], 0L));
			countPairs.forEach((k, v) -> {
				if (v > 0L) {
					String start = rules.get(k);
					String end = "" + start.charAt(1) + k.charAt(1);
					curr.put(start, curr.get(start) + v);
					curr.put(end, curr.get(end) + v);

					countChars.put(start.charAt(1), countChars.get(start.charAt(1)) + v);
				}
			});
			countPairs.clear();
			countPairs.putAll(curr);
		}
	}

	private long count(char ch) {
		return input.chars().filter(c -> c == ch).count();
	}

}
