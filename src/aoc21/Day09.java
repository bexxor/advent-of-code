package aoc21;

import util.AoCDay;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


public class Day09 extends AoCDay {

	private static final Map<String, List<Integer>> DIR = Map.of("U", List.of(-1, 0), "D", List.of(+1, 0), "L", List.of(0, -1), "R", List.of(0, +1));
	private final int X_SIZE;
	private final int Y_SIZE;

	private final List<String> inputList;
	private final List<Point> lowPoints;
	private final Set<Point> visited;
	private Set<Point> basin;
	private final List<Integer> maxValues;
	private final int[][] neighbours;
	private int SUM;

	public Day09(int day) {
		super(day, 2021, 2);
		this.inputList = getInputStream().collect(Collectors.toList());
		this.X_SIZE = inputList.get(0).length();
		this.Y_SIZE = inputList.size();
		this.neighbours = new int[Y_SIZE][X_SIZE];
		this.lowPoints = new ArrayList<>();
		this.visited = new HashSet<>();
		this.basin = new HashSet<>();
		this.maxValues = new ArrayList<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		for (int i = 0; i < inputList.size(); i++) {
			neighbours[i] = Arrays.stream(inputList.get(i).split("")).mapToInt(Integer::parseInt).toArray();
		}
		SUM = findMinNeighbours();
	}

	@Override
	public String solve1() {
		return String.valueOf(SUM);
	}

	@Override
	public String solve2() {
		for (Point p : lowPoints) {
			basin = new HashSet<>();
			getCurrBasin(p);
			if (maxValues.size() < 3) {
				maxValues.add(basin.size());
			} else if (basin.size() > Collections.min(maxValues)) {
				int i = maxValues.indexOf(Collections.min(maxValues));
				maxValues.set(i, basin.size());
			}
		}

		return String.valueOf(maxValues.get(0) * maxValues.get(1) * maxValues.get(2));
	}

	private int findMinNeighbours() {
		int sum = 0;
		for (int y = 0; y < neighbours.length; y++) {
			for (int x = 0; x < neighbours[0].length; x++) {
				Point p = new Point(x, y);
				if (isLowPoint(p)) {
					sum += neighbours[y][x] + 1;
					lowPoints.add(p);
				}
			}
		}
		return sum;
	}

	private void getCurrBasin(Point p) {
		if (visited.contains(p)) {
			return;
		}
		basin.add(p);
		visited.add(p);
		int y = (int) p.getY();
		int x = (int) p.getX();
		if (y > 0) {
			Point point = new Point(x + DIR.get("U").get(1), y + DIR.get("U").get(0));
			if (neighbours[point.y][point.x] < 9) {
				basin.add(point);
				getCurrBasin(point);
			}
		}
		if (y < Y_SIZE - 1) {
			Point point = new Point(x + DIR.get("D").get(1), y + DIR.get("D").get(0));
			if (neighbours[point.y][point.x] < 9) {
				basin.add(point);
				getCurrBasin(point);

			}
		}
		if (x > 0) {
			Point point = new Point(x + DIR.get("L").get(1), y + DIR.get("L").get(0));
			if (neighbours[point.y][point.x] < 9) {
				basin.add(point);
				getCurrBasin(point);

			}
		}
		if (x < X_SIZE - 1) {
			Point point = new Point(x + DIR.get("R").get(1), y + DIR.get("R").get(0));
			if (neighbours[point.y][point.x] < 9) {
				basin.add(point);
				getCurrBasin(point);
			}
		}
	}

	private boolean isLowPoint(Point p) {
		int y = (int) p.getY();
		int x = (int) p.getX();
		if (y > 0 && neighbours[y + DIR.get("U").get(0)][x + DIR.get("U").get(1)] <= neighbours[y][x]) return false;
		if (y < Y_SIZE - 1 && neighbours[y + DIR.get("D").get(0)][x + DIR.get("D").get(1)] <= neighbours[y][x])
			return false;
		if (x > 0 && neighbours[y + DIR.get("L").get(0)][x + DIR.get("L").get(1)] <= neighbours[y][x]) return false;
		return x >= X_SIZE - 1 || neighbours[y + DIR.get("R").get(0)][x + DIR.get("R").get(1)] > neighbours[y][x];
	}
}
