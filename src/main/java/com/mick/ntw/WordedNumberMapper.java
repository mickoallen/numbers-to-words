package com.mick.ntw;

import java.util.*;

public class WordedNumberMapper {
    private static final int SECTION_DIVISOR = 1000;

    private final IntegerToWordConverter toWordsConverter;

    public WordedNumberMapper(final IntegerToWordConverter toWordsConverter) {
        this.toWordsConverter = toWordsConverter;
    }

    public List<WordedNumber> mapFromNumber(final Integer number) {
        Stack<Integer> sections = divideIntoSections(number);
        return mapSectionsToWordedNumbers(sections);
    }

    private Stack<Integer> divideIntoSections(Integer number){
        Stack<Integer> sections = new Stack<>();

        int workingNumber = number;
        while (workingNumber > 0) {
            sections.add(workingNumber % SECTION_DIVISOR);
            workingNumber = workingNumber / SECTION_DIVISOR;
        }

        return sections;
    }

    private List<WordedNumber> mapSectionsToWordedNumbers(Stack<Integer> sections) {
        List<WordedNumber> wordedNumbers = new ArrayList<>();

        Iterator<Integer> sectionsIterator = sections.iterator();
        Iterator<Multiplier> multipliersIterator = EnumSet.allOf(Multiplier.class).iterator();

        while (sectionsIterator.hasNext()) {
            Integer currentSection = sectionsIterator.next();
            Multiplier currentMultiplier = multipliersIterator.next();

            if (currentSection > 0) {
                wordedNumbers.add(
                        WordedNumber
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
