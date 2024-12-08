package aoc2023.day4;

import aoc2023.Utils;

import java.io.IOException;
import java.util.*;

public class Day4 {

    private record IncompleteCard(int id, List<Integer> winningNumbers, List<Integer> inHandNumbers) {}
    private record CompleteCard(int id, int score, int hits) {}

    private static List<IncompleteCard> incompleteCards = new ArrayList<>();
    private static List<CompleteCard> completeCards = new ArrayList<>();
    private static List<CardInstances> cardInstances = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/aoc2023/day4/input.txt";
        List<String> lines = Utils.readFile(filePath);
        System.out.println(calculateNumberOfCardInstances(lines));
    }

    private static int calculateNumberOfCardInstances(List<String> lines) {
        populateCardInstances(lines);
        int sum = 0;
        for (CardInstances card : cardInstances) {
            sum += card.getORIGINAL() + card.getCopies();
        }

        return sum;
    }

    private static void populateCardInstances(List<String> lines) {
        initializeCardInstances(lines);

        for (int i = 0; i < completeCards.size(); i++) {
            for (int j = 1; j <= completeCards.get(i).hits; j++) {
                cardInstances.get(i + j).setCopies(cardInstances.get(i + j).copies + cardInstances.get(i).copies + 1);
            }
        }
    }

    private static void initializeCardInstances(List<String> lines) {
        populateCompleteCards(lines);
        for (CompleteCard coCard : completeCards) {
            cardInstances.add(new CardInstances(0));
        }
    }

    private static int calculateTotalScore(List<String> lines) {
        populateCompleteCards(lines);
        int sum = 0;
        for (CompleteCard card : completeCards) {
            sum += card.score;
        }
        return sum;
    }

    private static void populateIncompleteCards(List<String> lines) {
        for (String str : lines) {
            String[] splitString = str.split(":");
            int id = Integer.parseInt(splitString[0].substring(5).trim());
            String[] splitValues = splitString[1].split("\\|");
            String winningValues = splitValues[0];
            String valuesInHand = splitValues[1];
            IncompleteCard incompleteCard = new IncompleteCard(id, getIntValuesFromString(winningValues), getIntValuesFromString(valuesInHand));
            incompleteCards.add(incompleteCard);
        }
    }

    private static void populateCompleteCards(List<String> lines) {
        populateIncompleteCards(lines);
        for (IncompleteCard inCard : incompleteCards) {
            int hits = 0;
            for (Integer winningValue : inCard.winningNumbers) {
                for (Integer handValue : inCard.inHandNumbers) {
                    if (Objects.equals(winningValue, handValue)) {
                        hits++;
                    }
                }
            }
            if (hits < 2) {
                CompleteCard completeCard = new CompleteCard(inCard.id, hits, hits);
                completeCards.add(completeCard);
            }
            else {
                CompleteCard completeCard = new CompleteCard(inCard.id, (int) Math.pow(2, (hits - 1)), hits);
                completeCards.add(completeCard);
            }
        }
    }

    private static List<Integer> getIntValuesFromString(String values) {
        List<String> strValues = Arrays.asList(values.split(" "));
        return strValues.stream()
                .filter(value -> !value.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private static class CardInstances {

        private int copies;

        public CardInstances(int copies) {
            this.copies = copies;
        }

        public int getORIGINAL() {
            return 1;
        }

        public int getCopies() {
            return copies;
        }

        public void setCopies(int copies) {
            this.copies = copies;
        }

        @Override
        public String toString() {
            return "CardInstances{" +
                    "copies=" + copies +
                    '}';
        }
    }
}
