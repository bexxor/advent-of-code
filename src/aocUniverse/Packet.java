package aocUniverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Packet {
	int len;
	int V;

	public static Packet decode(String bin) {
		int V = Integer.parseInt(bin.substring(0, 3), 2);
		int T = Integer.parseInt(bin.substring(3, 6), 2);
		return T == 4 ? Literal.decode(bin, V) : Operator.decode(bin, V, T);
	}

	public int sum() {
		return V;
	}

	public long getVal() {
		return 0;
	}
}

class Literal extends Packet {
	static final int HEADER_SIZE = 6;
	long val;

	public static Literal decode(String bin, int V) {
		Literal literal = new Literal();
		literal.V = V;

		StringBuilder value = new StringBuilder();
		int i = HEADER_SIZE;
		while (bin.charAt(i) == '1') {
			value.append(bin, i + 1, i + 5);
			i += 5;
		}
		value.append(bin, i + 1, i + 5);
		literal.val = Long.parseLong(value.toString(), 2);
		literal.len = HEADER_SIZE + i - 1;
		return literal;
	}

	@Override
	public long getVal() {
		return val;
	}
}

class Operator extends Packet {
	static final int HEADER_SIZE = 7;
	int I;
	int T;
	int L;
	List<Packet> packets;

	public static Operator decode(String bin, int V, int T) {
		Operator operator = new Operator();
		operator.V = V;
		operator.T = T;
		operator.I = bin.charAt(6) == '0' ? 15 : 11;
		operator.L = Integer.parseInt(bin.substring(HEADER_SIZE, HEADER_SIZE + operator.I), 2);
		operator.packets = new ArrayList<>();

		return operator.I == 15 ? decodeByLength(bin, operator) : decodeByAmount(bin, operator);
	}

	public static Operator decodeByLength(String bin, Operator operator) {
		int pos = 0;
		while (pos < operator.L) {
			Packet p = Packet.decode(bin.substring(pos + HEADER_SIZE + operator.I));
			pos += p.len;
			operator.packets.add(p);
		}
		operator.len = HEADER_SIZE + operator.I + operator.L;
		return operator;
	}

	public static Operator decodeByAmount(String bin, Operator operator) {
		int pos = 0;
		for (int i = 0; i < operator.L; i++) {
			operator.packets.add(i, Packet.decode(bin.substring(pos + HEADER_SIZE + operator.I)));
			pos += operator.packets.get(i).len;
		}
		operator.len = HEADER_SIZE + operator.I + pos;
		return operator;
	}

	@Override
	public int sum() {
		int sum = packets.stream().map(Packet::sum).reduce(0, Integer::sum);
		return sum + V;
	}

	@Override
	public long getVal() {
		return switch (T) {
			case 0 -> packets.stream().map(Packet::getVal).reduce(0L, Long::sum);
			case 1 -> packets.stream().map(Packet::getVal).reduce(1L, (a, b) -> a * b);
			case 2 -> packets.stream().map(Packet::getVal).min(Long::compareTo).orElse(0L);
			case 3 -> packets.stream().map(Packet::getVal).max(Long::compareTo).orElse(0L);
			case 5 -> packets.get(0).getVal() > packets.get(1).getVal() ? 1 : 0;
			case 6 -> packets.get(0).getVal() < packets.get(1).getVal() ? 1 : 0;
			case 7 -> Objects.equals(packets.get(0).getVal(), packets.get(1).getVal()) ? 1 : 0;
			default -> -1L;
		};
	}
}
