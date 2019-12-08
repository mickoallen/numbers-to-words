package com.mick.ntw

import spock.lang.Specification

class NumbersIntegerToWordConverterSpec extends Specification {
    private NumbersToWordsConverter numbersToWordsConverter

    def setup() {
        numbersToWordsConverter = NumbersToWordsConverterFactory.create()
    }

    def "test positive whole number conversion"() {
        expect:
        numbersToWordsConverter.convert(number) == word

        where:
        number            | word
        0                 | "Zero"
        13                | "Thirteen"
        85                | "Eighty five"
        5237              | "Five thousand two hundred and thirty seven"
        1234567           | "One million two hundred and thirty four thousand five hundred and sixty seven"
        10000000          | "Ten million"
        100               | "One hundred"
        777               | "Seven hundred and seventy seven"
        1000              | "One thousand"
        1001              | "One thousand and one"
        2025              | "Two thousand and twenty five"
        100000            | "One hundred thousand"
        73010013          | "Seventy three million ten thousand and thirteen"
        1000000           | "One million"
        1001010           | "One million one thousand and ten"
        100000000         | "One hundred million"
        200000000         | "Two hundred million"
        1000000000        | "One billion"
        Integer.MAX_VALUE | "Two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven"
    }

    def "test negative number conversion"() {
        expect:
        numbersToWordsConverter.convert(number) == word

        where:
        number            | word
        -0                | "Zero"
        -13               | "Negative thirteen"
        -85               | "Negative eighty five"
        -5237             | "Negative five thousand two hundred and thirty seven"
        -1000000000       | "Negative one billion"
//        Integer.MIN_VALUE | "todo"
    }
}
