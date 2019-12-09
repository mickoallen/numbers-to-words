package com.mick.ntw;

import java.util.List;

public class SectionJoiner {
    public String join(final List<Section> sections) {
        StringBuilder words = new StringBuilder();

        for (int i = 0; i < sections.size(); i++) {
            Section currentSection = sections.get(i);

            words.append(currentSection.getWord());

            if (currentSection.getMultiplier() != Multiplier.NONE) {
                words.append(" ").append(currentSection.getMultiplier().getWordValue());
            }

            if (i + 1 < sections.size()) {
                Section nextSection = sections.get(i + 1);

                if (currentSection.getMultiplier() != Multiplier.NONE && (nextSection.getMultiplier() == Multiplier.NONE && nextSection.getValue() < 100)) {
                    words.append(JoiningWords.SPACE).append(JoiningWords.AND);
                }
                words.append(JoiningWords.SPACE);
            }
        }

        return words.toString();
    }
}
