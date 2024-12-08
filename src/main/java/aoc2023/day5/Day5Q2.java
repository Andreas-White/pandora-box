package aoc2023.day5;

import aoc2023.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5Q2 {

    private static final List<Long> MAP_FROM_SEED_TO_SOIL = Day5Utils.MAP_FROM_SEED_TO_SOIL;
    private static final List<Long> MAP_FROM_SOIL_TO_FERTILIZER = Day5Utils.MAP_FROM_SOIL_TO_FERTILIZER;
    private static final List<Long> MAP_FROM_FERTILIZER_TO_WATER = Day5Utils.MAP_FROM_FERTILIZER_TO_WATER;
    private static final List<Long> MAP_FROM_WATER_TO_LIGHT = Day5Utils.MAP_FROM_WATER_TO_LIGHT;
    private static final List<Long> MAP_FROM_LIGHT_TO_TEMPERATURE = Day5Utils.MAP_FROM_LIGHT_TO_TEMPERATURE;
    private static final List<Long> MAP_FROM_TEMPERATURE_TO_HUMIDITY = Day5Utils.MAP_FROM_TEMPERATURE_TO_HUMIDITY;
    private static final List<Long> MAP_FROM_HUMIDITY_TO_LOCATION = Day5Utils.MAP_FROM_HUMIDITY_TO_LOCATION;

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day5/input.txt";
        List<String> lines = Utils.readFile(filePath);

        initializeMappingResults(lines);
    }

    private static void initializeMappingResults(List<String> lines) {
        Day5Utils.populateMappings(lines);
        String[] seeds = lines.getFirst().split(":")[1].split(" ");
        List<Long> ranges = new ArrayList<>();

        for (String str : seeds) {
            if (str.isEmpty()) continue;
            long seed = Long.parseLong(str);
            ranges.add(seed);
        }

        long min = Long.MAX_VALUE;

        for (long l = 0; l < ranges.size(); l = l +2) {
            long start = ranges.get((int) l);
            long end = ranges.get((int) l) + ranges.get((int) l + 1);
            long seed;
            long soil = 0;
            long fertilizer = 0;
            long water = 0;
            long light = 0;
            long temp = 0;
            long humidity = 0;
            long loc = 0;

            for (long j = start; j < end; j++) {
                seed = j;

                for (long i = 0; i < MAP_FROM_SEED_TO_SOIL.size(); i = i + 3) {
                    long dest = MAP_FROM_SEED_TO_SOIL.get((int) i);
                    long source = MAP_FROM_SEED_TO_SOIL.get((int) i + 1);
                    long range = MAP_FROM_SEED_TO_SOIL.get((int) i + 2);

                    if (seed >= source && seed < source + range) {
                        long diff = seed - source;
                        soil = dest + diff;
                        break;
                    }
                    else {
                        soil = seed;
                    }
                }

                for (long i = 0; i < MAP_FROM_SOIL_TO_FERTILIZER.size(); i = i + 3) {
                    long dest = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i);
                    long source = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i + 1);
                    long range = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i + 2);

                    if (soil >= source && soil < source + range) {
                        long diff = soil - source;
                        fertilizer = dest + diff;
                        break;
                    }
                    else {
                        fertilizer = soil;
                    }
                }

                for (long i = 0; i < MAP_FROM_FERTILIZER_TO_WATER.size(); i = i + 3) {
                    long dest = MAP_FROM_FERTILIZER_TO_WATER.get((int) i);
                    long source = MAP_FROM_FERTILIZER_TO_WATER.get((int) i + 1);
                    long range = MAP_FROM_FERTILIZER_TO_WATER.get((int) i + 2);

                    if (fertilizer >= source && fertilizer < source + range) {
                        long diff = fertilizer - source;
                        water = dest + diff;
                        break;
                    }
                    else {
                        water = fertilizer;
                    }
                }

                for (long i = 0; i < MAP_FROM_WATER_TO_LIGHT.size(); i = i + 3) {
                    long dest = MAP_FROM_WATER_TO_LIGHT.get((int) i);
                    long source = MAP_FROM_WATER_TO_LIGHT.get((int) i + 1);
                    long range = MAP_FROM_WATER_TO_LIGHT.get((int) i + 2);

                    if (water >= source && water < source + range) {
                        long diff = water - source;
                        light = dest + diff;
                        break;
                    }
                    else {
                        light = water;
                    }
                }

                for (long i = 0; i < MAP_FROM_LIGHT_TO_TEMPERATURE.size(); i = i + 3) {
                    long dest = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i);
                    long source = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i + 1);
                    long range = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i + 2);

                    if (light >= source && light < source + range) {
                        long diff = light - source;
                        temp = dest + diff;
                        break;
                    }
                    else {
                        temp = light;
                    }
                }

                for (long i = 0; i < MAP_FROM_TEMPERATURE_TO_HUMIDITY.size(); i = i + 3) {
                    long dest = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i);
                    long source = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i + 1);
                    long range = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i + 2);

                    if (temp >= source && temp < source + range) {
                        long diff = temp - source;
                        humidity = dest + diff;
                        break;
                    }
                    else {
                        humidity = temp;
                    }
                }

                for (long i = 0; i < MAP_FROM_HUMIDITY_TO_LOCATION.size(); i = i + 3) {
                    long dest = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) i);
                    long source = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) (i + 1));
                    long range = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) (i + 2));

                    if (humidity >= source && humidity < source + range) {
                        long diff = humidity - source;
                        loc = dest + diff;
                        break;
                    }
                    else {
                        loc = humidity;
                    }
                }

                if (min > loc) min = loc;
            }
        }
        System.out.println(min);
    }
}
