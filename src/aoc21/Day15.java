package aoc21;

import util.AoCDay;
import util.GridUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day15 extends AoCDay {

	private final int SIZE;
	private final List<String> input;
	private final int[][] chitons;


	public Day15(int day) {
		super(day, 2021, 2);
		this.input = getInputList();
		this.SIZE = input.size();
		this.chitons = new int[SIZE][SIZE];
		handleInput();
	}

	@Override
	public void handleInput() {
		for (int i = 0; i < SIZE; i++) {
			chitons[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
		}
	}

	@Override
	public String solve1() {
		return String.valueOf(findLowestRisk(SIZE - 1, SIZE - 1));
	}

	@Override
	public String solve2() {
		return String.valueOf(findLowestRisk((SIZE * 5) - 1, (SIZE * 5) - 1));
	}

	public record Cave(int x, int y, long risk) {
	}

	private boolean isValid(int x, int y, int size) {
		return x >= 0 && y >= 0 && x < size && y < size;
	}

	private long findLowestRisk(int row, int col) {
		long[][] dist = new long[row + 1][col + 1];
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				dist[i][j] = Long.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		PriorityQueue<Cave> queue = new PriorityQueue<>(row * col, Comparator.comparing(Cave::risk));
		queue.add(new Cave(0, 0, dist[0][0]));
		while (!queue.isEmpty()) {
			Cave curr = queue.poll();
			for (int i = 0; i < GridUtil.DIRS; i++) {
				int x = curr.x + GridUtil.dirX[i];
				int y = curr.y + GridUtil.dirY[i];

				if (isValid(x, y, row + 1)) {
					long risk = chitons[y % SIZE][x % SIZE] + (y / SIZE) + (x / SIZE);
					if (risk >= 10) risk -= 9;
					if (dist[y][x] > dist[curr.y][curr.x] + risk) {
						if (dist[y][x] != Integer.MAX_VALUE) {
							Cave adj = new Cave(x, y, dist[y][x]);
							queue.remove(adj);
						}

						dist[y][x] = dist[curr.y][curr.x] + risk;

						queue.add(new Cave(x, y, dist[y][x]));
					}
				}
			}
		}
		return dist[row][col];
	}
}
