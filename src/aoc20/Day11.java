package aoc20;

import util.AoCDay;
import util.CharCountingUtil;
import util.GridUtil;

import java.util.List;


public class Day11 extends AoCDay {

    private final List<String> inputList;
    private String[][] seatPattern;

    public Day11(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        seatPattern = new String[inputList.size()][inputList.get(0).length()];
        handleInput();
    }

    @Override
    public void handleInput() {
        for (int i = 0, inputListSize = inputList.size(); i < inputListSize; i++) {
            String[] row = inputList.get(i).split("");
            seatPattern[i] = row;
        }
    }

    @Override
    public String solve1() {
        String[][] finalSeatPattern = changeSeats1();
        return String.valueOf(countOccupied(finalSeatPattern));
    }

    @Override
    public String solve2() {
        String[][] finalSeatPattern = changeSeats2();
        return String.valueOf(countOccupied(finalSeatPattern));
    }

    private boolean becomesOccupied1(int i, int j, String[][] newSeatPattern) {
        String seat = seatPattern[i][j];
        newSeatPattern[i][j] = seat;
        if (!seat.equals("L")) {
            return false;
        }
        int count = GridUtil.countNeighbours(i, j, seatPattern, "#");
        if (count == 0) {
            newSeatPattern[i][j] = "#";
            return true;
        }
        return false;
    }

    private boolean becomesEmpty1(int i, int j, String[][] newSeatPattern) {
        String seat = seatPattern[i][j];
        if (!seat.equals("#")) {
            return false;
        }
        int count = GridUtil.countNeighbours(i, j, seatPattern, "#");
        if (count >= 4) {
            newSeatPattern[i][j] = "L";
            return true;
        }
        return false;
    }

    private boolean becomesOccupied2(int i, int j, String[][] newSeatPattern) {
        String seat = seatPattern[i][j];
        newSeatPattern[i][j] = seat;
        if (!seat.equals("L")) {
            return false;
        }
        int count = GridUtil.countNeighboursQueen(i, j, seatPattern, "#");
        if (count == 0) {
            newSeatPattern[i][j] = "#";
            return true;
        }
        return false;
    }

    private boolean becomesEmpty2(int i, int j, String[][] newSeatPattern) {
        String seat = seatPattern[i][j];
        if (!seat.equals("#")) {
            return false;
        }
        int count = GridUtil.countNeighboursQueen(i, j, seatPattern, "#");
        if (count >= 5) {
            newSeatPattern[i][j] = "L";
            return true;
        }
        return false;
    }

    private int countOccupied(String[][] seatPattern) {
        int count = 0;
        for (String[] row : seatPattern) {
            for (String seat : row) {
                if (seat.equals("#")) {
                    count++;
                }
            }
        }
        return count;
    }

    private String[][] changeSeats1() {
        int sizeI = seatPattern.length;
        int sizeJ = seatPattern[0].length;
        boolean seatChanges = true;
        while (seatChanges) {
            String[][] newSeatPattern = new String[inputList.size()][inputList.get(0).length()];
            seatChanges = false;
            for (int i = 0; i < sizeI; i++) {
                for (int j = 0; j < sizeJ; j++) {
                    if (seatPattern[i][j].equals(".")) {
                        newSeatPattern[i][j] = ".";
                        continue;
                    }
                    seatChanges = becomesOccupied1(i, j, newSeatPattern) || becomesEmpty1(i, j, newSeatPattern) || seatChanges;
                }
            }
            this.seatPattern = newSeatPattern;
        }
        return this.seatPattern;
    }

    private String[][] changeSeats2() {
        int sizeI = seatPattern.length;
        int sizeJ = seatPattern[0].length;
        boolean seatChanges = true;
        while (seatChanges) {
            String[][] newSeatPattern = new String[inputList.size()][inputList.get(0).length()];
            seatChanges = false;
            for (int i = 0; i < sizeI; i++) {
                for (int j = 0; j < sizeJ; j++) {
                    if (seatPattern[i][j].equals(".")) {
                        newSeatPattern[i][j] = ".";
                        continue;
                    }
                    seatChanges = becomesOccupied2(i, j, newSeatPattern) || becomesEmpty2(i, j, newSeatPattern) || seatChanges;
                }
            }
            this.seatPattern = newSeatPattern;
        }
        return this.seatPattern;
    }
}
