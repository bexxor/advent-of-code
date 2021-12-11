package aoc21;

import util.AoCDay;
import util.GridUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Day11 extends AoCDay {

	private static final int NR = 10;
	private final int[][] octopuses;
	private final Queue<Point> flashies;
	List<String> input;
	private int sum;

	public Day11(int day) {
		super(day, 2021, 2);
		input = getInputList();
		octopuses = new int[NR][NR];
		flashies = new LinkedList<>();
		sum = 0;
		handleInput();
	}

	@Override
	public void handleInput() {
		for (int i = 0; i < NR; i++) {
			octopuses[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
		}
		GridUtil.print2D(octopuses);
	}

	@Override
	public String solve1() {
		for (int i = 0; i < 100; i++) {
			step();
		}
		return String.valueOf(sum);
	}

	@Override
	public String solve2() {
		int count = 0;
		while (sum != 100) {
			sum = 0;
			step();
			count++;
		}
		return String.valueOf(count);
	}

	private void step() {
		for (int y = 0; y < NR; y++) {
			for (int x = 0; x < NR; x++) {
				octopuses[y][x] += 1;
				if (octopuses[y][x] == 10) {
					flashies.add(new Point(x, y));
				}
			}
		}
		while (!flashies.isEmpty()) {
			increaseNeighbours(flashies.poll());
		}

		for (int i = 0; i < NR; i++) {
			for (int j = 0; j < NR; j++) {
				if (octopuses[i][j] >= 10) {
					octopuses[i][j] = 0;
					sum++;
				}
			}
		}
	}

	public void increaseNeighbours(Point p) {
		int y = p.y;
		int x = p.x;
		int k;
		if (y > 0) {
			if (x > 0) {
				k = ++octopuses[y - 1][x - 1];
				if (k == 10) flashies.add(new Point(x - 1, y - 1));
			}
			if (x < octopuses[y].length - 1) {
				k = ++octopuses[y - 1][x + 1];
				if (k == 10) flashies.add(new Point(x + 1, y - 1));
			}
			k = ++octopuses[y - 1][x];
			if (k == 10) flashies.add(new Point(x, y - 1));

		}
		if (y < octopuses.length - 1) {
			if (x > 0) {
				k = ++octopuses[y + 1][x - 1];
				if (k == 10) flashies.add(new Point(x - 1, y + 1));
			}
			if (x < octopuses[y].length - 1) {
				k = ++octopuses[y + 1][x + 1];
				if (k == 10) flashies.add(new Point(x + 1, y + 1));
			}
			k = ++octopuses[y + 1][x];
			if (k == 10) flashies.add(new Point(x, y + 1));
		}
		if (x > 0) {
			k = ++octopuses[y][x - 1];
			if (k == 10) flashies.add(new Point(x - 1, y));
		}
		if (x < octopuses[y].length - 1) {
			k = ++octopuses[y][x + 1];
			if (k == 10) flashies.add(new Point(x + 1, y));
		}
	}
}
