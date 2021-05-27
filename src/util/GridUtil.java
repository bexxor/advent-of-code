package util;

import java.util.Arrays;

public class GridUtil {

    public static int countNeighbours(int i, int j, String[][] array2d, String val) {
        int count = 0;
        if (i > 0) {
            if (j > 0 && array2d[i - 1][j - 1].equals(val)) {
                count++;
            }
            if (j < array2d[i].length - 1 && array2d[i - 1][j + 1].equals(val)) {
                count++;
            }
            if (array2d[i - 1][j].equals(val)) {
                count++;
            }
        }
        if (i < array2d.length - 1) {
            if (j > 0 && array2d[i + 1][j - 1].equals(val)) {
                count++;
            }
            if (j < array2d[i].length - 1 && array2d[i + 1][j + 1].equals(val)) {
                count++;
            }
            if (array2d[i + 1][j].equals(val)) {
                count++;
            }
        }
        if (j > 0 && array2d[i][j - 1].equals(val)) {
            count++;
        }
        if (j < array2d[i].length - 1 && array2d[i][j + 1].equals(val)) {
            count++;
        }
        return count;
    }

    public static int countNeighboursQueen(int i, int j, String[][] array2d, String val) {
        int count = 0;
        int sizeX = array2d.length;
        int sizeY = array2d[0].length;

        if (i > 0) {
            if (j > 0) {
                for (int k = 1; i - k >= 0 && j - k >= 0; k++) {
                    String seat = array2d[i - k][j - k];
                    if (seat.equals("L")) {
                        break;
                    }
                    if (seat.equals(val)) {
                        count++;
                        break;
                    }
                }
            }
            if (j < sizeY) {
                for (int k = 1; i - k >= 0 && j + k < sizeY; k++) {
                    String seat = array2d[i - k][j + k];
                    if (seat.equals("L")) {
                        break;
                    }
                    if (seat.equals(val)) {
                        count++;
                        break;
                    }
                }
            }
            for (int k = 1; i - k >= 0; k++) {
                String seat = array2d[i - k][j];
                if (seat.equals("L")) {
                    break;
                }
                if (seat.equals(val)) {
                    count++;
                    break;
                }
            }
        }
        if (i < array2d.length - 1) {
            if (j > 0) {
                for (int k = 1; i + k < sizeX && j - k >= 0; k++) {
                    String seat = array2d[i + k][j - k];
                    if (seat.equals("L")) {
                        break;
                    }
                    if (seat.equals(val)) {
                        count++;
                        break;
                    }
                }
            }
            if (j < sizeY) {
                for (int k = 1; i + k < sizeX && j + k < sizeY; k++) {
                    String seat = array2d[i + k][j + k];
                    if (seat.equals("L")) {
                        break;
                    }
                    if (seat.equals(val)) {
                        count++;
                        break;
                    }
                }
            }
            for (int k = 1; i + k < sizeX; k++) {
                String seat = array2d[i + k][j];
                if (seat.equals("L")) {
                    break;
                }
                if (seat.equals(val)) {
                    count++;
                    break;
                }
            }
        }

        if (j > 0) {
            for (int k = 1; j - k >= 0; k++) {
                String seat = array2d[i][j - k];
                if (seat.equals("L")) {
                    break;
                }
                if (seat.equals(val)) {
                    count++;
                    break;
                }
            }
        }
        if (j < sizeY) {
            for (int k = 1; j + k < sizeY; k++) {
                String seat = array2d[i][j + k];
                if (seat.equals("L")) {
                    break;
                }
                if (seat.equals(val)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    public static int countNeighbours3D(int i, int j, int k, String[][][] arr3d, String val) {
        int count = 0;

        if (i > 0) {
            count += countNeighbours(j, k, arr3d[i - 1], val);
            count += arr3d[i - 1][j][k].equals("#") ? 1 : 0;
        }

        if (i < arr3d.length - 1) {
            count += countNeighbours(j, k, arr3d[i + 1], val);
            count += arr3d[i + 1][j][k].equals("#") ? 1 : 0;

        }
        count += countNeighbours(j, k, arr3d[i], val);
        return count;
    }

    public static int countNeighbours4D(int i, int j, int k, int l, String[][][][] arr4d, String val) {
        int count = 0;

        if (i > 0) {
            count += countNeighbours3D(j, k, l, arr4d[i - 1], val);
            count += arr4d[i - 1][j][k][l].equals("#") ? 1 : 0;
        }

        if (i < arr4d.length - 1) {
            count += countNeighbours3D(j, k, l, arr4d[i + 1], val);
            count += arr4d[i + 1][j][k][l].equals("#") ? 1 : 0;

        }
        count += countNeighbours3D(j, k, l, arr4d[i], val);
        return count;
    }

    public static void print2D(Long[][] square) {
        for (Long[] line : square)
            System.out.println(Arrays.toString(line));
        System.out.println();
    }

    public static void print2D(String[][] square) {
        for (String[] line : square)
            System.out.println(Arrays.toString(line));
        System.out.println();
    }

    public static void print3D(String[][][] cube) {
        for (String[][] square : cube) {
            print2D(square);
            System.out.println();
        }
    }

    public static void print4D(String[][][][] hyperCube) {
        for (String[][][] cube : hyperCube) {
            print3D(cube);
            System.out.println();
        }
    }

    public static String[][] rotate2D(int x, String[][] square) {
        int size = square.length;
        String[][] rotated = new String[size][size];

        switch (x){
            case (1):
                for (int i = 0; i < size; ++i)
                    for (int j = 0; j < size; ++j)
                        rotated[i][j] = square[size - j - 1][i];
                break;
            case (2):
                for (int i = 0; i < size; ++i)
                    for (int j = 0; j < size; ++j)
                        rotated[i][j] = square[size - i - 1][size-j-1];
                break;
            case(3):
                for (int i = 0; i < size; ++i)
                    for (int j = 0; j < size; ++j)
                        rotated[i][j] = square[j][size-i-1];
                break;
            default:
                return square;

        }


        return rotated;
    }

    public static String[][] flip2D(String[][] square) {
        int size = square.length;
        String[][] flipped = new String[size][size];

        for (int i = 0; i < size; ++i)
            System.arraycopy(square[size - i - 1], 0, flipped[i], 0, size);

        return flipped;
    }

    public static boolean sameBorderRight(String[][] left, String[][] right){
        int size = left.length;
        boolean matches = true;
        for (int i = 0; i < size; i++) {
            matches &= left[i][size - 1].equals(right[i][0]);
        }
        return matches;
    }

    public static boolean sameBorderBottom(String[][] top, String[][] bottom){
        return Arrays.equals(top[top.length - 1], bottom[0]);
    }

    public static String[][] removeBorders(String[][] square){
        int size = square.length-2;
        String[][] noBorders = new String[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(square[i + 1], 1, noBorders[i], 0, size);
        }
        return noBorders;
    }
}
