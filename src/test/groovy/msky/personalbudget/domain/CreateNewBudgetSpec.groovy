package msky.personalbudget.domain

import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class CreateNewBudgetSpec extends Specification {

    def "user can create budget for given month with custom start and end day"() {
        when:
        def a = 2
        then:
        assertThat(true).isEqualTo(false);
    }
}