package com.mick.ntw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * This maps a single int into a list of {@link Section} objects.
 * This is achieved by breaking the int into groups of three (hundreds).
 * <p>
 * eg. 1 234 567 becomes [567, 342, 1]
 * Reverse order since using modulo to get remainder.
 * <p>
 * Each of these groups can then be mapped to English words and a multiplier (thousand, million, billion)
 * attributed to each, then reversed back to proper order for reading.
 */
class DefaultSectionCreator implements SectionCreator {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSectionCreator.class);
    // 10^(section size: 3)
    private static final int SECTION_DIVISOR = 1000;

    private final WordMapper toWordsConverter;

    public DefaultSectionCreator(final WordMapper toWordsConverter) {
        this.toWordsConverter = toWordsConverter;
    }

    /**
     * Map the given number into a list of {@link Section} objects.
     *
     * @param value number to map
     * @return mapped list of {@link Section} objects.
     */
    @Override
    public List<Section> mapFromNumber(int value) {
        logger.trace("Dividing {} into groups of three", value);
        List<Integer> groups = splitIntoHundreds(value);

        logger.trace("Number groups: {}", groups);
        return mapHundredsToSections(groups);
    }

    private List<Integer> splitIntoHundreds(int value) {
        List<Integer> hundreds = new ArrayList<>();
        int workingValue = value;

        while (workingValue != 0) {
            hundreds.add(getPositiveHundreds(workingValue));
            workingValue = workingValue / SECTION_DIVISOR;
        }

        return hundreds;
    }

    /**
     * Since this will always be > Integer.MIN_VALUE we can safely get the absolute value, simplifying the
     * rest of the conversion as it will only be with positive numbers.
     *
     * @param value int value to get the positive hundreds from
     * @return the positive hundreds
     */
    private int getPositiveHundreds(int value){
        return Math.abs(value % SECTION_DIVISOR);
    }

    /**
     * Maps each set of hundreds to a {@link Section}.
     *
     * @param hundreds list of hundreds to map
     * @return list of mapped {@link Section}
     */
    private List<Section> mapHundredsToSections(final List<Integer> hundreds) {
        List<Section> sections = new ArrayList<>();

        Iterator<Integer> hundredsIterator = hundreds.iterator();
        //enum sets are ordered based on the declaration order of the enums
        Iterator<Multiplier> multipliersIterator = EnumSet.allOf(Multiplier.class).iterator();

        //loop over each hundred AND each multiplier at the same time
        while (hundredsIterator.hasNext()) {
            int currentHundred = hundredsIterator.next();
            Multiplier currentMultiplier = multipliersIterator.next();
            logger.trace("Group: {} being mapped to multiplier: {}", currentMultiplier, currentMultiplier);

            if (currentHundred > 0) {
                sections.add(
                        Section
                                .builder()
                                .multiplier(currentMultiplier)
                                .value(currentHundred)
                                .word(toWordsConverter.toWords(currentHundred))
                                .build()
                );
            }
        }

        //re-order the hundreds from left-to-right
        Collections.reverse(sections);

        return sections;
    }
}
