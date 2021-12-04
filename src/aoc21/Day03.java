package aoc21;

import util.AoCDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day03 extends AoCDay {

	private final List<char[]> bytes;
	private Map<Integer, Map<Character, Integer>> frequencies;
	int len;

	public Day03(int day) {
		super(day, 2021, 2);
		bytes = getInputStream().map(String::toCharArray).collect(Collectors.toList());
		len = bytes.get(0).length;
		handleInput();
	}

	@Override
	public void handleInput() {
		frequencies = new HashMap<>();
		for (char[] b : bytes) {
			for (int i = 0; i < b.length; i++) {
				if (frequencies.containsKey(i)) {
					frequencies.get(i).put(b[i], frequencies.get(i).get(b[i]) + 1);
				} else {
					frequencies.put(i, new HashMap<>());
					frequencies.get(i).put('0', b[i] == '0' ? 1 : 0);
					frequencies.get(i).put('1', b[i] == '1' ? 1 : 0);
				}
			}
		}
	}

	@Override
	public String solve1() {
		char[] gammaRate = new char[len];
		char[] epsilonRate = new char[len];
		for (int i = 0; i < len; i++) {
			int nr0 = frequencies.get(i).get('0');
			int nr1 = frequencies.get(i).get('1');
			gammaRate[i] = nr0 > nr1 ? '0' : '1';
			epsilonRate[i] = nr0 < nr1 ? '0' : '1';
		}
		return String.valueOf(binToDec(gammaRate) * binToDec(epsilonRate));
	}

	@Override
	public String solve2() {
		long oxy = getOxygenGeneratorRating();
		long co2 = getCO2ScrubberRating();
		return String.valueOf(oxy * co2);
	}

	private Map<Character, Integer> findFreqAtPos(int i, List<char[]> bytes) {
		HashMap<Character, Integer> freq = new HashMap<>();
		for (char[] b : bytes) {
			if (freq.containsKey(b[i])) {
				freq.put(b[i], freq.get(b[i]) + 1);
			} else {
				freq.put('0', b[i] == '0' ? 1 : 0);
				freq.put('1', b[i] == '1' ? 1 : 0);
			}
		}
		return freq;
	}

	private long getOxygenGeneratorRating(){
		List<char[]> byteList = bytes;
		for (int i = 0; i < len; i++) {
			Map<Character, Integer> freq = findFreqAtPos(i, byteList);
			char most = freq.get('0')>freq.get('1')?'0':'1';
			int finalI = i;
			List<char[]> curr = byteList.stream().filter(b -> b[finalI]==most).collect(Collectors.toList());
			if(curr.size() == 1){
				return binToDec(curr.get(0));
			} else {
				byteList = curr;
			}
		}
		return 0;
	}

	private long getCO2ScrubberRating(){
		List<char[]> byteList = bytes;
		for (int i = 0; i < len; i++) {
			Map<Character, Integer> freq = findFreqAtPos(i, byteList);
			char least = freq.get('0')>freq.get('1')?'1':'0';
			int finalI = i;
			List<char[]> curr = byteList.stream().filter(b -> b[finalI]==least).collect(Collectors.toList());
			if(curr.size() == 1){
				return binToDec(curr.get(0));
			} else {
				byteList = curr;
			}
		}
		return 0;
	}

	private long binToDec(char[] bin) {
		long res = 0;
		int n = len - 1;
		int bit = 0;
		while (n >= 0) {
			if (bin[n] == '1') {
				// When get binary 1
				res += (1 << (bit));
			}
			n = n - 1;
			// Count number of bits
			bit++;
		}
		return res;
	}
}
