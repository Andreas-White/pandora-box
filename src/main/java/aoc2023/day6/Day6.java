package aoc2023.day6;

import aoc2023.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Day6 {

    private static final Map<Long, Long> TIME_TO_DISTANCE = new TreeMap<>();
    private static final List<Long> winnings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day6/input.txt";
        List<String> lines = Utils.readFile(filePath);

        lines.forEach(System.out::println);
        calculateWinnings(lines);

        System.out.println(winnings.stream().reduce(1L, (a, b) -> a * b));
    }

    private static void populateMapTimeToDistance(List<String> lines) {
        List<List<Long>> timeToDistance= lines.stream()
                .map(str -> {
                    List<String> newList = Stream.of(str.split(":")[1].split(" "))
                            .filter(s -> !s.isEmpty() || !s.isBlank()).toList();
                    return newList.stream().map(Long::parseLong).toList();
                })
                .toList();

        List<Long> firstList = timeToDistance.getFirst();
        List<Long> secondList = timeToDistance.getLast();
        for (int i = 0; i < firstList.size(); i++) {
            if (firstList.size() != secondList.size()) break;
            TIME_TO_DISTANCE.put(firstList.get(i), secondList.get(i));
        }
    }

    private static void populateMapTimeToDistance2(List<String> lines) {
        List<String> nums = lines.stream()
                .map(str -> {
                    List<String> newList = Stream.of(str.split(":")[1].split(" "))
                            .filter(s -> !s.isEmpty() || !s.isBlank()).toList();
                    return newList.stream().reduce("", String::concat);
                })
                .toList();
        TIME_TO_DISTANCE.put(Long.parseLong(nums.getFirst()), Long.parseLong(nums.getLast()));
    }

    private static void calculateWinnings(List<String> lines) {
        populateMapTimeToDistance2(lines);
        TIME_TO_DISTANCE.forEach((time, distance) -> {
            Long counter = 0L;
            for (int i = 1; i <= time; i++) {
                if ((time - i) * i > distance) counter++;
            }
            winnings.add(counter);
        });
    }
}
