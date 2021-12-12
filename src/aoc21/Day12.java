package aoc21;

import util.AoCDay;

import java.util.*;


public class Day12 extends AoCDay {

	private static final String START = "start";
	private static final String END = "end";
	private final Map<String, List<String>> adj;
	private String visitTwice;
	private final Set<String> paths;

	public Day12(int day) {
		super(day, 2021, 1);
		this.adj = new HashMap<>();
		this.visitTwice = "z";
		this.paths = new HashSet<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		List<String[]> list = getInputStream().map(line -> line.split("-")).toList();
		for (String[] line : list) {
			if (!adj.containsKey(line[0])) {
				adj.put(line[0], new ArrayList<>());
			}
			adj.get(line[0]).add(line[1]);
			if (!adj.containsKey(line[1])) {
				adj.put(line[1], new ArrayList<>());
			}
			adj.get(line[1]).add(line[0]);

		}
	}

	@Override
	public String solve1() {
		for (String cave : adj.get(START)) {
			traverse(cave, START);
		}
		return String.valueOf(paths.size());
	}

	@Override
	public String solve2() {
		for (String twice : adj.keySet()) {
			if (!isUpper(twice) && !twice.equals(START) && !twice.equals(END)) {
				visitTwice = twice;
				for (String cave : adj.get(START)) {
					traverse(cave, START);
				}
			}
		}
		return String.valueOf(paths.size());
	}

	private boolean isUpper(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}

	private void traverse(String cave, String currPath) {
		currPath += "," + cave.toLowerCase();
		for (String c : adj.get(cave)) {
			if (c.equals(END)) {
				currPath += "," + END;
				paths.add(currPath);
			} else {
				if (c.equals(visitTwice)) {
					if (Arrays.stream(currPath.split(","))
							.filter(l -> l.equals(c)).count() < 2 && adj.containsKey(c)) {
						traverse(c, currPath);
					}
				} else if (!currPath.contains(c) && adj.containsKey(c)) {
					traverse(c, currPath);
				}
			}
		}
	}
}
