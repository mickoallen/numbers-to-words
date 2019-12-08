package com.mick.ntw;

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
