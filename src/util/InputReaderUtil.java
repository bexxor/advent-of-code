package util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReaderUtil {

    public static final String SESSION = System.getenv("AOC_SESSION");

    public static String getContentFromHtmlPage(String page) {
        StringBuilder sb = new StringBuilder();
        try {
            URLConnection connection = new URL(page).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append(":");
            }
            in.close();
        } catch (IOException e) {
            // handle exception
        }
        return sb.toString();
    }

    public static String inputAsStringLine(int year, int day) {
        String input = "";
        try {
            InputStream is = new FileInputStream(getInputFile(year, day));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static Stream<String> getTestInput(int year) {
        String filepath = year + "/testinput.txt";
        File testInputFile;
        URL input = InputReaderUtil.class.getClassLoader().getResource(filepath);
        if (input == null) {
            throw new IllegalArgumentException(filepath + " not found!!");
        } else {
            testInputFile =  new File(input.getFile());
        }
        try {
            InputStream is = new FileInputStream(testInputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            return br.lines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.<String>builder().build();
    }


    public static Stream<String> inputAsStreamOfLines(int year, int day) {
        try {
            InputStream is = new FileInputStream(getInputFile(year, day));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            return br.lines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.<String>builder().build();
    }


    public static File getInputFile(int year, int day) {
        String d = day<10? "0"+day:String.valueOf(day);
        String filepath = year + "/input" + d + ".txt";
        URL input = InputReaderUtil.class.getClassLoader().getResource(filepath);
        if (input == null) {
            throw new IllegalArgumentException(filepath + " not found!!");
        } else {
            return new File(input.getFile());
        }
    }

    public static String fileToString(File file) {
        String data = "";
        try {
            data = Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String readFileAsString(String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
