package aoc20.days;

import util.AoCDay;

import java.util.*;

public class Day07 extends AoCDay {

    private final List<String> inputList;
    private final Set<String> bags;
    private final HashMap<String, List<String>> bagRules;
    private final HashMap<String, HashMap<String, Integer>> bagAmount;

    public Day07(int day) {
        super(day, true, 2020, 1);
        this.inputList = super.getInputList();
        this.bags = new HashSet<>();
        this.bagRules = new HashMap<>();
        this.bagAmount = new HashMap<>();
    }

    @Override
    public void handleInput(int part) {
        if (part == 1) {
            for (String line : inputList) {
                String bag = line.replaceAll(" bags? contain.*$", "");
                List<String> content = Arrays.asList(line.replaceAll(" \\d| bags?\\.?", "").replaceAll("^.*contain ", "").split(", "));
                bagRules.put(bag, content.get(0).equals("no other") ? new ArrayList<>() : content);
                bags.add(bag);
            }
        } else if (part == 2) {
            for (String line : inputList) {
                String bag = line.replaceAll(" bags? contain.*$", "");
                List<String> contentBags = Arrays.asList(line.replaceAll(" bags?\\.?", "").replaceAll("^.*contain ", "").split(", "));
                HashMap<String, Integer> content = new HashMap<>();
                if (!contentBags.get(0).equals("no other")) {
                    for (String contentBag : contentBags) {
                        int amount = Integer.parseInt(contentBag.replaceAll("\\s+|[a-zA-Z]+", ""));
                        String insideBag = contentBag.replaceAll("\\d+\\s+", "");
                        content.put(insideBag, amount);
                    }
                }
                bagAmount.put(bag, content);
            }
        }
    }

    @Override
    public String solve1() {
        handleInput(1);
        int count = 0;
        for (String bag : bags) {
            if (bagContainsShinyGold(bag)) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String solve2() {
        handleInput(2);
        return String.valueOf(amountInsideBag("shiny gold"));
    }

    private int amountInsideBag(String bag) {
        int count = 0;
        Map<String, Integer> bagsInside = bagAmount.get(bag);
        if (bagsInside.isEmpty()) {
            return 0;
        }
        for (Map.Entry<String, Integer> entry : bagsInside.entrySet()) {
            String insideBag = entry.getKey();
            Integer amount = entry.getValue();
            count += amount + amount * amountInsideBag(insideBag);
        }
        return count;
    }


    private boolean bagContainsShinyGold(String bag) {
        List<String> rules = bagRules.get(bag);
        if (rules.isEmpty()) {
            return false;
        } else if (rules.contains("shiny gold")) {
            return true;
        } else {
            return bagsContainShinyGold(rules);
        }
    }

    private boolean bagsContainShinyGold(List<String> bags) {
        boolean contains = false;
        for (String bag : bags) {
            contains = contains || bagContainsShinyGold(bag);
        }
        return contains;
    }
}
