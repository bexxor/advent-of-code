package aoc21;

import util.AoCDay;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day17 extends AoCDay {

	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;
	private final Point S;
	private final Point V;
	private final List<Integer> height;
	private final Set<Point> velocities;

	public Day17(int day) {
		super(day, 2021, 2);
		String[] in = getFirstLine().replaceAll(".*?x=| y=", "").split("\\.\\.|,");
		minX = Integer.parseInt(in[0]);
		maxX = Integer.parseInt(in[1]);
		maxY = Integer.parseInt(in[2]);
		minY = Integer.parseInt(in[3]);
		S = new Point(0, 0);
		V = new Point(0, 0);
		height = new ArrayList<>();
		velocities = new HashSet<>();

	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < maxX*2; i++) {
			for (int j = maxY; j < maxX*2; j++) {
				S.setLocation(0,0);
				V.setLocation(i,j);
				height.clear();
				while (S.x <= maxX && S.y >= maxY) {
					height.add(step());
					if (inTarget(S.x, S.y)) {
						max = Math.max(max, height.stream().max(Integer::compareTo).orElse(max));
					}
				}
			}
		}
		return String.valueOf(max);
	}

	@Override
	public String solve2() {
		for (int i = 1; i < maxX*2; i++) {
			for (int j = maxY; j < maxX * 2; j++) {
				S.setLocation(0,0);
				V.setLocation(i,j);
				height.clear();
				while (S.x <= maxX && S.y >= maxY) {
					int x = step();
					height.add(x);
					if (inTarget(S.x, S.y)) {
						velocities.add(new Point(i, j));
					}
				}
			}
		}
		return String.valueOf(velocities.size());
	}

	private boolean inTarget(int x, int y) {
		return x >= minX && x <= maxX && y <= minY && y >= maxY;
	}

	private int step() {
		S.x += V.x;
		S.y += V.y;
		if (V.x > 0) V.x--;
		else if (V.x < 0) V.x++;
		V.y--;
		return S.y;
	}
}
