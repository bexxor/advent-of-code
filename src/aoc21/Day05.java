package aoc21;

import util.AoCDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends AoCDay {

	private static final int MAX = 1000;
	private final int[][] diagram;
	private List<int[]> lines;

	public Day05(int day) {
		super(day, 2021, 2);
		this.diagram = new int[MAX][MAX];
		this.lines = new ArrayList<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		lines = getInputStream().map(l -> Arrays.stream(l.split(",|\s->\s")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());
		for (int[] row : diagram) {
			Arrays.fill(row, 0);
		}
	}

	@Override
	public String solve1() {
		fillDiagram();
		return String.valueOf(countOverlaps());
	}

	@Override
	public String solve2() {
		fillDiagram();
		return String.valueOf(countOverlaps());
	}

	private void fillDiagram() {
		for (int[] line : lines) {
			int x1 = line[0];
			int y1 = line[1];
			int x2 = line[2];
			int y2 = line[3];
			if (x1 == x2) {
				for (int y = Math.min(y1, y2), maxY = y == y1 ? y2 : y1; y <= maxY; y++) {
					diagram[y][x1] += 1;
				}
			} else if (y1 == y2) {
				for (int x = Math.min(x1, x2), maxX = x == x1 ? x2 : x1; x <= maxX; x++) {
					diagram[y1][x] += 1;
				}
			} else if (part == 2) {
				if (x1 <= x2 && y1 <= y2) {
					for (int i = 0, diff = x2 - x1; i <= diff; i++) {
						diagram[y1 + i][x1 + i] += 1;
					}
				} else if (x1 <= x2) {
					for (int i = 0, diff = x2 - x1; i <= diff; i++) {
						diagram[y1 - i][x1 + i] += 1;
					}
				} else if (y1 > y2) {
					for (int i = 0, diff = x1 - x2; i <= diff; i++) {
						diagram[y1 - i][x1 - i] += 1;
					}
				} else {
					for (int i = 0, diff = x1 - x2; i <= diff; i++) {
						diagram[y1 + i][x1 - i] += 1;
					}
				}
			}
		}
	}

	private int countOverlaps() {
		int count = 0;
		for (int[] line : diagram) {
			for (int point : line) {
				count += point >= 2 ? 1 : 0;
			}
		}
		return count;
	}
}
