package com.mick.ntw;

/**
 * Represents the postfix descriptor for numbers greater than or equal to 1000.
 */
public enum Multiplier {
    NONE(""),
    THOUSAND("thousand"),
    MILLION("million"),
    BILLION("billion");

    final String wordValue;

    Multiplier(String wordValue) {
        this.wordValue = wordValue;
    }

    public String getWordValue() {
        return wordValue;
    }
}
