package aoc23;


import util.AoCDay;

import java.util.Arrays;


public class Day06 extends AoCDay {

	private int[] times1;
	private long times2;
	private int[] dists1;
	private long dists2;

	public Day06(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		String[] input = getInputString().split("\\n");
		this.times1 = Arrays.stream(input[0].split(":\\s+")[1].split("\\s+")).mapToInt(Integer::parseInt).toArray();
		this.times2 = Long.parseLong(input[0].split(":\\s+")[1].replaceAll("\\s+", ""));
		this.dists1 = Arrays.stream(input[1].split(":\\s+")[1].split("\\s+")).mapToInt(Integer::parseInt).toArray();
		this.dists2 = Long.parseLong(input[1].split(":\\s+")[1].replaceAll("\\s+", ""));
	}

	@Override
	public String solve1() {
		int len = times1.length;
		int[] wins = new int[len];
		for (int i = 0; i < len; i++) {
			int sum = 0;
			int mid = times1[i] / 2;
			int x = mid * (times1[i] - mid);
			while (x > dists1[i]) {
				mid--;
				x = mid * (times1[i] - mid);
				sum++;
			}
			wins[i] = times1[i] % 2 == 0 ? sum * 2 - 1 : sum * 2;
		}
		int res = 1;
		for (int i = 0; i < len; i++) {
			res *= wins[i];
		}
		return String.valueOf(res);

	}

	@Override
	public String solve2() {
		int result = 0;
		long sum = 0;
		long mid = times2 / 2;
		long x = mid * (times2 - mid);
		while (x > dists2) {
			mid--;
			x = mid * (times2 - mid);
			sum++;
		}
		return String.valueOf(times2 % 2 == 0 ? sum * 2 - 1 : sum * 2);
	}

	private String print(String s) {
		System.out.println(s);
		return s;
	}


}
