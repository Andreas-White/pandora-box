package aoc2023.day7;

import aoc2023.Utils;

import java.io.IOException;
import java.util.*;

public class Day7 {

    private record Hand(String cards, int bid, int type) {}
    private static final List<Hand> hands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day7/input.txt";
        List<String> lines = Utils.readFile(filePath);
        populateUnsortedHand(lines);
        hands.sort(handComparator());
        hands.forEach(System.out::println);

        int sum = hands.stream()
                .mapToInt(hand -> hand.bid() * (hands.indexOf(hand) + 1))
                .reduce(0, Integer::sum);
        System.out.printf("The sum is: %d", sum);
    }

    private static void populateUnsortedHand(List<String> lines) {
        for (String line : lines) {
            line = line
                    .replaceAll("A", "Z")
                    .replaceAll("K", "Y")
                    .replaceAll("Q", "X")
                    .replaceAll("J", "W")
                    .replaceAll("T", "V");
            String[] splitLine = line.split(" ");
            String cards = splitLine[0].trim();
            String bid = splitLine[1].trim();

            Hand hand = new Hand(cards, Integer.parseInt(bid), determineType(cards));
            hands.add(hand);
        }
    }

    private static int determineType(String hand) {
        Set<Character> uniqueChars = new HashSet<>();
        char[] chars = hand.toCharArray();
        for (Character c : chars) {
            uniqueChars.add(c);
        }

        if (uniqueChars.size() == 1)
            return 7;
        else if (uniqueChars.size() == 4)
            return 2;
        else if (uniqueChars.size() == 5)
            return 1;
        else if (uniqueChars.size() == 2) {
            if (getHits(uniqueChars, chars) == 4)
                return 6;
            else
                return 5;
        }
        else {
            if (getHits(uniqueChars, chars) == 3)
                return 4;
            else
                return 3;
        }
    }

    private static int getHits(Set<Character> charSet, char[] charArray) {
        return charSet.stream()
                .map(character -> {
                    int count = 0;
                    for (Character c : charArray) {
                        if (c.equals(character))
                            count++;
                    }
                    return count;
                })
                .toList().stream()
                .max(Integer::compare).get();

    }

    private static Comparator<Hand> handComparator() {
        return Comparator
                .comparing(Hand::type)
                .thenComparing(Hand::cards);
    }
}
