package com.mick.ntw;

public class NumbersToWordsConverterFactory {
    private static final boolean DEFAULT_HYPHENATE_COMPOSITES = false;

    public static NumbersToWordsConverter create() {
        return create(DEFAULT_HYPHENATE_COMPOSITES);
    }

    public static NumbersToWordsConverter create(boolean hyphenateConstants){
        return new NumbersToWordsConverter(
                new WordedNumberMapper(
                        new IntegerToWordConverter(hyphenateConstants)
                ),
                new WordedNumberJoiner()
        );
    }
}
