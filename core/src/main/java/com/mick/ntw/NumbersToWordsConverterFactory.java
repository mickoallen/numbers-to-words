package com.mick.ntw;

/**
 * This should be used for building {@link NumbersToWordsConverter} instances.
 * <p>
 * *note: in the future this might want to consume a custom configuration object with more
 * configurable parameters. For now since there is only 1 we will set it in overloaded
 * constructor.
 */
public class NumbersToWordsConverterFactory {
    private static final boolean DEFAULT_HYPHENATE_COMPOSITES = false;

    /**
     * @return Default {@link NumbersToWordsConverter} instance.
     */
    public static NumbersToWordsConverter create() {
        return create(DEFAULT_HYPHENATE_COMPOSITES);
    }

    /**
     * @param hyphenateConstants option for hyphenating composites.
     * @return Configured {@link NumbersToWordsConverter} instance.
     */
    public static NumbersToWordsConverter create(boolean hyphenateConstants) {
        return new NumbersToWordsConverter(
                new DefaultSectionCreator(
                        new WordMapper(hyphenateConstants)
                ),
                new DefaultSectionJoiner()
        );
    }
}
