package com.mick.ntw

import spock.lang.Specification

class WordMapperSpec extends Specification {
    WordMapper integerToWordConverter
    WordMapper integerToWordConverterHyphenated

    def setup() {
        integerToWordConverter = new WordMapper(false)
        integerToWordConverterHyphenated = new WordMapper(true)
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
