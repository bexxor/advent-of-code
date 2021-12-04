package aoc21;

import util.AoCDay;
import util.InputReaderUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 extends AoCDay {

	private static final int SIZE = 5;
	private final int[] num;
	private int[][][] boards;
	private final List<String> inputList;
	private int nrBoards;


	public Day04(int day) {
		super(day, 2021, 2);
		this.inputList = getInputStream().collect(Collectors.toList());
		this.nrBoards = (inputList.size() - 1) / (SIZE + 1);
		this.boards = new int[nrBoards][SIZE][SIZE];
		this.num = Arrays.stream(inputList.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

		handleInput();
		System.out.println();
	}

	@Override
	public void handleInput() {
		int x = 0;
		int n = 0;
		for (int i = 2; i < inputList.size(); i++) {
			if (inputList.get(i).isEmpty()) {
				n++;
				x = 0;
			} else {
				String line = inputList.get(i);
				if (line.startsWith(" ")) {
					line = inputList.get(i).substring(1);
				}
				boards[n][x] = Arrays.stream(line.split("\s+")).mapToInt(Integer::parseInt).toArray();
				x++;
			}
		}
	}

	@Override
	public String solve1() {
		int bingo;
		for (int n : num) {
			setNum(n);
			for (int i = 0; i < nrBoards; i++) {
				bingo = isBingo(i);
				if (bingo != -1) {
					return String.valueOf(scoreBoard(bingo) * n);
				}
			}
		}
		return String.valueOf(-1);
	}

	@Override
	public String solve2() {
		int bingo;
		List<Integer> unifinished = IntStream.range(0,nrBoards).boxed().collect(Collectors.toList());
		for (int n : num) {
			List<Integer> curr = List.copyOf(unifinished);
			setNum(n);
			for (int i: curr) {
				bingo = isBingo(i);
				if (bingo != -1) {
					if(curr.size()==1){
						return String.valueOf(scoreBoard(curr.get(0))*n);
					}
					int finalBingo = bingo;
					unifinished = unifinished.stream().filter(b -> b!= finalBingo).collect(Collectors.toList());
				}
			}

		}
		return String.valueOf(-1);
	}

	private int scoreBoard(int i) {
		int sum = 0;

		for (int j = 0; j < boards[i].length; j++) {
			for (int k = 0; k < boards[i][j].length; k++) {
				if (boards[i][j][k] != -1) {
					sum += boards[i][j][k];
				}
			}
		}
		return sum;
	}

	private int isBingo(int i) {
			for (int j = 0; j < boards[i].length; j++) {
				if (Arrays.stream(boards[i][j]).sum() == -5) {
					return i;
				}
			}
			for (int k = 0; k < boards[i][0].length; k++) {
				int sum = 0;
				for (int j = 0; j < boards[i].length; j++) {
					sum += boards[i][j][k];
				}
				if (sum == -5) return i;
			}
		return -1;
	}

	private void setNum(int num) {
		for (int i = 0; i < boards.length; i++) {
			for (int j = 0; j < boards[i].length; j++) {
				for (int k = 0; k < boards[i][j].length; k++) {
					if (boards[i][j][k] == num) {
						boards[i][j][k] = -1;
					}
				}
			}
		}
	}
}
