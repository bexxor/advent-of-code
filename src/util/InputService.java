package util;

import java.io.*;
import java.net.*;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class InputService {
	private static final String AOC_URL = "https://adventofcode.com/";
	private static final String SESSION = System.getenv("AOC_SESSION");
	private static final String PATH = "src/resources/";




	private static void createInput(String year, String day){
		String dirName = PATH + year;
		String fileName = day.length()<2?"/input0"+day+".txt":"/input"+day+".txt";

		try {
			URL url = new URL(AOC_URL+ year +"/day/"+ day +"/input");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", "session="+SESSION);
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			//String cookiesHeader = con.getHeaderField("Set-Cookie");
			//List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			File dir = new File(dirName);
			dir.mkdir();
			Path filePath = Paths.get(dirName,fileName);
			Files.createFile(filePath);
			BufferedWriter out = new BufferedWriter(new FileWriter(dirName +fileName));
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
		LocalDate today = LocalDate.now();
		createInput(String.valueOf(2019), String.valueOf(2));
		//createInput(String.valueOf(today.getYear()), String.valueOf(today.getDayOfMonth()));
	}
}
