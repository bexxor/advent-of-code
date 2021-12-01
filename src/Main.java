import util.AoCDay;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class Main {

	public static int day = LocalDate.now().getDayOfMonth();
	public static int year = LocalDate.now().getYear();

	public static void main(String[] args) {
		String dd;
		String yy;
		if (args.length == 2) {
			dd = args[0];
			yy = args[1];
		} else {
			dd = day < 10 ? "0" + day : String.valueOf(day);
			yy = String.valueOf(year % 100);
		}
		try {
			String className = "aoc" + yy + ".Day" + dd;
			AoCDay aoCDay = (AoCDay) Class.forName(className).getDeclaredConstructor(int.class).newInstance(day);
			System.out.println(aoCDay.solve());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
