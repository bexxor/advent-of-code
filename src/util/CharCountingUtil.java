package util;

import java.util.*;

public class CharCountingUtil {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static HashMap<Character, Long> createAlphabetHashMap() {
        HashMap<Character, Long> occurrences = new HashMap<>();
        for (char c : ALPHABET.toCharArray()) {
            occurrences.put(c, 0L);
        }
        return occurrences;
    }

    public static void resetOccurrences(HashMap<Character, Long> occurrences) {
        for (char c : ALPHABET.toCharArray()) {
            occurrences.put(c, 0L);
        }
    }

    public static String find5MostCommonCharsAsString(HashMap<Character, Long> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Character, Long>> list =
                new LinkedList<Map.Entry<Character, Long>>(hm.entrySet());

        Collections.reverse(list);
        // Sort the list
        list.sort(new Comparator<Map.Entry<Character, Long>>() {
            public int compare(Map.Entry<Character, Long> o1,
                               Map.Entry<Character, Long> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        StringBuilder mostCommonFive = new StringBuilder();
        for (int i = list.size() - 1; i > list.size() - 6; i--) {
            mostCommonFive.append(list.get(i).getKey());
        }
        return mostCommonFive.toString();
    }

    public static List<Map.Entry<Character, Long>> orderByHighestValue(HashMap<Character, Long> hm) {
        List<Map.Entry<Character, Long>> list = orderByLowestValue(hm);
        Collections.reverse(list);
        return list;
    }

    public static List<Map.Entry<Character, Long>> orderByLowestValue(HashMap<Character, Long> hm) {
        List<Map.Entry<Character, Long>> list =
                new LinkedList<Map.Entry<Character, Long>>(hm.entrySet());

        list.sort(new Comparator<Map.Entry<Character, Long>>() {
            public int compare(Map.Entry<Character, Long> o1,
                               Map.Entry<Character, Long> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return list;
    }

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


}
