package msky.personalbudget.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class DefineNewBudgetCommand implements Command {
    String userId;
    String budgetGUID;
    LocalDate startDate;
    LocalDate endDate;
}
