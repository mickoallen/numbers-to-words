package com.mick.ntw;

/**
 * This class represents a 'section' of a number.
 */
public class Section {
    private final int value;
    private final String word;
    private final Multiplier multiplier;

    public Section(final Builder builder) {
        this.value = builder.value;
        this.word = builder.word;
        this.multiplier = builder.multiplier;
    }

    public static Builder builder() {
        return new Builder();
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

    @Override
    public String toString() {
        return "Section{" +
                "value=" + value +
                ", word='" + word + '\'' +
                ", multiplier=" + multiplier +
                '}';
    }

    public static class Builder {
        private Integer value;
        private String word;
        private Multiplier multiplier;

        /**
         * @param value Integer value representing the section
         * @return this {@link Builder} instance
         */
        public Builder value(int value) {
            this.value = value;
            return this;
        }

        /**
         * @param word words representing the section
         * @return this {@link Builder} instance
         */
        public Builder word(String word) {
            this.word = word;
            return this;
        }

        /**
         * @param multiplier {@link Multiplier} for the section
         * @return this {@link Builder} instance
         */
        public Builder multiplier(Multiplier multiplier) {
            this.multiplier = multiplier;
            return this;
        }

        /**
         * @return build and return a valid {@link Section}
         */
        public Section build() {
            if (value == null || word == null || multiplier == null) {
                throw new IllegalArgumentException("value, word and multiplier must all be set");
            }

            return new Section(this);
        }
    }
}
