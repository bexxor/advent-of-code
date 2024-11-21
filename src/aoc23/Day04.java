package aoc23;


import util.AoCDay;

import java.util.Arrays;
import java.util.stream.Stream;


public class Day04 extends AoCDay {

	private Stream<String> cards;

	public Day04(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.cards = getInputStream();
	}

	@Override
	public String solve1() {
		int points = cards.map(this::getPoints).mapToInt(Integer::intValue).sum();
		return String.valueOf(points);

	}

	@Override
	public String solve2() {
		int[] wins = cards.map(this::getWinners).mapToInt(Integer::intValue).toArray();

		int[] amount = new int[wins.length];
		Arrays.fill(amount, 1);
		int j = 0;
		for (int i = 0; i < wins.length; i++) {
			while (j < wins[i]) {
				amount[i + j + 1] += amount[i];
				j++;
			}
			j = 0;
		}
		int w = Arrays.stream(amount).sum();
		return String.valueOf(w);  // 2000 too low
	}

	private int getWinners(String s) {
		int p = 0;
		String[] card = getCard(s);
		int[] w = getWins(card);
		int[] y = getYours(card);
		for (int k : w) {
			for (int i : y) {
				if (k == i) {
					p++;
				} else if (k <= i) {
					break;
				}
			}
		}
		return p;

	}


	private int getPoints(String s) {
		int p = 0;
		String[] card = getCard(s);
		int[] w = getWins(card);
		int[] y = getYours(card);
		for (int k : w) {
			for (int i : y) {
				if (k == i) {
					p = p == 0 ? 1 : p * 2;
				} else if (k <= i) {
					break;
				}
			}
		}
		return p;
	}

	private int[] getWins(String[] card) {
		return Arrays.stream(card[0].split("\\s+")).mapToInt(Integer::parseInt).sorted().toArray();
	}

	private int[] getYours(String[] card) {
		return Arrays.stream(card[1].split("\\s+")).mapToInt(Integer::parseInt).sorted().toArray();
	}

	private String[] getCard(String s) {
		String z = s.replaceAll("Card\\s+\\d+:\\s+", "");
		return z.split("\\s+\\|\\s+");
	}

	private String print(String s) {
		System.out.println(s);
		return s;
	}
}
