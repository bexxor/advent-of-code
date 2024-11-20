package aoc23;


import util.AoCDay;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Day01 extends AoCDay {

	private Stream<String> calibrations;
	private final Map<String, String> numberMap = Map.of(
//			"zero", "0",
			"one", "1",
			"two", "2",
			"three", "3",
			"four", "4",
			"five", "5",
			"six", "6",
			"seven", "7",
			"eight", "8",
			"nine", "9"
	);
	String numberPattern = String.join("|", numberMap.keySet());
	Pattern pattern = Pattern.compile(numberPattern);

	public Day01(int day) {
		super(day, 2023, 2);
		handleInput();
	}

	@Override
	public void handleInput() {
		this.calibrations = getInputStream();
	}

	@Override
	public String solve1() {
		int sum = calibrations.mapToInt(this::toInt).sum();
		return String.valueOf(sum);

	}

	@Override
	public String solve2() {
		int sum = calibrations.mapToInt(this::getValue).sum();
		return String.valueOf(sum);
	}

	private int toInt(String s) {
		int n = 0;
		String z = s.replaceAll("\\D", "");
		if (!z.isEmpty()) {
			n = z.length() == 1 ? Integer.parseInt(z + z) : Integer.parseInt("" + z.charAt(0) + z.charAt(z.length() - 1));
		}
		return n;
	}

	private String toDigits(String s) {
		if (!s.isEmpty()) {
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				String writtenNumber = matcher.group();
				return numberMap.get(writtenNumber);
			}
		}
		return "";
	}

	private String getFirstDigit(String s) {
		StringBuilder z = new StringBuilder();
		for (char c : s.toCharArray()) {
			z.append(c);
			if (Character.isDigit(c)) return String.valueOf(c);
			String digit = toDigits(z.toString());
			if (!digit.isEmpty()) return digit;
		}
		return "0";
	}

	private String getLastDigit(String s) {
		StringBuilder z = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			z.insert(0, c);
			if (Character.isDigit(c)) return String.valueOf(c);
			String digit = toDigits(z.toString());
			if (!digit.isEmpty()) return digit;
		}
		return "0";
	}

	private int getValue(String s) {
		String first = getFirstDigit(s);
		String last = getLastDigit(s);
		return Integer.parseInt(first + last);
	}
}
