package util;

import java.io.*;
import java.net.URL;
import java.util.stream.Stream;

public class InputReaderUtil {

    public static String getFirstLine(int year, int day) {
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

    public static String getTestFirstLine(int year) {
        File testInputFile = openTestFile(year);
        String inputLine = "";
        try {
            InputStream is = new FileInputStream(testInputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            inputLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }

    public static Stream<String> getTestInput(int year) {
        File testInputFile = openTestFile(year);
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

    public static String getInputAsString(int year, int day) {
        return getStringFromFile(getInputFile(year, day));
    }

    public static String getTestInputAsString(int year) {
        return getStringFromFile(openTestFile(year));
    }

    private static String getStringFromFile(File testInputFile) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream is = new FileInputStream(testInputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str=br.readLine())!=null){
                builder.append(str).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static File openTestFile(int year) {
        String filepath = year + "/test.txt";
        File testInputFile;
        URL input = InputReaderUtil.class.getClassLoader().getResource(filepath);
        if (input == null) {
            throw new IllegalArgumentException(filepath + " not found!!");
        } else {
            testInputFile =  new File(input.getFile());
        }
        return testInputFile;
    }
}
