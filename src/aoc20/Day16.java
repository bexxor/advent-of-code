package aoc20;

import util.AoCDay;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends AoCDay {

    private final List<String> inputList;
    private List<Integer> myTicket;
    private final List<String> names;
    private final List<List<Integer>> nearbyTickets;
    private final List<List<Integer>> invalidTickets;
    private final HashMap<Integer, List<String>> validNumbers;
    private final HashMap<Integer, List<String>> result;
    private final HashMap<Integer, String> rules;

    public Day16(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.nearbyTickets = new ArrayList<>();
        this.invalidTickets = new ArrayList<>();
        this.validNumbers = new HashMap<>();
        this.result = new HashMap<>();
        this.names = new ArrayList<>();
        this.rules = new HashMap<>();
        handleInput();
    }

    @Override
    public void handleInput() {
        int i;
        int j;
        for (i = 0; i < inputList.size(); i++) {
            String line = inputList.get(i);
            if (line.equals("")) {
                i += 2;
                this.myTicket = Arrays.stream(inputList.get(i).split(",")).map(Integer::parseInt).collect(Collectors.toList());
                i += 3;
                break;
            }
            String name = line.replaceAll(":.*", "");
            List<Integer> range = Arrays.stream(line.replaceAll(".*:\\s", "").replaceAll("\\D+", ",").split(",")).map(Integer::parseInt).collect(Collectors.toList());
            this.names.add(name);
            for (int k = range.get(0); k <= range.get(1); k++) {
                if (!validNumbers.containsKey(k)) {
                    validNumbers.put(k, new ArrayList<>());
                }
                validNumbers.get(k).add(name);
            }
            for (int k = range.get(2); k <= range.get(3); k++) {
                if (!validNumbers.containsKey(k)) {
                    validNumbers.put(k, new ArrayList<>());
                }
                validNumbers.get(k).add(name);
            }
        }
        for (j = i; j < inputList.size(); j++) {
            List<Integer> ticket = Arrays.stream(inputList.get(j).split(",")).map(Integer::parseInt).collect(Collectors.toList());
            nearbyTickets.add(ticket);
        }
    }

    @Override
    public String solve1() {
        return String.valueOf(ticketScanningErrorRate());
    }

    @Override
    public String solve2() {
        ticketScanningErrorRate();
        nearbyTickets.removeAll(invalidTickets);
        possibleNamesForTicket();

        long product = 1;
        for (Integer k : rules.keySet()) {
            if (rules.get(k).startsWith("departure")){
                product *=  myTicket.get(k);
            }
        }
        return String.valueOf(product);

    }

    private void possibleNamesForTicket(){
        int ticketsAmount = nearbyTickets.size();
        int ticketSize = myTicket.size();
        for (int j = 0; j < ticketSize; j++) {
            List<String> current = new ArrayList<>();
            for (int i = 0; i < ticketsAmount; i++) {
                int number = nearbyTickets.get(i).get(j);
                if (i == 0) {
                    current = validNumbers.get(number);
                    continue;
                }
                List<String> same = new ArrayList<>();
                for (String name : current) {
                    if (validNumbers.get(number).contains(name)) {
                        same.add(name);
                    }
                }
                current = same;
            }
            result.put(j, current);
        }
        while (rules.size() < names.size()) {
            result.forEach((k, v) -> {
                if (v.size() == 1) {
                    String currName = v.get(0);
                    rules.put(k, currName);
                    for (List<String> names : result.values()) {
                        names.remove(currName);
                    }
                }
            });
        }
    }

    private int ticketScanningErrorRate() {
        int sum = 0;
        for (List<Integer> ticket : nearbyTickets) {
            for (int number : ticket) {
                if (!validNumbers.containsKey(number)) {
                    invalidTickets.add(ticket);
                    sum += number;
                }
            }
        }
        return sum;
    }
}
