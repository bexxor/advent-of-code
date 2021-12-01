package aoc20.days;

import util.AoCDay;
import util.GridUtil;

import java.util.*;

public class Day20 extends AoCDay {

    private static final int SIZE = 10;
    private final List<String> inputList;
    private final HashMap<Long, List<String[][]>> tiles;
    private final int side;
    private HashMap<List<Integer>, Long> result;
    private HashMap<List<Integer>, String[][]> placement;
    private Set<Long> corners;


    public Day20(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.tiles = new HashMap<>();
        handleInput(0);
        this.side = (int) Math.sqrt(tiles.size());
        this.result = new HashMap<>();
        this.placement = new HashMap<>();
        this.corners = new HashSet<>();
    }

    @Override
    public void handleInput() {
        int i, size = inputList.size(), row = 0;
        long id = -1L;
        String[][] tile = new String[SIZE][SIZE];
        for (i = 0; i < size; i++) {
            String line = inputList.get(i);
            if (line.equals("")) {
                tiles.put(id, tilePositions(tile));
                tile = new String[SIZE][SIZE];
                row = 0;
            } else if (line.startsWith("Tile")) {
                id = Long.parseLong(line.replaceAll("\\D+", ""));
            } else {
                tile[row] = line.split("");
                row++;
            }
        }
        if(!inputList.get(i-1).equals("")){
            tiles.put(id, tilePositions(tile));
        }
    }

    @Override
    public String solve1() {
        for (Long id : tiles.keySet()) {
            if(hasTwoNeighbours(id, tiles.get(id).get(0))){
                corners.add(id);
            }
        }
        long result = corners.stream().reduce(1L, (product, c) -> product * c);
        return String.valueOf(result);
    }

    @Override
    public String solve2() {
        /*
        System.out.println();
        GridUtil.print2D(tiles.get(1181L).get(0));
        GridUtil.print2D(GridUtil.removeBorders(tiles.get(1181L).get(0)));

         */

        return String.valueOf(-1);
    }

    private List<String[][]> assembleImage(){
        List<String[][]> image = new ArrayList<>();
        for (int i = 0; i < side; i++) {
            System.out.println();
        }
        return image;
    }



    private String[][] position(int i, String[][] sq) {
        switch (i) {
            case (0):
                return sq;
            case (1):
            case (2):
            case (3):
                return GridUtil.rotate2D(i, sq);
            case (4):
                return GridUtil.flip2D(sq);
            case (5):
            case (6):
            case (7):
                return GridUtil.flip2D(GridUtil.rotate2D(i - 4, sq));
            default:
                System.out.println("wrong number");
                return sq;
        }
    }

    private List<String[][]> tilePositions(String[][] sq) {
        List<String[][]> tiles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            tiles.add(position(i, sq));
        }
        return tiles;
    }

    private boolean hasTwoNeighbours(long id, String[][] sq) {
        int neighbours = 0;
        for (List<String[][]> tiles : tiles.values()) {
            for (String[][] tile : tiles) {
                if(tile == sq){
                    break;
                }
                if (GridUtil.sameBorderBottom(sq, tile)) {
                    neighbours++;
                    break;
                } else if (GridUtil.sameBorderBottom(tile, sq)) {
                    neighbours++;
                    break;
                } else if (GridUtil.sameBorderRight(sq, tile)) {
                    neighbours++;
                    break;
                } else if (GridUtil.sameBorderRight(tile, sq)) {
                    neighbours++;
                    break;
                }
            }
        }
        return neighbours == 2;
    }
}
