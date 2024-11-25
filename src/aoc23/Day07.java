package aoc23;


import util.AoCDay;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day07 extends AoCDay {

	private Stream<String> test;
	private final Map<Character, Integer> CardValues = Map.of(
			'A', 14,
			'K', 13,
			'Q', 12,
			'J', 11,
			'T', 10);

	private final Map<Character, Integer> CardValues2 = Map.of(
			'A', 14,
			'K', 13,
			'Q', 12,
			'J', 1,
			'T', 10);

	public Day07(int day) {
		super(day, 2023, 1);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.test = getInputStream();
	}

	@Override
	public String solve1() {
		int[] bids = test.map(this::getHand1).sorted(Hand::compare).mapToInt(Hand::bid).toArray();
		int len = bids.length;
		long sum = IntStream.range(0, len).mapToLong(i -> (long) bids[i] * (i + 1)).sum();
		return String.valueOf(sum);
	}

	@Override
	public String solve2() {
//		CardValues.put('J', 1);
		int[] bids = test.map(this::getHand2).sorted(Hand::compare).mapToInt(Hand::bid).toArray();
		int len = bids.length;
		long sum = IntStream.range(0, len).mapToLong(i -> (long) bids[i] * (i + 1)).sum();
		return String.valueOf(sum); //248763975 too high  249287545
	}

	public record Hand(String cards, int[] cardValues, int type, int bid) {


		public static int compare(Hand o1, Hand o2) {
			int t = Integer.compare(o1.type, o2.type);
			if (t != 0) {
				return t;
			}
			for (int i = 0; i < 5; i++) {
				int n = Integer.compare(o1.cardValues[i], o2.cardValues[i]);
				if (n != 0) return n;
			}
			return 0;
		}
	}

	private Hand getHand1(String s) {
		String[] z = s.split("\\s+");
		int type = getType2(z[0]);
		int[] cardValues = getCardValues(z[0], CardValues);
		return new Hand(z[0], cardValues, type, Integer.parseInt(z[1]));
	}

	private Hand getHand2(String s) {
		String[] z = s.split("\\s+");
		int type = getType2(z[0]);
		int[] cardValues = getCardValues(z[0], CardValues2);
		return new Hand(z[0], cardValues, type, Integer.parseInt(z[1]));
	}

	private int[] getCardValues(String s, Map<Character, Integer> cardVals) {
		int[] values = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (cardVals.containsKey(c)) {
				values[i] = cardVals.get(c);
			} else {
				values[i] = Character.getNumericValue(c);
			}
		}
		return values;
	}


	private int containsValue(Map<Character, Integer> map) {
		if (map.size() == 1) return 6;
		else if (map.containsValue(4)) {
			return 5;
		} else if (map.containsValue(3) && map.containsValue(2)) {
			return 4;
		} else if (map.containsValue(3) && map.containsValue(1)) {
			return 3;
		} else if (map.size() == 3 && map.containsValue(2)) {
			return 2;
		} else if (map.size() == 4 && map.containsValue(2)) {
			return 1;
		} else return 0;
	}

	private Map<Character, Integer> createCardMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				int cnt = map.get(c);
				map.put(c, ++cnt);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}


	private int resolveJoker(int j, Map<Character, Integer> map) {
		switch (j) {
			case 5, 4 -> {
				return 6;
			}
			case 3 -> {
				return map.containsValue(2) ? 6 : 5;
			}
			case 2 -> {
				if (map.containsValue(3)) {
					return 6;
				} else if (map.containsValue(2)) {
					return 5;
				}
				return 3;
			}
			case 1 -> {
				if (map.containsValue(4)) {
					return 6;
				} else if (map.containsValue(3)) {
					return 5;
				} else if (map.values().stream().allMatch(i -> i == 2)) {
					return 4;
				} else if (map.containsValue(2)) {
					return 3;
				}
				return 1;
			}
			default -> {
				return 0;
			}
		}
	}


	private int getType2(String s) {
		Map<Character, Integer> map = createCardMap(s);
		if (map.containsKey('J')) {
//			return resolveJoker(map.get('J'), map);
			int[] values = map.values().stream().mapToInt(Integer::intValue).sorted().toArray();
			if (map.size() <= 2) return 6;
			else if (map.containsValue(3) || (map.get('J') == 2 && values[0] == 2 && values[1] == 2 && values[2] == 1)) {
				return 5;
			} else if (map.size() == 3) {//map.get('J')== 1 && values[0]==2 && values[1]==2 ) {
				return 4;
			} else if (map.size() == 4) {
				return 3;
			} else if (map.size() == 5) {
				return 1;
			} else return 0;
		}
		return containsValue(map);
	}

	private int getType1(String s) {
		Map<Character, Integer> map = createCardMap(s);
		return containsValue(map);
	}

	private String print(String s) {
		System.out.println(s);
		return s;
	}
}
