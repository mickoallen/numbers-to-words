package com.mick.ntw

import spock.lang.Specification

class IntegerToWordConverterSpec extends Specification {
    IntegerToWordConverter integerToWordConverter
    IntegerToWordConverter integerToWordConverterHyphenated

    def setup() {
        integerToWordConverter = new IntegerToWordConverter(false)
        integerToWordConverterHyphenated = new IntegerToWordConverter(true)
    }

    def "negative number conversion causes exception"() {
        when:
        integerToWordConverter.toWords(-10)
        then:
        thrown(IllegalArgumentException)
    }

    def "number larger than 1000 causes exception"() {
        when:
        integerToWordConverter.toWords(1001)
        then:
        thrown(IllegalArgumentException)
    }

    def "non hyphenated composite words convert correctly"() {
        expect:
        integerToWordConverter.toWords(number) == word

        where:
        number | word
        77     | "seventy seven"
        256    | "two hundred and fifty six"
    }

    def "hyphenated composite words convert correctly"() {
        expect:
        integerToWordConverterHyphenated.toWords(number) == word

        where:
        number | word
        77     | "seventy-seven"
        256    | "two hundred and fifty-six"
    }


}
