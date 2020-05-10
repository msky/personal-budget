package msky.personalbudget.domain;

import lombok.Value;

@Value
public class FindBudgetByIdQuery implements Query {
    String budgetId;
}
