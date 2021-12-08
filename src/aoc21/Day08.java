package aoc21;

import util.AoCDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day08 extends AoCDay {

	List<List<String[]>> lines;

	public Day08(int day) {
		super(day, 2021, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.lines = getInputStream().map(l -> l.split(" \\| ")).map(n -> Arrays.stream(n).map(l -> l.split(" ")).collect(Collectors.toList())).collect(Collectors.toList());
	}

	@Override
	public String solve1() {
		long result = lines.stream().map(l -> Arrays.stream(l.get(1)).filter(n -> n.length() == 2 || n.length() == 3 || n.length() == 4 || n.length() == 7).count()).reduce(0L, Long::sum);
		return String.valueOf(result);
	}

	@Override
	public String solve2() {
		int sum = 0;
		for (List<String[]> line : lines) {
			StringBuilder result = new StringBuilder();
			String[] numbers = findNumbers(line.get(0));
			for (String r : line.get(1)) {
				for (int i = 0; i < 10; i++) {
					String other = numbers[i];
					if (r.length() == other.length() && strContainsAllOfOther(other, r)) {
						result.append(i);
						break;
					}
				}
			}
			sum += Integer.parseInt(result.toString());
		}
		return String.valueOf(sum);
	}

	private String[] findNumbers(String[] numbers) {
		String[] mappings = new String[10];
		Arrays.stream(numbers).filter(n -> n.length() == 2).findFirst().ifPresent(s -> mappings[1] = s);
		Arrays.stream(numbers).filter(n -> n.length() == 3).findFirst().ifPresent(s -> mappings[7] = s);
		Arrays.stream(numbers).filter(n -> n.length() == 4).findFirst().ifPresent(s -> mappings[4] = s);
		Arrays.stream(numbers).filter(n -> n.length() == 7).findFirst().ifPresent(s -> mappings[8] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 6 && strContainsAllOfOther(n, mappings[4]))).findFirst().ifPresent(s -> mappings[9] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 6 && !n.equals(mappings[9]) && strContainsAllOfOther(n, mappings[1]))).findFirst().ifPresent(s -> mappings[0] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 6 && !n.equals(mappings[9]) && !n.equals(mappings[0]))).findFirst().ifPresent(s -> mappings[6] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 5 && strContainsAllOfOther(n, mappings[1]))).findFirst().ifPresent(s -> mappings[3] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 5 && !n.equals(mappings[3]) && strContainsAllOfOther(mappings[6], n))).findFirst().ifPresent(s -> mappings[5] = s);
		Arrays.stream(numbers).filter(n -> (n.length() == 5 && !n.equals(mappings[3]) && !n.equals(mappings[5]))).findFirst().ifPresent(s -> mappings[2] = s);
		return mappings;
	}

	private boolean strContainsAllOfOther(String str, String other) {
		for (char o : other.toCharArray()) {
			if (str.indexOf(o) < 0) {
				return false;
			}
		}
		return true;
	}
}
