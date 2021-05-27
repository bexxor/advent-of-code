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
}
