package aoc21;

import util.AoCDay;

import java.util.List;
import java.util.stream.Collectors;

public class Day02 extends AoCDay {

	private final List<String[]> directions;
	private final Submarine sub;

	public Day02(int day) {
		super(day, 2021, 2);
		this.directions = getInputStream().map(l -> l.split(" ")).collect(Collectors.toList());
		this.sub = new Submarine();
	}

	@Override
	public void handleInput() {

	}

	@Override
	public String solve1() {
		this.directions.forEach(d -> sub.move1(d[0], d[1]));
		return String.valueOf(sub.x * sub.y);
	}

	@Override
	public String solve2() {
		this.directions.forEach(d -> sub.move(d[0], d[1]));
		return String.valueOf(sub.x * sub.y);
	}
}
