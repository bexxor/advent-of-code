package util;

public class MathUtil {

	public static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	public static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	public static long lcm(long[] input) { // least common multiple
		long result = input[0];
		for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
		return result;
	}
}
