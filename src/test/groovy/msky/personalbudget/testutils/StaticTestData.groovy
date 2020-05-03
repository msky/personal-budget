package msky.personalbudget.testutils

import java.time.LocalDate

class StaticTestData {
    public static final String USER = UUID.randomUUID().toString()
    public static final String BUDGET_GUID = UUID.randomUUID().toString()

    public static final LocalDate FIRST_OF_MAY = LocalDate.of(2010, 5,1)
    public static final LocalDate LAST_OF_MAY = LocalDate.of(2010, 5, 31)
}
