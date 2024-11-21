package aoc23;


import util.AoCDay;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class Day02 extends AoCDay {

	private Stream<String> games;
	private Stream<String> test;
	private final Map<String, Integer> CUBES = Map.of(
			"red", 12,
			"green", 13,
			"blue", 14
	);
	String[] colors = {"red", "green", "blue"};


	public Day02(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.games = getInputStream();
		this.test = getTestStream();
	}

	@Override
	public String solve1() {
		int sum = games.mapToInt(this::checkPossibility).sum();
		return String.valueOf(sum);

	}

	@Override
	public String solve2() {
		int sum = games.mapToInt(this::getPower).sum();
		return String.valueOf(sum);
	}

	private int checkPossibility(String s) {
		String gameNr = s.replaceAll("Game |:.*", "");
		int nr = Integer.parseInt(gameNr);
		if (isPossible(s)) {
			return nr;
		}
		return 0;

	}

	private int getPower(String s) {
		Map<String, Integer> g = convertDraws(s);
		return g.get("red") * g.get("blue") * g.get("green");
	}

	private Map<String, Integer> convertDraws(String s) {
		Map<String, Integer> result = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
		String[] draws = s.replaceAll("Game \\d+: ", "").split("; ");
		for (String draw : draws) {
			String[] types = draw.split(", ");
			for (String type : types) {
				String[] cube = type.split(" ");
				int amount = Integer.parseInt(cube[0]);
				if (result.get(cube[1]) < amount) {
					result.put(cube[1], amount);
				}
			}
		}
		return result;
	}

	private boolean isPossible(String s) {
		Map<String, Integer> g = convertDraws(s);
		for (String col : colors) {
			if (g.get(col) > CUBES.get(col)) {
				return false;
			}
		}
		return true;
	}

}
