package aoc2023.day5;

import aoc2023.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

    private static final List<MappingResult> MAPPING_RESULTS = new ArrayList<>();

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
        populateMappingResults(lines);
//        MAPPING_RESULTS.forEach(System.out::println);

        long lowestLocation = MAPPING_RESULTS.getFirst().location;
        for (MappingResult mappingResult : MAPPING_RESULTS) {
            if (lowestLocation > mappingResult.getLocation()) {
                lowestLocation = mappingResult.getLocation();
            }
        }
        System.out.println(lowestLocation);
    }

    private static void initializeMappingResults(List<String> lines) {
        String[] seeds = lines.getFirst().split(":")[1].split(" ");
        for (String str : seeds) {
            if (str.isEmpty()) continue;
            MappingResult mappingResult = new MappingResult();
            mappingResult.setSeed(Long.parseLong(str));
            MAPPING_RESULTS.add(mappingResult);
        }
    }

    private static void populateMappingResults(List<String> lines) {
        initializeMappingResults(lines);
        Day5Utils.populateMappings(lines);

        for (MappingResult mappingResult : MAPPING_RESULTS) {
            for (long i = 0; i < MAP_FROM_SEED_TO_SOIL.size(); i = i + 3) {
                long dest = MAP_FROM_SEED_TO_SOIL.get((int) i);
                long source = MAP_FROM_SEED_TO_SOIL.get((int) i + 1);
                long range = MAP_FROM_SEED_TO_SOIL.get((int) i + 2);

                if (mappingResult.getSeed() >= source && mappingResult.getSeed() < source + range) {
                    long diff = mappingResult.getSeed() - source;
                    mappingResult.setSoil(dest + diff);
                    break;
                }
                else {
                    mappingResult.setSoil(mappingResult.getSeed());
                }
            }

            for (long i = 0; i < MAP_FROM_SOIL_TO_FERTILIZER.size(); i = i + 3) {
                long dest = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i);
                long source = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i + 1);
                long range = MAP_FROM_SOIL_TO_FERTILIZER.get((int) i + 2);

                if (mappingResult.getSoil() >= source && mappingResult.getSoil() < source + range) {
                    long diff = mappingResult.getSoil() - source;
                    mappingResult.setFertilizer(dest + diff);
                    break;
                }
                else {
                    mappingResult.setFertilizer(mappingResult.getSoil());
                }
            }

            for (long i = 0; i < MAP_FROM_FERTILIZER_TO_WATER.size(); i = i + 3) {
                long dest = MAP_FROM_FERTILIZER_TO_WATER.get((int) i);
                long source = MAP_FROM_FERTILIZER_TO_WATER.get((int) i + 1);
                long range = MAP_FROM_FERTILIZER_TO_WATER.get((int) i + 2);

                if (mappingResult.getFertilizer() >= source && mappingResult.getFertilizer() < source + range) {
                    long diff = mappingResult.getFertilizer() - source;
                    mappingResult.setWater(dest + diff);
                    break;
                }
                else {
                    mappingResult.setWater(mappingResult.getFertilizer());
                }
            }

            for (long i = 0; i < MAP_FROM_WATER_TO_LIGHT.size(); i = i + 3) {
                long dest = MAP_FROM_WATER_TO_LIGHT.get((int) i);
                long source = MAP_FROM_WATER_TO_LIGHT.get((int) i + 1);
                long range = MAP_FROM_WATER_TO_LIGHT.get((int) i + 2);

                if (mappingResult.getWater() >= source && mappingResult.getWater() < source + range) {
                    long diff = mappingResult.getWater() - source;
                    mappingResult.setLight(dest + diff);
                    break;
                }
                else {
                    mappingResult.setLight(mappingResult.getWater());
                }
            }

            for (long i = 0; i < MAP_FROM_LIGHT_TO_TEMPERATURE.size(); i = i + 3) {
                long dest = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i);
                long source = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i + 1);
                long range = MAP_FROM_LIGHT_TO_TEMPERATURE.get((int) i + 2);

                if (mappingResult.getLight() >= source && mappingResult.getLight() < source + range) {
                    long diff = mappingResult.getLight() - source;
                    mappingResult.setTemperature(dest + diff);
                    break;
                }
                else {
                    mappingResult.setTemperature(mappingResult.getLight());
                }
            }

            for (long i = 0; i < MAP_FROM_TEMPERATURE_TO_HUMIDITY.size(); i = i + 3) {
                long dest = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i);
                long source = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i + 1);
                long range = MAP_FROM_TEMPERATURE_TO_HUMIDITY.get((int) i + 2);

                if (mappingResult.getTemperature() >= source && mappingResult.getTemperature() < source + range) {
                    long diff = mappingResult.getTemperature() - source;
                    mappingResult.setHumidity(dest + diff);
                    break;
                }
                else {
                    mappingResult.setHumidity(mappingResult.getTemperature());
                }
            }

            for (long i = 0; i < MAP_FROM_HUMIDITY_TO_LOCATION.size(); i = i + 3) {
                long dest = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) i);
                long source = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) (i + 1));
                long range = MAP_FROM_HUMIDITY_TO_LOCATION.get((int) (i + 2));

                if (mappingResult.getHumidity() >= source && mappingResult.getHumidity() < source + range) {
                    long diff = mappingResult.getHumidity() - source;
                    mappingResult.setLocation(dest + diff);
                    break;
                }
                else {
                    mappingResult.setLocation(mappingResult.getHumidity());
                }
            }
        }
    }

    private static class MappingResult {
        private long seed;
        private long soil;
        private long fertilizer;
        private long water;
        private long light;
        private long temperature;
        private long humidity;
        private long location;

        public MappingResult() {}

        public long getSeed() {
            return seed;
        }

        public void setSeed(long seed) {
            this.seed = seed;
        }

        public long getSoil() {
            return soil;
        }

        public void setSoil(long soil) {
            this.soil = soil;
        }

        public long getFertilizer() {
            return fertilizer;
        }

        public void setFertilizer(long fertilizer) {
            this.fertilizer = fertilizer;
        }

        public long getWater() {
            return water;
        }

        public void setWater(long water) {
            this.water = water;
        }

        public long getLight() {
            return light;
        }

        public void setLight(long light) {
            this.light = light;
        }

        public long getTemperature() {
            return temperature;
        }

        public void setTemperature(long temperature) {
            this.temperature = temperature;
        }

        public long getHumidity() {
            return humidity;
        }

        public void setHumidity(long humidity) {
            this.humidity = humidity;
        }

        public long getLocation() {
            return location;
        }

        public void setLocation(long location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "MappingResult{" +
                    "seed=" + seed +
                    ", soil=" + soil +
                    ", fertilizer=" + fertilizer +
                    ", water=" + water +
                    ", light=" + light +
                    ", temperature=" + temperature +
                    ", humidity=" + humidity +
                    ", location=" + location +
                    '}';
        }
    }
}
