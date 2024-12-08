package aoc2023.day5;

import java.util.ArrayList;
import java.util.List;

public class Day5Utils {

    public static final List<Long> MAP_FROM_SEED_TO_SOIL = new ArrayList<>();
    public static final List<Long> MAP_FROM_SOIL_TO_FERTILIZER = new ArrayList<>();
    public static final List<Long> MAP_FROM_FERTILIZER_TO_WATER = new ArrayList<>();
    public static final List<Long> MAP_FROM_WATER_TO_LIGHT = new ArrayList<>();
    public static final List<Long> MAP_FROM_LIGHT_TO_TEMPERATURE = new ArrayList<>();
    public static final List<Long> MAP_FROM_TEMPERATURE_TO_HUMIDITY = new ArrayList<>();
    public static final List<Long> MAP_FROM_HUMIDITY_TO_LOCATION = new ArrayList<>();

    public static void populateMappings(List<String> lines) {
        populateIndividualMapping(lines, "seed-to-soil map:", MAP_FROM_SEED_TO_SOIL);
        populateIndividualMapping(lines, "soil-to-fertilizer map:", MAP_FROM_SOIL_TO_FERTILIZER);
        populateIndividualMapping(lines, "fertilizer-to-water map:", MAP_FROM_FERTILIZER_TO_WATER);
        populateIndividualMapping(lines, "water-to-light map:", MAP_FROM_WATER_TO_LIGHT);
        populateIndividualMapping(lines, "light-to-temperature map:", MAP_FROM_LIGHT_TO_TEMPERATURE);
        populateIndividualMapping(lines, "temperature-to-humidity map:", MAP_FROM_TEMPERATURE_TO_HUMIDITY);
        populateIndividualMapping(lines, "humidity-to-location map:", MAP_FROM_HUMIDITY_TO_LOCATION);
    }

    private static void populateIndividualMapping(List<String> lines, String mapName, List<Long> numMaps) {
        boolean isTheRightMap = false;
        for (long i = 0; i < lines.size(); i++) {
            if (lines.get((int) i).equals(mapName)) {
                isTheRightMap = true;
                i++;
            }

            boolean isLineEmpty = lines.get((int) i).isEmpty();

            if (isTheRightMap && !isLineEmpty) {
                String[] numbersStr = lines.get((int) i).split(" ");

                for(String numStr : List.of(numbersStr)) {
                    numMaps.add(Long.parseLong(numStr));
                }
            }

            if ((isLineEmpty && isTheRightMap)) break;

        }
    }
}
