package aoc20.days;

import util.AoCDay;

import java.util.*;

public class Day04 extends AoCDay {

    private final List<String> inputList;
    private final List<Map<String, String>> passports;

    public Day04(int day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.passports = new ArrayList<>();
        handleInput(0);
    }

    @Override
    public void handleInput(int part) {
        Map<String, String> passport = new HashMap<>();
        for (String s : inputList) {
            if (s.equals("")) {
                passports.add(passport);
                passport = new HashMap<>();
            } else {
                String[] entries = s.split(" ");
                for (String entry : entries) {
                    String[] pair = entry.split(":");
                    passport.put(pair[0], pair[1]);
                }
            }
        }
    }

    @Override
    public String solve1() {
        int count = 0;
        for (Map<String, String> passport : passports) {
            if (validPassport(passport)) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String solve2() {
        int count = 0;
        for (Map<String, String> passport : passports) {
            if (validPassport(passport)) {
                if (validBYR(passport.get("byr")) &&
                        validIYR(passport.get("iyr")) &&
                        validEYR(passport.get("eyr")) &&
                        validHGT(passport.get("hgt")) &&
                        validHCL(passport.get("hcl")) &&
                        validECL(passport.get("ecl")) &&
                        validPID(passport.get("pid"))) {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    private boolean validPassport(Map<String, String> passport) {
        return passport.size() >= 7 && (!passport.containsKey("cid") || passport.size() == 8);
    }


    private boolean validBYR(String s) {
        int byr = Integer.parseInt(s);
        return byr >= 1920 && byr <= 2002;
    }

    private boolean validIYR(String s) {
        int iyr = Integer.parseInt(s);
        return iyr >= 2010 && iyr <= 2020;
    }

    private boolean validEYR(String s) {
        int eyr = Integer.parseInt(s);
        return eyr >= 2020 && eyr <= 2030;
    }

    private boolean validHGT(String s) {
        if (s.endsWith("cm")) {
            int hgt = Integer.parseInt(s.replaceAll("cm", ""));
            return hgt >= 150 && hgt <= 193;
        } else if (s.endsWith("in")) {
            int hgt = Integer.parseInt(s.replaceAll("in", ""));
            return hgt >= 59 && hgt <= 76;
        }
        return false;
    }

    private boolean validHCL(String s) {
        return s.length() == 7 && s.matches("#[0-9a-fA-F]+");
    }

    private boolean validECL(String s) {
        List<String> validColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return validColors.contains(s);
    }

    private boolean validPID(String s) {
        return s.length() == 9 && s.matches("[0-9]+");
    }
}
