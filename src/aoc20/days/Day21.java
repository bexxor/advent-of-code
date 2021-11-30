package aoc20.days;

import util.AoCDay;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 extends AoCDay {

    private final List<String> inputList;
    private final HashMap<List<String>, List<String>> menus;
    private final Set<String> ingredients;
    private final Set<String> allergens;
    private final Set<String> contaminated;
    private final HashMap<String, List<String>> allergenOfIngredient;

    public Day21(int day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.menus = new HashMap<>();
        this.ingredients = new HashSet<>();
        this.allergens = new HashSet<>();
        this.contaminated = new HashSet<>();
        this.allergenOfIngredient = new HashMap<>();

        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        for (String line : inputList) {
            List<String> contains = Arrays.asList(line.replaceAll(".*\\(contains |\\)", "").split(", "));
            List<String> menu = Arrays.asList(line.replaceAll("\\(.*", "").split(" "));
            ingredients.addAll(menu);
            allergens.addAll(contains);
            this.menus.put(menu, contains);
        }
    }

    @Override
    public String solve1() {
        for (String allergen : allergens) {
            Set<String> temp = new HashSet<>();
            for (List<String> menuItems : menus.keySet()) {
                if (menus.get(menuItems).contains(allergen)) {
                    if (temp.isEmpty()) {
                        temp.addAll(menuItems);
                    }
                    temp = temp.stream().filter(menuItems::contains).collect(Collectors.toSet());
                }
            }
            contaminated.addAll(temp);
        }

        int count = 0;
        for (String noAllergen : ingredients) {
            for (List<String> menuItems : menus.keySet()) {
                count += Collections.frequency(menuItems, noAllergen);
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String solve2() {
        for (String allergen : allergens) {
            Set<String> temp = new HashSet<>();
            for (List<String> menuItems : menus.keySet()) {
                if (menus.get(menuItems).contains(allergen)) {
                    if (temp.isEmpty()) {
                        temp.addAll(menuItems);
                    }
                    temp = temp.stream().filter(menuItems::contains).collect(Collectors.toSet());
                }
            }
            allergenOfIngredient.put(allergen, new ArrayList<>(temp));
        }

        while (contaminated.size() < allergenOfIngredient.size()) {
            for (List<String> items : allergenOfIngredient.values()) {
                String definite = items.get(0);
                if (items.size() == 1 && !contaminated.contains(definite)) {
                    contaminated.add(definite);
                    for (List<String> others : allergenOfIngredient.values()) {
                        if (items != others) {
                            others.remove(definite);
                        }
                    }
                }
            }
        }
        return orderByLowestKey(allergenOfIngredient).stream().map(e -> e.getValue().get(0)).collect(Collectors.joining(","));
    }

    public static List<Map.Entry<String, List<String>>> orderByLowestKey(HashMap<String, List<String>> hm) {
        List<Map.Entry<String, List<String>>> list =
                new LinkedList<>(hm.entrySet());

        list.sort(new Comparator<>() {
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });
        return list;
    }
}
