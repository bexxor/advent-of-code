package aoc21;

import util.AoCDay;
import util.GridUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 extends AoCDay {

	private static final String FOLD = "fold along ";
	private List<Instruction> folds;
	private Set<Point> dots;
	private String[][] origami;

	public Day13(int day) {
		super(day, 2021, 2);
		this.folds = new ArrayList<>();
		handleInput();
	}

	@Override
	public void handleInput() {
		this.folds = getInputStream()
				.filter(l -> l.startsWith(FOLD))
				.map(l -> new Instruction(l.replaceAll(FOLD + "|=\\d+", ""), Integer.parseInt(l.replaceAll(".*?=", ""))))
				.collect(Collectors.toList());
		this.dots = getInputStream()
				.filter(l -> !l.isEmpty() && !l.startsWith(FOLD))
				.map(l -> Arrays.stream(l.split(","))
						.mapToInt(Integer::parseInt).toArray())
				.map(p -> new Point(p[0], p[1]))
				.collect(Collectors.toSet());
	}

	@Override
	public String solve1() {
		return String.valueOf(dots.stream().map(d -> fold(d, folds.get(0))).distinct().count());
	}

	@Override
	public String solve2() {
		for (Instruction fold : folds) {
			dots = dots.stream().map(d -> fold(d, fold)).collect(Collectors.toSet());
		}
		printDots();
		return String.valueOf(-1);
	}

	public record Instruction(String dir, int i) {
	}

	private Point fold(Point dot, Instruction instr) {
		if (instr.dir.equals("y")) {
			dot.y = dot.y > instr.i ? 2 * instr.i - dot.y : dot.y;
		} else {
			dot.x = dot.x > instr.i ? 2 * instr.i - dot.x : dot.x;
		}
		System.out.println(dot);
		return dot;
	}

	private void printDots() {
		origami = new String[6][40];
		GridUtil.fill(origami, ".");
		for (Point p : dots) {
			origami[p.y][p.x] = "#";
		}
		GridUtil.print2D(origami);
	}
}
