package com.mick.ntw.cli;

import com.mick.ntw.NumbersToWordsConverter;
import com.mick.ntw.NumbersToWordsConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Small CLI app to test {@link NumbersToWordsConverter}.
 *
 * See readme for usage.
 */
public class CliMain {
    private static final Logger logger = LoggerFactory.getLogger(CliMain.class);
    private static final String EXIT_CODE = "exit";
    private static final String HYPHENATE_COMPOSITES_SWITCH = "hyphenate-composites";

    public static void main(String[] args) {
        logger.trace("Initializing scanner");
        Scanner scanner = new Scanner(System.in);

        boolean hyphenateCompsites = doHyphenateComposites(args);
        logger.trace("{} set to {}", HYPHENATE_COMPOSITES_SWITCH, hyphenateCompsites);

        logger.info("Enter a number (or type 'exit' to exit):");
        NumbersToWordsConverter numbersToWordsConverter = NumbersToWordsConverterFactory.create(hyphenateCompsites);

        logger.trace("Start REP loop");
        while (true) {
            logger.info("\n");
            String input = scanner.nextLine();
            logger.trace("Recieved input: {}", input);

            if (EXIT_CODE.equals(input)) {
                break;
            }

            try {
                int value = Integer.parseInt(input);

                logger.trace("Converting {} to words", value);
                logger.info(numbersToWordsConverter.convert(value));
            } catch (NumberFormatException e) {
                logger.error("ERROR: Failed to parse input, must be a valid 32 bit between between {} and {}", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }

        logger.info("Bye.\n");
    }

    private static boolean doHyphenateComposites(String[] args) {
        return args.length > 0 && HYPHENATE_COMPOSITES_SWITCH.equals(args[0]);
    }
}
