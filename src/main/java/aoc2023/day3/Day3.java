package aoc2023.day3;

import aoc2023.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    private static List<SpecialCharacter> specialCharactersList = new ArrayList<>();
    private static List<Number> numberList = new ArrayList<>();
    private static List<Number> addableNumbers = new ArrayList<>();
    private static List<StarCharacter> stars = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day3/input.txt";
        List<String> lines = Utils.readFile(filePath);
        int sum = calculateAggregatedRatio(lines);
        stars.forEach(System.out::println);
        System.out.println("Sum: " + sum + " and stars size: " + stars.size());

//        addableNumbers.forEach(number -> System.out.printf("Number: %d, row number: %d, column number: %d, isAddable: %b%n", number.number, number.rowNumber, number.columnNumber, number.isAddable));
//        int sum = 0;
//        for (Number num : addableNumbers) {
//            sum += num.number;
//        }
//        System.out.println(sum);
    }

    private static int calculateAggregatedRatio(List<String> lines) {
        populateStars(lines);
        int sum = 0;
        for (StarCharacter st : stars) {
            int ratio = st.numbers.get(0) * st.numbers.get(1);
            sum += ratio;

        }

        return sum;
    }

    private static void populateStars(List<String> lines) {
        populateAddableNumbers(lines);
        for (SpecialCharacter sc : specialCharactersList) {
            if (sc.symbol == '*') {
                List<Integer> adjNumbers = new ArrayList<>();
                for (Number num : addableNumbers) {
                    int numLength = String.valueOf(num.number).length();
                    if ((sc.rowNumber == num.rowNumber &&
                            (sc.columnNumber - numLength == num.columnNumber || sc.columnNumber + 1 == num.columnNumber))
                    || ((sc.rowNumber - 1 == num.rowNumber || sc.rowNumber + 1 == num.rowNumber) &&
                            (sc.columnNumber - numLength <= num.columnNumber && sc.columnNumber + 1 >= num.columnNumber))) {
                        adjNumbers.add(num.number);
                    }
                }
                if (adjNumbers.size() >= 2) {
                    StarCharacter starCharacter = new StarCharacter(sc.rowNumber, sc.columnNumber, adjNumbers);
                    stars.add(starCharacter);
                }
            }
        }
    }

    private static void populateAddableNumbers(List<String> lines) {
       setAddableNumbers(lines);

        for (Number num : numberList) {
            if (num.isAddable) {
                addableNumbers.add(num);
            }
        }
    }

    private static void setAddableNumbers(List<String> lines) {
        populateSpecialCharacterList(lines);
        populateNumberList(lines);

        for (Number num : numberList) {
            int numLength = String.valueOf(num.number).length();
            for (SpecialCharacter spChar : specialCharactersList) {
                if ((num.rowNumber == spChar.rowNumber &&
                        (num.columnNumber -1 == spChar.columnNumber || num.columnNumber + numLength == spChar.columnNumber))) {
                    num.setAddable(true);
                }

                if (((num.rowNumber - 1 == spChar.rowNumber || num.rowNumber + 1 == spChar.rowNumber) &&
                        (num.columnNumber - 1 <= spChar.columnNumber && num.columnNumber + numLength >= spChar.columnNumber))) {
                    num.setAddable(true);
                }
            }
        }
    }

    private static void populateSpecialCharacterList(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            char[] chars = lines.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (!Character.isDigit(chars[j]) && chars[j] != '.') {
                    SpecialCharacter specialCharacter = new SpecialCharacter(chars[j], i, j);
                    specialCharactersList.add(specialCharacter);
                }
            }
        }
    }

    private static void populateNumberList(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            char[] chars = lines.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                StringBuilder numberStr = new StringBuilder();
                int columnNumber = j;
                while (Character.isDigit(chars[j])) {
                    numberStr.append(chars[j]);
                    j++;
                    if (j == 140) break;
                }

                if (!numberStr.isEmpty() && Character.isDigit(numberStr.charAt(0))) {
                    Number number = new Number(Integer.parseInt(numberStr.toString()), i, columnNumber, false);
                    numberList.add(number);
                }
            }
        }
    }

    private record SpecialCharacter(char symbol, int rowNumber, int columnNumber) {}
    private record StarCharacter(int row, int column, List<Integer> numbers) {}
    private static class Number {
        private int number;
        private int rowNumber;
        private int columnNumber;
        private boolean isAddable;

        public Number(int number, int rowNumber, int columnNumber, boolean isAddable) {
            this.number = number;
            this.rowNumber = rowNumber;
            this.columnNumber = columnNumber;
            this.isAddable = isAddable;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getRowNumber() {
            return rowNumber;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        public boolean isAddable() {
            return isAddable;
        }

        public void setAddable(boolean addable) {
            isAddable = addable;
        }
    }
}
