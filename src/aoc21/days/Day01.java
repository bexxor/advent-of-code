package aoc21.days;


import util.AoCDay;

import java.util.List;
import java.util.stream.IntStream;


public class Day01 extends AoCDay {

	private List<Integer> measurements;

	public Day01(int day) {
		super(day, 2021, 1);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.measurements = getInputAsListOfInts();
	}

	@Override
	public String solve1() {
		long i = IntStream
				.range(0, measurements.size() - 1)
				.filter(m -> measurements.get(m) < measurements.get(m + 1))
				.count();
		return String.valueOf(i);
	}

	@Override
	public String solve2() {
		this.measurements = IntStream
				.range(0, measurements.size() - 2)
				.map(m -> measurements.get(m) + measurements.get(m + 1) + measurements.get(m + 2))
				.boxed().toList();
		System.out.println();
		return this.solve1();
	}
}
