package aoc2023.day1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1 {

    private static final Map<String, String> mappedIntegers = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four","4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9",
            "ten", "10"
    );

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day1/input.txt";
        System.out.println(readFileAndSumIntegers(filePath));
    }

    private static int readFileAndSumIntegers(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        List<Integer> nums = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            nums.add(getFirstAndLastDigit(line));
        }

        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        return sum;
    }

    private static int getFirstAndLastDigit(String line) {
        TreeMap<Integer, Character> integers = new TreeMap<>();
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                integers.put(i, chars[i]);
            }
        }
        String num1 = "";
        String num2 = "";
        String firstSubString = "";
        String lastSubString= "";

        if (!integers.isEmpty()) {
            firstSubString = line.substring(0, integers.firstKey());
            lastSubString =line.substring(integers.lastKey() + 1);
        }


        for (String str: mappedIntegers.keySet()) {
            if (firstSubString.contains(str) && !firstSubString.isEmpty()) {
                num1 = getNumFromSubString(firstSubString);
                break;
            } else if (line.contains(str) && integers.isEmpty()) {
                num1 = getNumFromSubString(line);
                break;
            }
        }

        for (String str: mappedIntegers.keySet()) {
            if (lastSubString.contains(str) && !lastSubString.isEmpty()) {
                num2 = getNumFromSubStringReversed(lastSubString);
                break;
            } else if (line.contains(str) && integers.isEmpty()) {
                num2 = getNumFromSubStringReversed(line);
                break;
            }
        }

        if (!integers.isEmpty() && Objects.equals(num1, ""))
            num1= integers.firstEntry().getValue().toString();
        if (!integers.isEmpty() && Objects.equals(num2, ""))
            num2 = integers.lastEntry().getValue().toString();

        String result = num1 + num2;
        System.out.println(result);
        return Integer.parseInt(result);
    }

    private static String getNumFromSubString(String subString) {
        for (int i = 3; i <= subString.length(); i++) {
            for (String s: mappedIntegers.keySet()) {
                if (subString.substring(0, i).contains(s)) {
                    return mappedIntegers.get(s);
                }
            }
        }
        return "";
    }

    private static String getNumFromSubStringReversed(String subString) {
        String reversedSubstring = reverse(subString);
        for (int i = 3; i <= subString.length(); i++) {
            for (String s: mappedIntegers.keySet()) {
                String reversedNumber = reverse(s);
                if (reversedSubstring.substring(0, i).contains(reversedNumber)) {
                    return mappedIntegers.get(s);
                }
            }
        }
        return "";
    }

    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
}
