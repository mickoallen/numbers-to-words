package com.mick.ntw;

import java.util.List;

public class NumbersToWordsConverter {
    private static final String NEGATIVE = "negative";
    private static final String ZERO = "Zero";

    private final WordedNumberMapper wordedNumberMapper;
    private final WordedNumberJoiner wordedNumberJoiner;

    public NumbersToWordsConverter(WordedNumberMapper wordedNumberMapper, WordedNumberJoiner wordedNumberJoiner) {
        this.wordedNumberMapper = wordedNumberMapper;
        this.wordedNumberJoiner = wordedNumberJoiner;
    }

    public String convert(Integer integer) {
        if (integer == 0) {
            return ZERO;
        }

        List<WordedNumber> wordedNumbers = wordedNumberMapper.mapFromNumber(Math.abs(integer));
        String numbersAsWords = wordedNumberJoiner.join(wordedNumbers);

        if (isNegative(integer)) {
            numbersAsWords = String.format("%s %s", NEGATIVE, numbersAsWords);
        }

        return capitalize(numbersAsWords);
    }

    private boolean isNegative(int integer) {
        return integer < 0;
    }

    private String capitalize(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
