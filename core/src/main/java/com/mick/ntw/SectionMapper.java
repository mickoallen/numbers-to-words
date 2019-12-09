package com.mick.ntw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SectionMapper {
    private static final Logger logger = LoggerFactory.getLogger(SectionMapper.class);

    private static final int SECTION_DIVISOR = 1000;

    private final IntegerToWordConverter toWordsConverter;

    public SectionMapper(final IntegerToWordConverter toWordsConverter) {
        this.toWordsConverter = toWordsConverter;
    }

    public List<Section> mapFromNumber(int value) {
        logger.trace("Dividing {} into sections", value);
        List<Integer> sections = splitIntoParts(value);

        logger.trace("Sections: {}", sections);
        return mapPartsToSections(sections);
    }

    private List<Integer> splitIntoParts(int value) {
        List<Integer> sections = new ArrayList<>();

        int workingNumber = value;
        while (workingNumber > 0) {
            sections.add(workingNumber % SECTION_DIVISOR);
            workingNumber = workingNumber / SECTION_DIVISOR;
        }

        return sections;
    }

    private List<Section> mapPartsToSections(final List<Integer> sections) {
        List<Section> wordedNumbers = new ArrayList<>();

        Iterator<Integer> sectionsIterator = sections.iterator();
        Iterator<Multiplier> multipliersIterator = EnumSet.allOf(Multiplier.class).iterator();

        while (sectionsIterator.hasNext()) {
            int currentSection = sectionsIterator.next();
            Multiplier currentMultiplier = multipliersIterator.next();
            logger.trace("Section: {} being mapped to multiplier: {}", currentMultiplier, currentMultiplier);

            if (currentSection > 0) {
                wordedNumbers.add(
                        Section
                                .builder()
                                .multiplier(currentMultiplier)
                                .value(currentSection)
                                .word(toWordsConverter.toWords(currentSection))
                                .build()
                );
            }
        }

        Collections.reverse(wordedNumbers);

        return wordedNumbers;
    }
}
