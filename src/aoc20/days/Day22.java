package aoc20.days;

import util.AoCDay;

import java.util.*;

public class Day22 extends AoCDay {

    private final List<String> inputList;
    private final Deque<Integer> deck1;
    private final Deque<Integer> deck2;
    private final Set<Deque<Integer>> history1;
    private final Set<Deque<Integer>> history2;

    public Day22(int day) {
        super(day, 2020, 1);
        this.inputList = super.getInputList();
        this.deck1 = new LinkedList<>();
        this.deck2 = new LinkedList<>();
        this.history1 = new HashSet<>();
        this.history2 = new HashSet<>();
    }

    @Override
    public void handleInput() {
        int size = inputList.size();
        int i;
        for (i = 1; i < size; i++) {
            String line = inputList.get(i);
            if (line.equals("")) {
                i += 2;
                break;
            }
            deck1.add(Integer.parseInt(line));
        }
        for (int j = i; j < size; j++) {
            String line = inputList.get(j);
            deck2.add(Integer.parseInt(line));
        }
    }

    @Override
    public String solve1() {
        combat();
        return String.valueOf(calculateScore(deck1, deck2));
    }

    @Override
    public String solve2() {
        recursiveCombat(deck1, deck2);
        return String.valueOf(calculateScore(deck1, deck2));
    }

    private void combat(){
        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            int card1 = deck1.pop();
            int card2 = deck2.pop();
            if (card1 > card2) {
                deck1.add(card1);
                deck1.add(card2);
            } else {
                deck2.add(card2);
                deck2.add(card1);
            }
        }
    }

    private int calculateScore(Deque<Integer> deck1, Deque<Integer> deck2) {
        int score = 0;
        int amount = deck1.size() + deck2.size();
        if (deck1.isEmpty()) {
            for (int i = amount; i > 0; i--) {
                score += deck2.pop() * i;
            }
        } else {
            for (int i = amount; i > 0; i--) {
                score += deck1.pop() * i;
            }
        }
        return score;
    }

    private boolean recursiveCombat(Deque<Integer> deck1, Deque<Integer> deck2) {
        boolean player1Wins = true;
        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            int card1;
            int card2;
            if (history1.contains(deck1) && history2.contains(deck2)) {
                return true;
            } else {
                history1.add(deck1);
                history2.add(deck2);
            }
            card1 = deck1.pop();
            card2 = deck2.pop();
            player1Wins = card1 > card2;
            if (card1 <= deck1.size() && card2 <= deck2.size()) {
                player1Wins = recursiveCombat(subDeck(card1, deck1), subDeck(card2, deck2));
            }
            if (player1Wins) {
                deck1.add(card1);
                deck1.add(card2);
            } else {
                deck2.add(card2);
                deck2.add(card1);
            }
        }

        return player1Wins;
    }

    private Deque<Integer> subDeck(int i, Deque<Integer> deck) {
        List<Integer> integers = List.copyOf(deck);
        return new LinkedList<>(integers.subList(0, i));
    }
}
