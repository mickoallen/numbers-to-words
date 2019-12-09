package com.mick.ntw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Joins sections into a single string.
 */
public class SectionJoiner {
    private static final Logger logger = LoggerFactory.getLogger(SectionJoiner.class);

    /**
     * Join each of the sections together, adding the appropriate {@link Multiplier} string if required,
     * and any additional 'and's.
     *
     *  @param sections List of {@link Section} to join
     * @return {@link String} representing the joint sections
     */
    public String join(final List<Section> sections) {
        logger.trace("Joining sections {}", sections);

        StringBuilder jointSection = new StringBuilder();

        for (int i = 0; i < sections.size(); i++) {
            Section currentSection = sections.get(i);

            logger.trace("Adding {} to sections", currentSection.getWord());
            jointSection.append(currentSection.getWord());

            if (currentSection.getMultiplier() != Multiplier.NONE) {
                logger.trace("Section has not-none multiplier {}, adding to section", currentSection.getMultiplier());
                jointSection.append(" ").append(currentSection.getMultiplier().getWordValue());
            }

            //if this is not the last section,
            if (i + 1 < sections.size()) {
                Section nextSection = sections.get(i + 1);

                if (isAndRequiredBetweenSections(currentSection, nextSection)) {
                    logger.trace("'and' is required between {} and {}", currentSection, nextSection);
                    jointSection.append(JoiningWords.SPACE).append(JoiningWords.AND);
                }
                jointSection.append(JoiningWords.SPACE);
            }
        }

        logger.trace("finished joining sections: {}", jointSection);
        return jointSection.toString();
    }

    private boolean isAndRequiredBetweenSections(Section firstSection, Section secondSection){
        return firstSection.getMultiplier() != Multiplier.NONE && secondSection.getMultiplier() == Multiplier.NONE && secondSection.getValue() < 100;
    }
}
