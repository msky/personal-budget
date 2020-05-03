package msky.personalbudget.query;

import lombok.Value;

@Value
public class FindBudgetByIdQuery implements Query {
    String budgetId;
}
