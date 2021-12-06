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
        String filepath = year + "/test.txt";
        File testInputFile;
        URL input = InputReaderUtil.class.getClassLoader().getResource(filepath);
        if (input == null) {
            throw new IllegalArgumentException(filepath + " not found!!");
        } else {
            testInputFile =  new File(input.getFile());
        }
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
        String filepath = year + "/test.txt";
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
}
