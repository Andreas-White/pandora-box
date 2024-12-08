package aoc2023.day2;

import aoc2023.Utils;

import java.io.IOException;
import java.util.*;

public class Day2 {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day2/input.txt";
        System.out.println(getSumOfGameIds(filePath));
        System.out.println(getSumOfGamesPower(filePath));
    }

    private static int getSumOfGameIds(String filePath) throws IOException {
        List<String> lines = Utils.readFile(filePath);

        int sum = 0;
        for (Game game: getAllGamesWithMaxValues(lines)) {
            if (game.blues <= 14 && game.greens <= 13 && game.reds <= 12)
                sum += game.id;
        }

        return sum;
    }

    private static int getSumOfGamesPower(String filePath) throws IOException {
        List<String> lines = Utils.readFile(filePath);
        List<Game> games = getAllGamesWithMaxValues(lines);
        int sum = 0;

        for (Game game : games) {
            sum += (game.blues * game.greens * game.reds);
        }
        return sum;
    }

    private static List<Game> getAllGamesWithMaxValues(List<String> lines) {
        List<Game> games = new ArrayList<>();
        lines.forEach(line -> {
            String[] dividedLine = line.split(":");
            String lineForGameId = dividedLine[0];
            String lineForSets = dividedLine[1];
            int gameId = getGameId(lineForGameId);
            int maxBlue = getMaxPerColor(lineForSets, "blue");
            int maxGreen = getMaxPerColor(lineForSets, "green");
            int maxRed = getMaxPerColor(lineForSets, "red");
            Game game = new Game(gameId, maxBlue, maxGreen, maxRed);
            games.add(game);
        });
        return games;
    }

    private static int getMaxPerColor(String subString, String color) {
        List<String> sets = Arrays.asList(subString.split(";"));
        List<Integer> numOfEachColor = new ArrayList<>();
        sets.forEach(set -> {
            List<String> cubes = Arrays.asList(set.split(","));
            cubes.forEach(cube -> {
                if (cube.contains(color)) {
                    numOfEachColor.add(Integer.parseInt(cube.substring(1, 3).trim()));
                }
            });
        });

        Collections.sort(numOfEachColor);
        return numOfEachColor.getLast();
    }

    private static int getGameId(String subString) {
        return Integer.parseInt(subString.substring(5));
    }

    private record Game(int id, int blues, int greens, int reds){}
}
