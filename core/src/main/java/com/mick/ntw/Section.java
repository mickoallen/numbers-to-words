package com.mick.ntw;

import java.util.Objects;

/**
 * This class represents a 'section' of a number.
 */
public class Section {
    private final int value;
    private final String word;
    private final Multiplier multiplier;

    public Section(Builder builder) {
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

        public Builder value(int value){
            this.value = value;
            return this;
        }

        public Builder word(String word) {
            this.word = word;
            return this;
        }

        public Builder multiplier(Multiplier multiplier){
            this.multiplier = multiplier;
            return this;
        }

        public Section build(){
            if(value == null || word == null || multiplier == null){
                throw new IllegalArgumentException("value, word and multiplier must all be set");
            }

            return new Section(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section that = (Section) o;
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
