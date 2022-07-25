package util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AoCDay {
	protected final int year;
	protected final int day;
	protected final int part;

	public AoCDay(int day, int year, int part) {
		this.year = year;
		this.part = part;
		this.day = day;
	}

	public final String solve() {
		return (this.part != 2) ? this.solve1() : this.solve2();
	}

	public abstract void handleInput();

	public abstract String solve1();

	public abstract String solve2();

	public String getInput() {
		return InputReaderUtil.inputAsStreamOfLines(year, day).collect(Collectors.joining());
	}

	public String getFirstLine() {
		return InputReaderUtil.getFirstLine(year, day);
	}

	public String getTestLine() {
		return InputReaderUtil.getTestFirstLine(year);
	}

	public Stream<String> getInputStream() {
		return InputReaderUtil.inputAsStreamOfLines(year, day);
	}

	public Stream<String> getTestStream() {
		return InputReaderUtil.getTestInput(year);
	}

	public String getTestString(){
		return InputReaderUtil.getTestInputAsString(year);
	}

	public String getInputString(){
		return InputReaderUtil.getInputAsString(year, day);
	}

	public List<String> getTestList() {
		return InputReaderUtil.getTestInput(year).collect(Collectors.toList());
	}

	public String[] getInputArray() {
		return (String[]) InputReaderUtil.inputAsStreamOfLines(year, day).toArray();
	}

	public List<String> getInputList() {
		return InputReaderUtil.inputAsStreamOfLines(year, day).collect(Collectors.toList());
	}

	public List<Integer> getInputAsListOfInts() {
		return InputReaderUtil.inputAsStreamOfLines(year, day).map(Integer::parseInt).collect(Collectors.toList());
	}

	public List<Long> getInputAsListOfLongs() {
		return InputReaderUtil.inputAsStreamOfLines(year, day).map(Long::parseLong).collect(Collectors.toList());
	}
}
