package aoc20;

import util.AoCDay;
import util.GridUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day17 extends AoCDay {

    private static final int M = 10;
    private static final int S = M * 3;

    private final List<String> inputList;
    private final String[][][] conwayCube;
    private final String[][][][] hyperCube;
    private final Stack<List<Integer>> change;

    public Day17(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.conwayCube = new String[S][S][S];
        this.hyperCube = new String[S][S][S][S];
        this.change = new Stack<>();
        handleInput();

    }

    @Override
    public void handleInput() {
        if (part == 1) {
            for (int x = 0; x < S; x++)
                for (int y = 0; y < S; y++)
                    for (int z = 0; z < S; z++)
                        conwayCube[x][y][z] = ".";

            for (int j = 0, inputListSize = inputList.size(); j < inputListSize; j++) {
                String[] row = inputList.get(j).split("");
                for (int k = 0, rowSize = row.length; k < rowSize; k++) {
                    conwayCube[M][j + M][k + M] = row[k];
                }
            }
        } else {
            for (int w = 0; w < S; w++)
                for (int x = 0; x < S; x++)
                    for (int j = 0; j < S; j++)
                        for (int z = 0; z < S; z++)
                            hyperCube[w][x][j][z] = ".";

            for (int j = 0, inputListSize = inputList.size(); j < inputListSize; j++) {
                String[] row = inputList.get(j).split("");
                for (int k = 0, rowSize = row.length; k < rowSize; k++) {
                    hyperCube[M][M][j + M][k + M] = row[k];
                }
            }
        }
    }

    @Override
    public String solve1() {
        for (int i = 0; i < 6; i++) {
            conwayCycle3D(conwayCube);
        }
        return String.valueOf(countActive3D(conwayCube));
    }

    @Override
    public String solve2() {
        for (int i = 0; i < 6; i++) {
            conwayCycle4D(hyperCube);
        }
        return String.valueOf(countActive4D(hyperCube));
    }

    private void conwayCycle3D(String[][][] cube) {
        for (int x = 0; x < S; x++)
            for (int y = 0; y < S; y++)
                for (int z = 0; z < S; z++)
                    control3D(x, y, z);
        while (!change.empty()) {
            List<Integer> curr = change.pop();
            String coordinate = cube[curr.get(0)][curr.get(1)][curr.get(2)];
            cube[curr.get(0)][curr.get(1)][curr.get(2)] = coordinate.equals("#") ? "." : "#";
        }
    }

    private void conwayCycle4D(String[][][][] hyperCube) {
        for (int w = 0; w < S; w++)
            for (int x = 0; x < S; x++)
                for (int y = 0; y < S; y++)
                    for (int z = 0; z < S; z++)
                        control4D(w, x, y, z);
        while (!change.empty()) {
            List<Integer> curr = change.pop();
            String coordinate = hyperCube[curr.get(0)][curr.get(1)][curr.get(2)][curr.get(3)];
            hyperCube[curr.get(0)][curr.get(1)][curr.get(2)][curr.get(3)] = coordinate.equals("#") ? "." : "#";
        }
    }

    private void control4D(int i, int j, int k, int l) {
        int count = GridUtil.countNeighbours4D(i, j, k, l, hyperCube, "#");
        String curr = hyperCube[i][j][k][l];
        if (curr.equals("#") && !(count == 2 || count == 3)) {
            change.push(Arrays.asList(i, j, k, l));
        } else if (curr.equals(".") && count == 3) {
            change.push(Arrays.asList(i, j, k, l));
        }
    }

    private void control3D(int i, int j, int k) {
        int count = GridUtil.countNeighbours3D(i, j, k, conwayCube, "#");
        String curr = conwayCube[i][j][k];
        if (curr.equals("#") && !(count == 2 || count == 3)) {
            change.push(Arrays.asList(i, j, k));
        } else if (curr.equals(".") && count == 3) {
            change.push(Arrays.asList(i, j, k));
        }
    }

    public int countActive3D(String[][][] cube) {
        int count = 0;
        for (int x = 0; x < S; x++)
            for (int y = 0; y < S; y++)
                for (int z = 0; z < S; z++)
                    count += cube[x][y][z].equals("#") ? 1 : 0;
        return count;
    }

    public int countActive4D(String[][][][] hyperCube) {
        int count = 0;
        for (String[][][] cube : hyperCube) {
            count += countActive3D(cube);
        }
        return count;
    }
}
