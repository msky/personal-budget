package msky.personalbudget.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
class NewBudgetDefinedEvent implements Event {
    String userId;
    String budgetId;
    LocalDate startDate;
    LocalDate endDate;
}
