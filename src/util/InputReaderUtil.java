package util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputReaderUtil {

    public static String inputAsStringLine(int year, String day) {
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


    public static List<String> inputAsListOfLines(int year, String day) {
        List<String> inputLines = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(getInputFile(year, day));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            inputLines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLines;
    }


    public static File getInputFile(int year, String day) {
        String filepath = year + "/input" + day + ".txt";
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
