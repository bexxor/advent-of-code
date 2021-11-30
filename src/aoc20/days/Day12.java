package aoc20.days;

import util.AoCDay;

import java.util.List;


public class Day12 extends AoCDay {

    private final List<String> inputList;
    private int x;
    private int y;
    private int dir; // 0=N, 1=E, 2=S, 3=W
    private int wX;
    private int wY;

    public Day12(String day) {
        super(day, true, 2020);
        this.inputList = super.getInputList();
        this.x = 0;
        this.y = 0;
        this.dir = 1;
        this.wX = x + 10;
        this.wY = y + 1;
    }

    @Override
    public void handleInput(int part) {
    }

    @Override
    public String solve1() {
        inputList.forEach(p -> move1(p.charAt(0), Integer.parseInt(p.substring(1))));
        return String.valueOf(Math.abs(x) + Math.abs(y));
    }

    @Override
    public String solve2() {
        inputList.forEach(p -> move2(p.charAt(0), Integer.parseInt(p.substring(1))));
        return String.valueOf(Math.abs(x) + Math.abs(y));
    }

    private void move2(char action, int value) {
        int angle = value / 90;
        switch (action) {
            case ('N'):
                wY += value;
                break;
            case ('E'):
                wX += value;
                break;
            case ('S'):
                wY -= value;
                break;
            case ('W'):
                wX -= value;
                break;
            case ('L'):
                int reverseAngle = angle == 1 ? 3 : angle == 3 ? 1 : angle;
                rotate(reverseAngle);
                break;
            case ('R'):
                rotate(angle);
                break;
            case ('F'):
                x += (value * wX);
                y += (value * wY);
                break;
            default:
                break;
        }
    }

    private void rotate(int angle) {
        int swap;
        switch (angle) {
            case 1:
                swap = wX;
                wX = wY;
                wY = -swap;
                break;
            case 2:
                wX = -wX;
                wY = -wY;
                break;
            case 3:
                swap = wX;
                wX = -wY;
                wY = swap;
                break;
        }
    }

    private void move1(char action, int value) {
        int angle = value / 90;
        switch (action) {
            case ('N'):
                y -= value;
                break;
            case ('E'):
                x += value;
                break;
            case ('S'):
                y += value;
                break;
            case ('W'):
                x -= value;
                break;
            case ('L'):
                dir = (4 + dir - angle) % 4;
                break;
            case ('R'):
                dir = (dir + angle) % 4;
                break;
            case ('F'):
                switch (dir) {
                    case (0):
                        move1('N', value);
                        break;
                    case (1):
                        move1('E', value);
                        break;
                    case (2):
                        move1('S', value);
                        break;
                    case (3):
                        move1('W', value);
                        break;
                }
                break;
        }
    }
}
