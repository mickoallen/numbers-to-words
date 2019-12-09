package com.mick.ntw

import spock.lang.Specification

class SectionBuilderSpec extends Specification {
    def "valid section is built correctly"() {
        when:
        Section section = Section
                .builder()
                .value(123)
                .multiplier(Multiplier.NONE)
                .word("one hundred and twenty three")
                .build()

        then:
        section.getValue() == 123
        section.getMultiplier() == Multiplier.NONE
        section.getWord() == "one hundred and twenty three"
    }

    def "invalid section is not built and exception is thrown"() {
        when:
        Section.builder().build()

        then:
        thrown(IllegalArgumentException)
    }
}
