package com.mick.ntw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Converts numbers to English words.
 */
public class NumbersToWordsConverter {
    private static final Logger logger = LoggerFactory.getLogger(NumbersToWordsConverter.class);

    private static final String NEGATIVE = "negative";
    private static final String ZERO = "Zero";

    private final SectionCreator sectionCreator;
    private final SectionJoiner sectionJoiner;

    public NumbersToWordsConverter(final SectionCreator sectionCreator, final SectionJoiner sectionJoiner) {
        this.sectionCreator = sectionCreator;
        this.sectionJoiner = sectionJoiner;
    }

    /**
     * Convert the given number into words.
     *
     * @param value the number to convert to words
     * @return the converted number as words
     */
    public String convert(int value) {
        logger.trace("Converting number: {} to words", value);

        if (value == 0) {
            logger.trace("Number is zero, returning");
            return ZERO;
        }

        List<Section> sections = sectionCreator.mapFromNumber(value);
        String numbersAsWords = sectionJoiner.join(sections);

        if (isNegative(value)) {
            logger.trace("{} is negative, treating accordingly", value);
            numbersAsWords = NEGATIVE + JoiningWords.SPACE + numbersAsWords;
        }

        String wordedNumber = capitalizeFirstLetter(numbersAsWords);
        logger.trace("Finished converting {} into {}", value, wordedNumber);

        return wordedNumber;
    }

    private boolean isNegative(int value) {
        return value < 0;
    }

    private String capitalizeFirstLetter(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
