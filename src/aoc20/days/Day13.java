package aoc20.days;

import util.AoCDay;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 extends AoCDay {

    private final List<String> inputList;
    private final long departure;
    private List<Long> busList;
    private final List<Integer> indices;

    public Day13(int day) {
        super(day, true, 2020, 1);
        this.inputList = Arrays.asList(super.getInputList().get(1).split(",").clone());
        this.departure = Integer.parseInt(super.getInputList().get(0));
        this.busList = new ArrayList<>();
        this.indices = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        this.busList = this.inputList.stream().filter(bus -> !bus.equals("x")).map(Long::parseLong).collect(Collectors.toList());
        inputList.stream().filter(bus -> !bus.equals("x"))
                .forEach(bus -> indices.add((Integer.parseInt(bus) * 10 - inputList.indexOf(bus)) % Integer.parseInt(bus)));
    }

    @Override
    public String solve1() {
        Collections.sort(busList);
        int i = 0;
        while (true) {
            for (long bus : busList) {
                if (i % bus == 0 && i > departure) {
                    return String.valueOf((i - departure) * bus);
                }
            }
            i++;
        }
    }

    @Override
    public String solve2() {
        return String.valueOf(chineseRemainderTheorem());
    }

    private long chineseRemainderTheorem() {
        int len = busList.size();
        long product = busList.stream().reduce(1L, (b1, b2) -> b1 * b2);
        List<Long> dividedProduct = busList.stream().map(bus -> product/bus).collect(Collectors.toList());
        List<Long> modInv = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            modInv.add(moduloInverse(dividedProduct.get(i), busList.get(i)));
        }

        long result = 0;
        for (int i = 0; i <len; i++) {
            result += (indices.get(i)*dividedProduct.get(i)*modInv.get(i));
        }

        return result%product;
    }

    private long moduloInverse(long a, long m){
        for (int i = 1; i < m; i++) {
            if ((i*a)%m == 1){
                return i;
            }
        }
        return 1;
    }
}
