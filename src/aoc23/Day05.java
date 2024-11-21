package aoc23;


import util.AoCDay;

import java.util.Arrays;


public class Day05 extends AoCDay {

	private String input;
	private long[] seeds;
	long[][][] almanac;

	public Day05(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.input = getInputString();
		parseInput();
	}

	@Override
	public String solve1() {
		long min = Long.MAX_VALUE;
		for (long seed : seeds) {
			long loc = lookupLocation(seed);
			min = Math.min(loc, min);
		}
		return String.valueOf(min);
	}

	@Override
	public String solve2() {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < seeds.length - 1; i += 2) {
			for (int j = 0; j < seeds[i + 1]; j++) {
				long loc = lookupLocation(seeds[i] + j);
				min = Math.min(loc, min);
			}
		}
		return String.valueOf(min);
	}

	private long lookupLocation(long seed) {
		long curr = seed;
		for (long[][] map : almanac) {
			for (long[] entry : map) {
				if (isBetween(curr, entry[1], entry[1] + entry[2])) {
					curr = curr + (entry[0] - entry[1]);
					break;
				}
			}
		}
		return curr;
	}

	private boolean isBetween(long i, long x, long y) {
		return x <= i && i < y;
	}

	private void parseInput() {
		String[] in = input.split("\\n\\n");
		this.almanac = new long[in.length - 1][][];
		this.seeds = parseValues(in[0]);

		for (int i = 1; i < in.length; i++) {
			String[] tmp = parseString(in[i]);
			long[][] map = new long[tmp.length][];
			for (int j = 0; j < map.length; j++) {
				map[j] = Arrays.stream(tmp[j].split("\\s")).mapToLong(Long::parseLong).toArray();
			}
			almanac[i - 1] = map;
		}
	}

	private String[] parseString(String entry) {
		return entry.replaceAll(".*:\\s+", "").split("\\n+");
	}

	private long[] parseValues(String entry) {
		String[] temp = entry.replaceAll(".*:\\s+", "").split("\\s+");
		return Arrays.stream(temp).mapToLong(Long::parseLong).toArray();
	}

	private String print(String s) {
		System.out.println(s);
		return s;
	}
}
