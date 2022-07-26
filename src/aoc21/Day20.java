package aoc21;

import util.AoCDay;

public class Day20 extends AoCDay {

	private final char[] iea;
	private final char[][] inputImage;

	public Day20(int day) {
		super(day, 2021, 2);
		iea = getFirstLine().toCharArray();
		inputImage = getInputStream().filter(l -> l.length() != 512 && !l.isEmpty()).map(String::toCharArray).toArray(char[][]::new);
	}

	@Override
	public void handleInput() {
	}

	@Override
	public String solve1() {
		var out = enhanceImage(inputImage, 2);
		return String.valueOf(count(out));
	}

	@Override
	public String solve2() {
		var out = enhanceImage(inputImage, 50);
		return String.valueOf(count(out));
	}

	private char[][] enhanceImage(char[][] img, int t) {
		var toggle = iea[0] == '#';
		var c1 = '.';
		var c2 = toggle ? '#' : c1;
		var in = img;
		for (int i = 0; i < t / 2; i++) {
			var out = applyAlgorithm(in, c1);
			in = applyAlgorithm(out, c2);
		}
		return in;
	}

	private char[][] applyAlgorithm(char[][] inputImage, char c) {
		var img = zoomOut3(inputImage, c);
		var outImg = new char[img.length][img[0].length];
		for (int i = 0; i < img.length; i++) {
			var line = img[i];
			for (int j = 0; j < line.length; j++) {
				int code = getCode(img, i, j, c);
				outImg[i][j] = iea[code];
			}
		}
		return outImg;
	}

	private int getCode(char[][] img, int i, int j, char c) {
		int len = img[0].length;
		var code = "";
		code += (i > 0 && j > 0) ? img[i - 1][j - 1] : c;
		code += (i > 0) ? img[i - 1][j] : c;
		code += (i > 0 && j + 1 < len) ? img[i - 1][j + 1] : c;
		code += (j > 0) ? img[i][j - 1] : c;
		code += img[i][j];
		code += (j + 1 < len) ? img[i][j + 1] : c;
		code += (i + 1 < img.length && j > 0) ? img[i + 1][j - 1] : c;
		code += (i + 1 < img.length) ? img[i + 1][j] : c;
		code += (i + 1 < img.length && j + 1 < len) ? img[i + 1][j + 1] : c;
		code = code.replaceAll("\\.", "0").replaceAll("#", "1");
		return Integer.parseInt(code, 2);
	}

	private char[][] zoomOut3(char[][] img, char c) {
		int imgLen = img.length;
		int imgWidth = img[0].length;
		var inImg = new char[imgLen + 6][imgWidth + 6];
		for (int i = 0; i < imgLen + 6; i++) {
			for (int j = 0; j < imgWidth + 6; j++) {
				if ((i < 3 || i >= imgLen + 3) || (j < 3 || j >= imgLen + 3)) {
					inImg[i][j] = c;
				} else {
					inImg[i][j] = img[i - 3][j - 3];
				}
			}
		}
		return inImg;
	}

	private int count(char[][] img) {
		int cnt = 0;
		for (char[] line : img) {
			for (char c : line) {
				cnt += (c == '#') ? 1 : 0;
			}
		}
		return cnt;
	}

}
