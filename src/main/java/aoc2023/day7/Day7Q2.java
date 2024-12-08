package aoc2023.day7;

import aoc2023.Utils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Day7Q2 {

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
                    .replaceAll("T", "V")
                    .replaceAll("J", "0");
            String[] splitLine = line.split(" ");
            String cards = splitLine[0].trim();
            String bid = splitLine[1].trim();

            Hand hand = new Hand(cards, Integer.parseInt(bid), checkForZerosAndDetermineType(cards));
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

    private static int checkForZerosAndDetermineType(String hand) {
        int type = determineType(hand);
        int zeros = getZeroOccurrences(hand);

        if (zeros == 1) {
            if (type == 1) {
                return 2;
            } else if (type == 2) {
                return 4;
            } else if (type == 3) {
                return 5;
            } else if (type == 4) {
                return 6;
            } else if (type == 6) {
                return 7;
            }
        } else if (zeros == 2) {
            if (type == 2) {
                return 4;
            } else if (type == 3) {
                return 6;
            } else if (type == 5) {
                return 7;
            }
        } else if (zeros == 3) {
            if (type == 4) {
                return 6;
            } else if (type == 5) {
                return 7;
            }
        } else if (zeros == 4) {
            return 7;
        }

        return type;
    }

    private static int getZeroOccurrences(String hand) {
        return Stream.of(hand.toCharArray())
                .map(chars -> {
                    int count = 0;
                    for (char c : chars) {
                        if (c == '0') {
                            count++;
                        }
                    }
                    return count;
                }).findFirst().orElse(0);
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
