package com.mick.ntw;

import java.util.List;

public class WordedNumberJoiner {
    public String join(List<WordedNumber> wordedNumbers) {
        StringBuilder words = new StringBuilder();

        for (int i = 0; i < wordedNumbers.size(); i++) {
            WordedNumber currentWordedNumber = wordedNumbers.get(i);

            words.append(currentWordedNumber.getWord());

            if (currentWordedNumber.getMultiplier() != Multiplier.NONE) {
                words.append(" ").append(currentWordedNumber.getMultiplier().getWordValue());
            }

            if (i + 1 < wordedNumbers.size()) {
                WordedNumber nextWordedNumber = wordedNumbers.get(i + 1);

                if (currentWordedNumber.getMultiplier() != Multiplier.NONE && (nextWordedNumber.getMultiplier() == Multiplier.NONE && nextWordedNumber.getValue() < 100)) {
                    words.append(JoiningWords.SPACE).append(JoiningWords.AND);
                }
                words.append(JoiningWords.SPACE);
            }
        }

        return words.toString();
    }
}
