package aoc21;

import java.util.List;
import java.util.Map;

public class Submarine {

	public int x = 0;
	public int y = 0;
	public int aim = 0;

	private static final Map<String, List<Integer>> DIR1 =Map.of("forward", List.of(+1,0), "down", List.of(0, +1),"up", List.of(0, -1));
	private static final Map<String, List<Integer>> DIR =Map.of("forward", List.of(+1,+1,0), "down", List.of(0, 0, +1),"up", List.of(0, 0, -1));


	public Submarine(){
	}

	public void move1(String dir, String amount){
		x += DIR1.get(dir).get(0)*Integer.parseInt(amount);
		y += DIR1.get(dir).get(1)*Integer.parseInt(amount);
	}
	public void move(String dir, String amount){
		List<Integer> curr = DIR.get(dir);
		aim += curr.get(2)*Integer.parseInt(amount);
		x += curr.get(0)*Integer.parseInt(amount);
		y += curr.get(1)*Integer.parseInt(amount)*aim;
	}


}
