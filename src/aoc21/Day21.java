package aoc21;

import util.AoCDay;

import java.util.*;

public class Day21 extends AoCDay {

	private final static int DET_MAX = 1000;
	private final static int DIRAC_MAX = 21;
	private final int player1;
	private final int player2;
	private final HashMap<Integer, Integer> rollCount;
	private final HashMap<State, long[]> memo;


	public Day21(int day) {
		super(day, 2021, 2);
		String[]  input = super.getInputString().replaceAll("Player \\d starting position: ", "").split("\n");
		this.player1 = Integer.parseInt(input[0]);
		this.player2 = Integer.parseInt(input[1]);
		this.rollCount = new HashMap<>();
		this.memo = new HashMap<>();
		handleInput();
	}

	record DetState(int p1, int p2, int s1, int s2, int r, Dice dice, boolean won) {
		public String toString() {
			return String.format("p1: %d, s1: %d; p2: %d, s2: %d; r: %d, d: %d%n", p1, s1, p2, s2, r, dice.d);
		}
	}

	record State(int[] p, int[] s, int turn) {

		public State(int p1, int p2, int s1, int s2, int turn) {
			this(new int[]{p1, p2}, new int[]{s1, s2}, turn);
		}
		public String toString() {
			return String.format("p1: %d, s1: %d; p2: %d, s2: %d; t: %d", p[0], s[0], p[1], s[1], turn);
		}
	}

	record Dice(int d, int roll) {
	}

	@Override
	public void handleInput() {
		List<Integer> rolls = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				for (int k = 1; k <= 3; k++) {
					rolls.add(i + j + k);
				}
			}
		}
		for (int i : rolls) {
			rollCount.merge(i, 1, Integer::sum);
		}
	}

	@Override
	public String solve1() {
		DetState s = new DetState(player1, player2, 0, 0, 0, new Dice(0, 0), false);
		s = playDet(s);
		return String.valueOf(Math.min(s.s1, s.s2) * s.r);
	}

	@Override
	public String solve2() {
		State s = new State(player1, player2, 0, 0, 0);
		long[] games = playDirac(s);
		return String.valueOf(Math.max(games[0], games[1]));
	}

	private long[] playDirac(State s) {
		int curr = s.turn%2;
		var other = (s.turn + 1) % 2;
		if (s.s[0] >= DIRAC_MAX) {
			return new long[]{1L, 0L};
		} else if (s.s[1] >= DIRAC_MAX) {
			return new long[]{0L, 1L};
		} else if (memo.containsKey(s)) {
			return memo.get(s);
		}
		var round = new long[2];
		for (int key : rollCount.keySet()) {
			int pNew = (s.p[curr] + key) % 10;
			int sNew = s.s[curr] + (pNew == 0 ? 10 : pNew);
			int[] player = new int[2];
			player[curr] = pNew;
			player[other] = s.p[other];
			int[] scores = new int[2];
			scores[curr] = sNew;
			scores[other] = s.s[other];
			long[] newRound = playDirac(new State(player, scores, s.turn+1));
			round[0]+= newRound[0] * rollCount.get(key);
			round[1]+= newRound[1] * rollCount.get(key);
		}
		memo.put(s, round);
		return round;
	}

	private DetState playDet(DetState s) {
		while (!s.won) {
			s = deterministicTurn1(s);
			if (s.won) return s;
			s = deterministicTurn2(s);
			if (s.won) return s;
		}
		return s;
	}

	private DetState deterministicTurn1(DetState s) {
		var dice = rollDeterministicDice(s.dice);
		var r = s.r + 3;
		var p1 = (s.p1 + dice.roll) % 10;
		var s1 = s.s1;
		s1 += p1 == 0 ? 10 : p1;
		if (s1 >= DET_MAX) return new DetState(p1, s.p2, s1, s.s2, r, dice, true);
		return new DetState(p1, s.p2, s1, s.s2, r, dice, false);
	}

	private DetState deterministicTurn2(DetState s) {
		var dice = rollDeterministicDice(s.dice);
		var r = s.r + 3;
		var p2 = (s.p2 + dice.roll) % 10;
		var s2 = s.s2;
		s2 += p2 == 0 ? 10 : p2;
//			print();
		if (s2 >= DET_MAX) return new DetState(s.p1, p2, s.s1, s2, r, dice, true);
		return new DetState(s.p1, p2, s.s1, s2, r, dice, false);
	}

	private Dice rollDeterministicDice(Dice dice) {
		var roll = 0;
		int d = dice.d;
		for (int i = 0; i < 3; i++) {
			d = d % 100 + 1;
			roll += d;
		}
		return new Dice(d, roll);
	}

}
