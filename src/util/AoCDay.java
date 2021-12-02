package util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AoCDay {
	protected final int day;
	protected final int part;
	public final Stream<String> inputStream;
	public final Stream<String> testStream;

	public AoCDay(int day, int year, int part) {
		this.part = part;
		this.day = day;
		this.inputStream = InputReaderUtil.inputAsStreamOfLines(year, day);
		this.testStream = InputReaderUtil.getTestInput(year);
	}

	public final String solve() {
		return (this.part != 2) ? this.solve1() : this.solve2();
	}

	public abstract void handleInput();

	public abstract String solve1();

	public abstract String solve2();

	public int getDay() {
		return day;
	}

	public int getPart() {
		return part;
	}

	public String getInput() {
		return inputStream.collect(Collectors.joining());
	}

	public Stream<String> getInputStream() {
		return inputStream;
	}

	public String[] getInputArray() {
		return (String[]) inputStream.toArray();
	}

	public List<String> getInputList() {
		return inputStream.collect(Collectors.toList());
	}

	public List<Integer> getInputAsListOfInts() {
		return inputStream.map(Integer::parseInt).collect(Collectors.toList());
	}

	public List<Long> getInputAsListOfLongs() {
		return inputStream.map(Long::parseLong).collect(Collectors.toList());
	}
}
