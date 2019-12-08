package com.mick.ntw;

import java.util.Objects;

public class WordedNumber {
    private final int value;
    private final String word;
    private final Multiplier multiplier;

    public WordedNumber(Builder builder) {
        this.value = builder.value;
        this.word = builder.word;
        this.multiplier = builder.multiplier;
    }

    public int getValue() {
        return value;
    }

    public String getWord() {
        return word;
    }

    public Multiplier getMultiplier() {
        return multiplier;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer value;
        private String word;
        private Multiplier multiplier;

        public Builder value(final int value){
            this.value = value;
            return this;
        }

        public Builder word(final String word) {
            this.word = word;
            return this;
        }

        public Builder multiplier(final Multiplier multiplier){
            this.multiplier = multiplier;
            return this;
        }

        public WordedNumber build(){
            return new WordedNumber(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordedNumber that = (WordedNumber) o;
        return value == that.value &&
                Objects.equals(word, that.word) &&
                multiplier == that.multiplier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, word, multiplier);
    }

    @Override
    public String toString() {
        return "WordedNumber{" +
                "value=" + value +
                ", word='" + word + '\'' +
                ", multiplier=" + multiplier +
                '}';
    }
}
