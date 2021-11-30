package aoc20.days;

import util.AoCDay;

import java.util.*;

public class Day23 extends AoCDay {

    private static final int MAX1 = 9;
    private static final int MAX2 = 1000000;
    private static final int MOVE = 10000000;

    private long startTime;
    private long endTime;
    private double dur;

    private final String input;
    private LinkedList<Integer> cups;
    private LinkedList<Integer> cups2;
    private List<Integer> cupList;
    private Deque<Integer> temp;
    private Deque<Integer> pickup;
    private HashMap<Integer, Integer> cupNumber;
    private HashMap<Integer, Integer> cupIndex;
    private Hashtable<Integer, Integer> tempMap;
    private int[] cupArray;


    public Day23(int day) {
        super(day, false, 2020);
        this.input = super.getInput();
        this.cups = new LinkedList<>();
        this.cups2 = new LinkedList<>();
        this.pickup = new LinkedList<>();
        this.cupList = new ArrayList<>();
        this.temp = new ArrayDeque<>();
        this.cupNumber = new HashMap<>();
        this.cupIndex = new HashMap<>();
        this.tempMap = new Hashtable<>();
        this.cupArray = new int[MAX2];
        this.startTime = 0L;
        this.endTime = 0L;
        this.dur = 0;
        handleInput(0);

    }

    @Override
    public void handleInput(int part) {
        String[] line = input.split("");
        int j;
        int len = line.length-1;
        for (j = 0; j < len; j++) {
            String number1 = line[j];
            String number2 = line[j+1];
            int n1 = Integer.parseInt(number1);
            int n2 = Integer.parseInt(number2);
            this.cups.add(n1);
            this.cupList.add(n1);
            this.cups2.add(n1);
            this.cupIndex.put(n1, j);
            this.cupNumber.put(j, n1);
            this.cupArray[n1] = n2;
        }

        //for (int i = j + 1; i <= MAX2; i++, j++) {
        for (int i = j + 1; i <= MAX2; i++, j++) {
            cupList.add(i);
            cups2.addLast(i);
            //cupIndex.put(i, j);
            //cupNumber.put(j,i);
        }
        //System.out.println("cupList = " + cupList);
        System.out.println("cupArray = " + Collections.singletonList(cupArray));

        //tempMap.
    }

    @Override
    public String solve1() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            moveCups(this.cups);
            //foo(i, this.cupList, this.temp);

        }
        long endTime = System.currentTimeMillis();
        double dur = (endTime - startTime) / 1000.0;
        System.out.println("dur = " + dur);

        StringBuilder sb = new StringBuilder();
        int index1 = cups.indexOf(1);
        for (int i = 1; i < MAX1; i++) {
            sb.append(cups.get(((i + index1 + MAX1) % MAX1)));
        }
        return String.valueOf(sb);
    }

    @Override
    public String solve2() {
        LinkedList<Integer> cups = this.cups2;
        //List<Integer> cups = this.cupList;
        //LinkedList<Integer> cups = new LinkedList<>(this.cups2.subList(0, 100000));
        //cups.addAll(this.cups2.subList(999000, cups2.size()));

        int size = cups.size();

        long startTime = System.currentTimeMillis();
        int index = 0;
        for (int i = 0; i < 100000; i++) {
            //moveCups3(i, cups);
            // moveCups2(cups); // 9.5
            index += foo(index, cups, this.temp); // 15
        }
        long endTime = System.currentTimeMillis();
        double dur = (endTime - startTime) / 1000.0;
        System.out.println("dur = " + dur);
        //System.out.println("cups = " + cups);

        int index1 = cups.lastIndexOf(1);
        long n1 = cups.get(((1 + index1 + size) % size));
        long n2 = cups.get(((2 + index1 + size) % size));


        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX1; i++) {
            sb.append(cups.get(i));
        }
        System.out.println("sb = " + sb);

        return String.valueOf(n1 * n2);
    }

    private void moveCups3(int turn, LinkedList<Integer> cups) {

        int curr = cups.pop();
        int dest = (curr == 0) ? MAX2 - 1 : curr - 1;
        for (int i = 0; i < 3; i++) {
            int p = cups.pop();
            this.pickup.add(i);
            dest = (p == dest) ? dest - 1 : dest;
        }


        startTime = System.currentTimeMillis(); // 13 millis

        //int i = findI(dest, cups);
        int i = (turn % 2 != 0) ? cups.lastIndexOf(dest) : cups.indexOf(dest);
        // 9.5
        //int i = cups.indexOf(dest)+1; // 21.883
        //int i = getIndexOfNumberFromList(dest, cups); // 12.204
        endTime = System.currentTimeMillis();
        dur = (endTime - startTime);
        System.out.println("turn = " + turn);
        System.out.println("dur = " + dur); // dur = 13 milis


        // startTime = System.currentTimeMillis();
        cups.add(i + 1, pickup.pop());
        cups.add(i + 2, pickup.pop());
        cups.add(i + 3, pickup.pop());
        //cups.addAll(i + 1, temp);
        //cups.addAll(cups.indexOf(dest)+1, pickup);
        /*endTime = System.currentTimeMillis();
        dur = (endTime - startTime);
        System.out.println("dur = " + dur);*/ // dur = 0


        //startTime = System.currentTimeMillis();

        //this.pickup.clear();
        cups.addLast(curr);
        /*endTime = System.currentTimeMillis();
        dur = (endTime - startTime);
        System.out.println("dur = " + dur);*/ // dur =
    }


    private void moveCups(LinkedList<Integer> cups) {

        int curr = cups.pop();
        for (int i = 0; i < 3; i++) {
            this.pickup.add(cups.pop());
        }
        int dest = curr - 1;
        if (!cups.contains(dest)) {
            for (int i = 2; i < MAX1; i++) {
                dest = ((curr - i + MAX1) % MAX1) + 1;
                if (cups.contains(dest)) {
                    break;
                }
            }
        }

        cups.addAll(cups.indexOf(dest) + 1, pickup);

        this.pickup.clear();
        cups.addLast(curr);

    }

    private void moveCups2(LinkedList<Integer> cups) {
        int curr = cups.pop();
        for (int i = 0; i < 3; i++) {
            this.pickup.add(cups.pop());
        }
        int dest = curr - 1;
        if (!cups.contains(dest)) {
            for (int i = 2; i < MAX1; i++) {
                dest = ((curr - i + MAX1) % MAX1) + 1;
                if (cups.contains(dest)) {
                    break;
                }
            }
        }
        //int i = findI(dest, cups);
        int i = cups.lastIndexOf(dest); // 9.5
        //int i = cups.indexOf(dest)+1; // 21.883
        //int i = getIndexOfNumberFromList(dest, cups); // 12.204


        cups.addAll(i + 1, pickup);
        //cups.addAll(cups.indexOf(dest)+1, pickup);
        this.pickup.clear();
        cups.addLast(curr);
    }

    private int findI(int number, List<Integer> list) {
        return list.indexOf(number);
    }

    private int foo(int currIndex, List<Integer> cups, Deque<Integer> pickup) {
        int max = cups.size();
        int index = currIndex % max;
        int curr = cups.get(index % max);

        startTime = System.currentTimeMillis();

        int dest = (curr == 1) ? max : curr - 1;
        for (int i = 1; i <= 3; i++) {
            int p = cups.get((index + i) % max);
            pickup.add(p);
            dest = (p == dest) ? dest - 1 : dest;
        }

        int remIndex = index+1 % cups.size();
        //cups.remove(remIndex);
        //cups.remove(remIndex);
        for (int i = 0; i < 3; i++) {  // TODO figure out closing circle
            if (index == max - 1) {
                cups.remove(0);
            } else{
                cups.remove(remIndex % cups.size());
            }
        }

        /*
        if (index == max - 1) {
            cups.remove((index + 1) % max);
            cups.remove((index + 1) % max); // over edge deletion
            cups.remove((index + 1) % max);
        } else {
            cups.remove((index + 1) % cups.size());
            cups.remove((index + 1) % cups.size());
            cups.remove((index + 1) % cups.size());
        }

         */


        //cups.removeAll(pickup);


        //int i = findIndex(cups, dest);
        int i = (index < max / 2) ? cups.indexOf(dest) : cups.lastIndexOf(dest); // ???
        //int i = cups.lastIndexOf(dest); // 24.035


        cups.addAll(i + 1, pickup);
        pickup.clear();
        endTime = System.currentTimeMillis();
        dur = (endTime - startTime);
        //System.out.println("dur = " + dur);

        return (i < index) ? 4 : 1;
    }

    private void bar(int currIndex, HashMap<Integer, Integer> cups, List<Integer> pickup) {
        System.out.println("cupNumber = " + cupNumber);
        int curr = cupNumber.get(currIndex);
        System.out.println("curr = " + curr);


        for (int i = currIndex + 1; i < currIndex + 4; i++) {
            pickup.add(cupNumber.get(i));
            cupNumber.remove(i);
        }
        System.out.println("tempList = " + pickup);

        int dest = curr - 1;
        if (!cupNumber.containsValue(dest)) {  // values.contains
            for (int i = 2; i < MAX2; i++) {
                dest = ((curr - i + MAX2) % MAX2) + 1;
                if (cupNumber.containsValue(dest)) {
                    break;
                }
            }
        }
        System.out.println("dest = " + dest);


        //int destIndex = cupIndex.get(dest-1);
        int destIndex = cupNumber.get(dest - 1);

        System.out.println("destIndex = " + destIndex);

        pickup.clear();
    }


    private int findIndex(List<Integer> cups, int number, List<Integer> sub) {
        int size = cups.size();
        sub = cups.subList(0, size / 4);
        if (sub.contains(number)) {
            sub.clear();
            return sub.indexOf(number);
        }
        sub = cups.subList(size / 4, size / 2);
        if (sub.contains(number)) {
            return sub.indexOf(number) + (size / 4);
        }
        sub = cups.subList(size / 2, size * 3 / 4);
        if (sub.contains(number)) {
            return sub.indexOf(number) + (size / 2);
        }
        sub = cups.subList(size * 3 / 4, size);
        if (sub.contains(number)) {
            return sub.indexOf(number) + (size / 3 * 4);
        }
        return -1;
    }

    /*
    private int getIndexOfNumberFromList(int number, LinkedList<Integer> cups) {
        int s4 = cups.size();
        int s3 = s4 * 3 / 4;
        int s2 = s4 / 2;
        int s1 = s4 / 4;
        temp = cups.subList(s3, s4);
        if (temp.contains(number)) {
            return temp.lastIndexOf(number) + s3;
        }
        temp = cups.subList(s2, s3);
        if (temp.contains(number)) {
            return temp.lastIndexOf(number) + s2;
        }
        temp = cups.subList(s1, s2);
        if (temp.contains(number)) {
            return temp.lastIndexOf(number) + s1;
        }
        temp = cups.subList(0, s1);
        if (temp.contains(number)) {
            return temp.lastIndexOf(number);
        }
        System.out.println("oh no");
        return -1;
    }

     */


}
