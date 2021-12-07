package aoc21;

import util.AoCDay;

import java.util.Arrays;

public class Day07 extends AoCDay {

	private final int[] crabs;
	private final int[] fuel;
	private final int MAX;


	public Day07(int day) {
		super(day, 2021, 2);
		this.crabs = Arrays.stream(getFirstLine().split(",")).mapToInt(Integer::parseInt).toArray();
		this.MAX = crabs.length;
		this.fuel = new int[MAX];
	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		for (int i = 0; i < MAX; i++) {
			for (int pos : crabs) {
				fuel[i] += Math.abs(pos - i);
			}
		}
		return String.valueOf(getMinFuel());
	}

	private int getMinFuel() {
		return Arrays.stream(fuel).min().getAsInt();
	}

	@Override
	public String solve2() {
		for (int i = 0; i < MAX; i++) {
			for (int pos : crabs) {
				fuel[i] += getFuelCost(Math.abs(pos - i));
			}
		}
		return String.valueOf(getMinFuel());
	}

	private int getFuelCost(int n) {
		return (n * (n + 1)) / 2;
	}
}
