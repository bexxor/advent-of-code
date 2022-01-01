package aoc21;

import aocUniverse.Packet;
import util.AoCDay;

public class Day16 extends AoCDay {

	private final String bin;

	public Day16(int day) {
		super(day, 2021, 2);
		String hex = getFirstLine();
		bin = hexToBin(hex);
	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		Packet p = Packet.decode(bin);
		return String.valueOf(p.sum());
	}

	@Override
	public String solve2() {
		Packet p = Packet.decode(bin);
		return String.valueOf(p.getVal());
	}

	private String hexToBin(String hex) {
		StringBuilder bin = new StringBuilder();
		for (char c : hex.toCharArray()) {
			bin.append(binValueOfHex(c));
		}
		return bin.toString();
	}

	private String binValueOfHex(char c) {
		return switch (c) {
			case '0' -> "0000";
			case '1' -> "0001";
			case '2' -> "0010";
			case '3' -> "0011";
			case '4' -> "0100";
			case '5' -> "0101";
			case '6' -> "0110";
			case '7' -> "0111";
			case '8' -> "1000";
			case '9' -> "1001";
			case 'A' -> "1010";
			case 'B' -> "1011";
			case 'C' -> "1100";
			case 'D' -> "1101";
			case 'E' -> "1110";
			case 'F' -> "1111";
			default -> "";
		};
	}
}