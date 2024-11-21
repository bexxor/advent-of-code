package aoc23;


import util.AoCDay;

import java.util.List;


public class Day03 extends AoCDay {

	private List<String> schematic;
	private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	private int rows;
	private int cols;

	public Day03(int day) {
		super(day, 2023, 2);
		handleInput();
		rows = schematic.size();
		cols = schematic.getFirst().length();
	}

	@Override
	public void handleInput() {
		this.schematic = getInputList();
	}

	@Override
	public String solve1() {
		int i = getSum();
		return String.valueOf(i);

	}

	@Override
	public String solve2() {
		int i = getGearRatio(); // 82454502 is too low
		return String.valueOf(i);
	}

	private int getGearRatio() {
		int sum = 0;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				char current = schematic.get(row).charAt(col);
				if (current == '*') {
					sum += getRatio(row, col);
				}
			}
		}
		return sum;
	}

	private int getRatio(int row, int col) {
		int ratio = 0;
		int count = 0;
		int n1 = 0;
		int n2 = 0;
		int[] vals = {2, 3, 4, 8};

		for (int dir = 0; dir < dx.length; dir++) {
			int newRow = row + dx[dir];
			int newCol = col + dy[dir];

			// Ensure within bounds
			if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
				char neighbor = schematic.get(newRow).charAt(newCol);
				if (Character.isDigit(neighbor)) {
					if (dir != 2 && dir != 3 && dir != 4 && dir != 7 && Character.isDigit(schematic.get(newRow).charAt(newCol + 1))) {
						continue;
					}
					int n = getNumber(newRow, newCol);
					if (count == 0) {
						n1 = n;
						count++;
					} else if (count == 1) {
						n2 = n;
						count++;
					} else {
						count++;
					}
				}
			}
		}
		if (count == 2) {
			ratio = n1 * n2;
		}
		return ratio;
	}

	private int getSum() {
		int sum = 0;
		boolean currAdjacent = false;
		// Traverse each cell in the test
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				char current = schematic.get(row).charAt(col);

				// Check if the current cell is a number
				if (Character.isDigit(current)) {
					if (col > 0 && currAdjacent && Character.isDigit(schematic.get(row).charAt(col - 1))) {
						continue;
					}
					// Check if the number is adjacent to a symbol
					currAdjacent = isAdjacentToSymbol(row, col);
					if (currAdjacent) {
						sum += getNumber(row, col);
					}
				} else {
					currAdjacent = false;
				}
			}
		}

		return sum;
	}

	private int getNumber(int row, int col) {
		StringBuilder s = new StringBuilder();
		int c = col - 1;
		while (c >= 0 && Character.isDigit(schematic.get(row).charAt(c))) {
			s.insert(0, schematic.get(row).charAt(c));
			c--;
		}
		while (col < cols && Character.isDigit(schematic.get(row).charAt(col))) {
			s.append(schematic.get(row).charAt(col));
			col++;
		}
		return Integer.parseInt(s.toString());

	}

	private boolean isAdjacentToSymbol(int row, int col) {
		// Check all 8 adjacent cells
		for (int direction = 0; direction < dx.length; direction++) {
			int newRow = row + dx[direction];
			int newCol = col + dy[direction];

			// Ensure within bounds
			if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
				char neighbor = schematic.get(newRow).charAt(newCol);

				// Check if neighbor is a symbol (non-digit, non-period)
				if (!Character.isDigit(neighbor) && neighbor != '.') {
					return true;
				}
			}
		}

		return false;
	}


	private String print(String s) {
		System.out.println(s);
		return s;
	}


}
