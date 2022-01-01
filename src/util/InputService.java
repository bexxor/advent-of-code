package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class InputService {
	private static final String AOC_URL = "https://adventofcode.com/";
	private static final String SESSION = System.getenv("AOC_SESSION");
	private static final String PATH = "src/resources/";
	private static int day = LocalDate.now().getDayOfMonth();
	private static int year = LocalDate.now().getYear();

	private static void createInput(String year, String day) {
		String dirName = PATH + year;
		String fileName = day.length() < 2 ? "/input0" + day + ".txt" : "/input" + day + ".txt";

		try {
			URL url = new URL(AOC_URL + year + "/day/" + day + "/input");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", "session=" + SESSION);
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			//String cookiesHeader = con.getHeaderField("Set-Cookie");
			//List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			File dir = new File(dirName);
			dir.mkdir();
			Path filePath = Paths.get(dirName, fileName);
			Files.createFile(filePath);
			BufferedWriter out = new BufferedWriter(new FileWriter(dirName + fileName));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				out.write(inputLine);
				out.write("\n");
			}
			in.close();
			out.close();
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String dd;
		String yyyy;
		if (args.length == 2) {
			dd = args[0];
			yyyy = args[1];
		} else {
			dd =  day < 10 ? "0" + day :String.valueOf(day);
			yyyy = String.valueOf(year);
		}

		createInput(yyyy, dd);
		//createInput(String.valueOf(2020), String.valueOf(1));
	}
}
