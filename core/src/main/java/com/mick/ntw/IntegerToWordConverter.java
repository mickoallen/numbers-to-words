package com.mick.ntw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Here is where the actual conversion of numbers to words takes place.
 * This class converts numbers less than 1000 into their English equivalents.
 */
public class IntegerToWordConverter {
    private static final Logger logger = LoggerFactory.getLogger(IntegerToWordConverter.class);
    private static final Map<Integer, String> NUMBER_DEFINITIONS;

    // Initialize number to word mappings
    static {
        Map<Integer, String> numberDefinitions = new HashMap<>();
        numberDefinitions.put(0, "zero");
        numberDefinitions.put(1, "one");
        numberDefinitions.put(2, "two");
        numberDefinitions.put(3, "three");
        numberDefinitions.put(4, "four");
        numberDefinitions.put(5, "five");
        numberDefinitions.put(6, "six");
        numberDefinitions.put(7, "seven");
        numberDefinitions.put(8, "eight");
        numberDefinitions.put(9, "nine");
        numberDefinitions.put(10, "ten");
        numberDefinitions.put(11, "eleven");
        numberDefinitions.put(12, "twelve");
        numberDefinitions.put(13, "thirteen");
        numberDefinitions.put(14, "fourteen");
        numberDefinitions.put(15, "fifteen");
        numberDefinitions.put(16, "sixteen");
        numberDefinitions.put(17, "seventeen");
        numberDefinitions.put(18, "eighteen");
        numberDefinitions.put(19, "nineteen");
        numberDefinitions.put(20, "twenty");
        numberDefinitions.put(30, "thirty");
        numberDefinitions.put(40, "forty");
        numberDefinitions.put(50, "fifty");
        numberDefinitions.put(60, "sixty");
        numberDefinitions.put(70, "seventy");
        numberDefinitions.put(80, "eighty");
        numberDefinitions.put(90, "ninety");
        numberDefinitions.put(100, "one hundred");
        numberDefinitions.put(200, "two hundred");
        numberDefinitions.put(300, "three hundred");
        numberDefinitions.put(400, "four hundred");
        numberDefinitions.put(500, "five hundred");
        numberDefinitions.put(600, "six hundred");
        numberDefinitions.put(700, "seven hundred");
        numberDefinitions.put(800, "eight hundred");
        numberDefinitions.put(900, "nine hundred");

        NUMBER_DEFINITIONS = Collections.unmodifiableMap(numberDefinitions);
    }

    private final boolean hyphenateComposites;

    /**
     * @param hyphenateComposites Whether to hyphenate composites, seventy three vs seventy-three
     */
    public IntegerToWordConverter(boolean hyphenateComposites) {
        logger.trace("hyphenateComposites set to {}", hyphenateComposites);
        this.hyphenateComposites = hyphenateComposites;
    }

    /**
     * Convert the given int value to words
     *
     * @param value int to convert
     * @return converted int
     * @throws IllegalArgumentException if value is greater than or equal to 1000
     */
    public String toWords(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Only positive numbers can be converted");
        }

        logger.trace("Converting integer {} to words", value);
        if (NUMBER_DEFINITIONS.containsKey(value)) {
            if (logger.isTraceEnabled()) {
                logger.trace("number definition defined: {}", NUMBER_DEFINITIONS.get(value));
            }
            return NUMBER_DEFINITIONS.get(value);
        } else if (isNumberInTheTens(value)) {
            return tensToWords(value);
        } else if (isNumberInTheHundreds(value)) {
            return hundredsToWords(value);
        }

        throw new IllegalArgumentException("Can only convert integers less than 1000");
    }

    private boolean isNumberInTheTens(int value) {
        return value >= 21 && value <= 99;
    }

    private String tensToWords(int value) {
        int singles = value % 10;

        String joiningWord = hyphenateComposites ? JoiningWords.HYPHEN : JoiningWords.SPACE;

        return String.format("%s%s%s", toWords(value - singles), joiningWord, toWords(singles));
    }

    private boolean isNumberInTheHundreds(int value) {
        return value >= 101 && value <= 999;
    }

    private String hundredsToWords(int value) {
        int tens = value % 100;
        return String.format("%s %s %s", toWords(value - tens), JoiningWords.AND, toWords(tens));
    }
}

