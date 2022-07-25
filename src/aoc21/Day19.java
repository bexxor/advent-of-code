package aoc21;

import util.AoCDay;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day19 extends AoCDay {
	private static final int MIN = 12;
	private final String testString;
	private List<Scanner> scanners;
	private List<String> input;

	private Set<Loc> finalLocations;
	private Set<Loc> finalDistances;

	record Scanner(List<Loc> locs, int rot, List<List<Loc>> rotations, List<List<Loc>> distances) {
		void addLoc(Loc loc) {
			locs.add(loc);
		}

		boolean equals(Scanner other) {
			int same = 0;
			for (Loc thisLoc : locs) {
				for (Loc otherLoc : other.locs) {
					// TODO
					continue;
				}
			}
			return false;
		}

		void generateRotations() {
			for (int i = 0; i < 24; i++) {
				List<Loc> rotation = new ArrayList<>();
				for (Loc loc : locs) {
					rotation.add(loc.rotate(i));
				}
				rotations.add(rotation);
			}
		}

		void generateDistances() {
			for (List<Loc> rots : rotations) {
				var dists = new ArrayList<Loc>();
				for (int i = 0; i < rots.size(); i++) {
					for (int j = i + 1; j < rots.size(); j++) {
						dists.add(rots.get(i).diff(rots.get(j)));
					}
				}
				distances.add(dists);
			}
		}
	}

	record Loc(int x, int y, int z) {

		public Loc diff(Loc o) {
			return new Loc(x - o.x, y - o.y, z - o.z);
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || this.getClass() != o.getClass()) return false;
			Loc loc = (Loc) o;
			return this.x == loc.x && this.y == loc.y && this.z == loc.z;
		}

		public int hashCode() {
			return Objects.hash(x, y, z);
		}

		@Override
		public String toString() {
			return String.format("(%d, %d, %d)", x, y, z);
		}

		public Loc rotate(int i) {
			Loc l = this.turn(i % 4);
			return l.changeFace(i % 6);
		}

		private Loc turn(int i) {
			return switch (i) {
				case 1 -> new Loc(-y, x, z);
				case 2 -> new Loc(-x, -y, z);
				case 3 -> new Loc(y, -x, z);
				default -> this;
			};
		}

		private Loc changeFace(int i) {
			return switch (i) {
				case 1 -> new Loc(x, -y, -z);
				case 2 -> new Loc(x, -z, y);
				case 3 -> new Loc(-y, -z, x);
				case 4 -> new Loc(-x, -z, -y);
				case 5 -> new Loc(y, -z, -x);
				default -> this;
			};
		}

		static Loc toLoc(String str) {
//			System.out.println(str);
			String[] loc = str.split(",");
			return new Loc(Integer.parseInt(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[1]));
		}
	}


	public Day19(int day) {
		super(day, 2021, 1);
//		this.inputList = super.getInputList();
//		this.testStream = super.getTestStream();
		this.testString = super.getTestString();
		this.scanners = new ArrayList<>();
		this.finalLocations = new HashSet<>();
		this.finalDistances = new HashSet<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		input = Arrays.stream(testString.split("\n*--- scanner \\d+ ---\n")).filter(i -> !i.equals("")).toList();
		for (String locations : input) {
			List<Loc> locs = Arrays.stream(locations.split("\n")).map(Loc::toLoc).toList();
			Scanner scanner = new Scanner(locs, 0, new ArrayList<>(), new ArrayList<>());
			scanner.generateRotations();
			scanner.generateDistances();
			scanners.add(scanner);
		}
		finalLocations.addAll(scanners.get(0).locs);
		finalDistances.addAll(scanners.get(0).distances.get(0));
	}

	@Override
	public String solve1() {
		print();
		var dif = findDiffs(scanners.get(1));
		System.out.println(dif);
		return String.valueOf(-1);
	}

	@Override
	public String solve2() {
		return String.valueOf(-1);
	}

	private void print() {
//        List<String> testies = testStream.toList();
//        System.out.println(testies.get(0));
//        System.out.println(testies.get(1));
//		System.out.println(scanners.get(0).locs);
//		System.out.println(scanners.get(0).rotations.get(0));
//		System.out.println(scanners.get(0).rotations.get(1));

	}

	private int findDiff(Scanner s) {
		for (int i = 0; i < 24; i++) {
			var rot = s.rotations.get(i);
			var diffs = new HashMap<Loc, Integer>();
			for (Loc l1 : finalLocations) {
				for (Loc l2 : rot) {
					diffs.merge(l1.diff(l2), 1, Integer::sum);
				}
			}
			System.out.println(diffs);
			System.out.println(Collections.max(diffs.values()));
			var diff = diffs.entrySet().stream().filter(e -> e.getValue() >= MIN).map(Map.Entry::getKey).findAny();
			if (diff.isPresent()) {
				System.out.println(diff);
				return i;
			}
		}
		return -1;
	}

	private int findDiffs(Scanner s) {
		for (int i = 0; i < 24; i++) {
			var dist = s.distances.get(i);
			var diffs = new HashMap<Loc, Integer>();
			for (Loc l1 : finalDistances) {
				for (Loc l2 : dist) {
					diffs.merge(l2.diff(l1), 1, Integer::sum);
				}
			}
//			System.out.println(diffs);
			System.out.println(Collections.max(diffs.values()));
			var diff = diffs.entrySet().stream().filter(e -> e.getValue() >= MIN).map(Map.Entry::getKey).findAny();
			if (diff.isPresent()) {
				System.out.println(diff);
				return i;
			}
		}
		return -1;
	}

}
